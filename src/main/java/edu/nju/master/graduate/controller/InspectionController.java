package edu.nju.master.graduate.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.entity.UrlRecord;
import edu.nju.master.graduate.utils.CommunicationUtils;
import edu.nju.master.graduate.utils.FileUtils;
import edu.nju.master.graduate.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/picture/batch")
    public void inspectPictureByBatch() {}

}
