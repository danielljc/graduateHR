package edu.nju.master.graduate.controller;

import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.entity.UrlRecord;
import edu.nju.master.graduate.service.IUrlRecordService;
import edu.nju.master.graduate.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Daniel
 * @title: RecordController
 * @description: 检测记录控制器
 * @date 2019-03-30 15:20
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    IUrlRecordService urlRecordService;

    @ApiOperation("历史检测列表")
    @GetMapping("/list/user/{userId}")
    public ResponseDto recordList(@PathVariable Integer userId) {
        return ResultUtil.getResult(urlRecordService.findByUserId(userId));
    }

    @ApiOperation("删除检测记录")
    @PutMapping("/{recordId}/delete")
    public ResponseDto deleteRecord(@PathVariable Integer recordId) {
        UrlRecord urlRecord = urlRecordService.findByRecordId(recordId);
        if (urlRecord != null) {
            urlRecordService.deleteByRecordId(recordId);
            return ResultUtil.getResult("删除成功");
        } else
            return ResultUtil.getResult("无此记录");
    }

    @ApiOperation("搜索检测历史")
    @GetMapping("/search/keyword/{keyword}")
    public ResponseDto search(@PathVariable String keyword) {
        return ResultUtil.getResult(urlRecordService.searchByKeyword(keyword));
    }

}
