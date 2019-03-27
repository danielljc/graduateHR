package edu.nju.master.graduate.controller;


import edu.nju.master.graduate.dto.AdminUserAddReqDto;
import edu.nju.master.graduate.dto.LoginInReqDto;
import edu.nju.master.graduate.dto.ResponseDto;
import edu.nju.master.graduate.entity.User;
import edu.nju.master.graduate.exception.BusinessException;
import edu.nju.master.graduate.service.IUserService;
import edu.nju.master.graduate.utils.DictionaryConst;
import edu.nju.master.graduate.utils.PasswordHelper;
import edu.nju.master.graduate.utils.ResultUtil;
import edu.nju.master.graduate.utils.ValidUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseDto login(@Valid @RequestBody LoginInReqDto dto, BindingResult result, HttpSession session) {
        ValidUtil.judgeResult(result);
        User user = userService.findUserByUsername(dto.getUsername());
        if (user == null || user.getStatus() == 2) {
            throw new BusinessException(DictionaryConst.RESULT_CODE.FAIL, "用户不存在");
        }
        if (user.getStatus() == 1) {
            throw new BusinessException(DictionaryConst.RESULT_CODE.FAIL, "用户已被锁定");
        }
        String password = PasswordHelper.encryptPassword(dto.getUsername(), dto.getPassword());
        if (!password.equals(user.getPassword())) {
            throw new BusinessException(DictionaryConst.RESULT_CODE.FAIL, "用户名或密码错误");
        }
        // 通过shiro的验证权限
        Subject sub = SecurityUtils.getSubject();
        UsernamePasswordToken shiroToken = new UsernamePasswordToken(dto.getUsername(), password);
        sub.login(shiroToken);
        return ResultUtil.getResult("登录成功！");
    }

    @ApiOperation("后台新增用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "X-Token", dataType = "String", required = true, value = "Token值", defaultValue = "")
    })
    @PostMapping("/add")
    @ResponseBody
    public ResponseDto add(@Valid  @RequestBody AdminUserAddReqDto dto, BindingResult result) {
        ValidUtil.judgeResult(result);
        User existUser = userService.findUserByUsername(dto.getUsername());
        if(existUser!=null)
            throw new BusinessException(DictionaryConst.RESULT_CODE.FAIL, "用户名已被占用");
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        // 设置用户默认密码
        user.setPassword("123456");
        // 用户状态正常
        user.setStatus(0);
        // 后台管理人员
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        } else {
            user.setRole(0);
        }
        PasswordHelper.encryptPassword(user);
        userService.insertUser(user);
        existUser = userService.findUserByUsername(dto.getUsername());
        return ResultUtil.getResult(existUser);
    }
}