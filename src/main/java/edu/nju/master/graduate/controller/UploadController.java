package edu.nju.master.graduate.controller;

import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.utils.FileUtils;
import edu.nju.master.graduate.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Daniel
 * @title: UploadController
 * @description: 上传控制器
 * @date 2019-03-27 19:06
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @ApiOperation("上传文件夹")
    @PostMapping("/folder")
    public ResponseDto uploadFolder(MultipartFile[] folder) {
        String result = FileUtils.saveMultiFile(FileUtils.basePath, folder);
        return ResultUtil.getResult(result);
    }
}
