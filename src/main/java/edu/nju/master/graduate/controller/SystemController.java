package edu.nju.master.graduate.controller;


import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.entity.User;
import edu.nju.master.graduate.service.IUserService;
import edu.nju.master.graduate.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Daniel
 * @since 2019-03-22
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseDto getLogin(@RequestBody String username, String password){
        return ResultUtil.getResult(userService.doLogin(username, password));
    }
}