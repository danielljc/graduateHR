package edu.nju.master.graduate.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.nju.master.graduate.dto.ClassificationRespDto;
import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.entity.UrlRecord;
import edu.nju.master.graduate.utils.CommunicationUtils;
import edu.nju.master.graduate.utils.FileUtils;
import edu.nju.master.graduate.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @description: 检测
 * @author: Daniel Li
 * @create: 2018-10-23
 */
@RestController
@RequestMapping("/inspect")
public class InspectionController {

    @ApiOperation("网址批量检测")
    @PostMapping("/url/batch")
    public ResponseDto inspectUrlByBatch(@RequestBody MultipartFile multipartFile) throws IOException {
        // 解析txt文件，获取UrlRecord对象
        List<UrlRecord> records = FileUtils.getUrlsFromMultipartFile(multipartFile);
        List<String> urls = records.stream()
                .map(record->record.getUrl())
                .collect(Collectors.toList());
        // 与python服务通信：检测，并返回检测结果（Json字符串）
        String response = CommunicationUtils.getResultByUrls(urls);

        // 字符串解析
        // Json格式为：
        // {
        //  "predict":[0, 1, 0, 1, 1],
        //  "possibility":[0.912, 0.969, 0.865, 0.734, 0.999],
        //  "website":["百度", "阿里"， "腾讯"， "小米", "美团"]
        // }
        JSONObject data = JSONObject.parseObject(response);
        JSONArray predict = data.getJSONArray("predict");  // 模型预测结果
        List<Integer> predictList = predict.stream()
                .map(p->Integer.parseInt(p.toString()))
                .collect(Collectors.toList());  // 转换成数组
        JSONArray possibility = data.getJSONArray("possibility");  // 模型预测概率
        List<Double> possibilityList = possibility.stream()
                .map(p->Double.parseDouble(p.toString()))
                .collect(Collectors.toList());
        JSONArray website = data.getJSONArray("website");  // 爬虫得到的网站名称
        List<String> websites = website.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        // 将结果封装成UrlRecord对象返回给前端
        AtomicInteger flag = new AtomicInteger(0);  // 标识
        records.parallelStream().forEachOrdered(record->{
            record.setResult(predictList.get(flag.get()));
            record.setPossibility(possibilityList.get(flag.get()));
            record.setWebsiteName(websites.get(flag.get()));
            flag.incrementAndGet();
        });

        return ResultUtil.getResult(records);
    }

    @PostMapping("/url/{url}")
    public void inspectUrl(@PathVariable String url) {

    }

    @ApiOperation("检测文件夹中的图片并分类")
    @GetMapping("/picture/folder/{folder}")
    public ResponseDto inspectPictureByBatch(@PathVariable String folder) {
        // 传入服务器本地的待检测文件夹路径，返回检测结果
        String response = CommunicationUtils.getResultByFolderPath(FileUtils.basePath + folder);
        // Json格式：
        // {
        //   "filename": ["picture1", "picture2", "picture3"],
        //   "predict": [0, 1, 1],
        //   "possibility": [0.998, 0.987, 0.864]
        // }
        JSONObject data = JSONObject.parseObject(response);
        JSONArray filename = data.getJSONArray("filename");  // 文件名
        List<String> filenameList = filename.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        JSONArray predict = data.getJSONArray("predict");  // 模型预测结果
        List<Integer> predictList = predict.stream()
                .map(p->Integer.parseInt(p.toString()))
                .collect(Collectors.toList());  // 转换成数组

        List<String> normalPicture = new ArrayList<>();
        List<String> abnormalPicture = new ArrayList<>();
        // 图片服务器地址
        String pictureServer = "http://192.168.0.126:5000/";
        AtomicInteger flag = new AtomicInteger(0);  // 标识
        filenameList.stream().forEachOrdered(file -> {
            if (predictList.get(flag.get()).equals(0))
                normalPicture.add(pictureServer+file);
            else
                abnormalPicture.add(pictureServer+file);
            flag.incrementAndGet();
        });
        ClassificationRespDto dto = new ClassificationRespDto();
        dto.setNormalPicture(normalPicture);
        dto.setAbnormalPicture(abnormalPicture);
        return ResultUtil.getResult(dto);
    }

}
