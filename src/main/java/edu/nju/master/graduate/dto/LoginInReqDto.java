package edu.nju.master.graduate.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * request DTO 封装请求数据，使用数据校验框架。
 * @author roshen
 *
 */
public class LoginInReqDto {

	@NotNull(message = "用户名不能为空")
	@ApiModelProperty(value = "用户名",required = true,dataType = "String",example = "admin")
	private String username;
	//
	@ApiModelProperty(value = "密码",required = true,dataType = "String",example = "123456")
	@NotNull(message = "密码不能为空")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
