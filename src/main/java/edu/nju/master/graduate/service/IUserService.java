package edu.nju.master.graduate.service;

import edu.nju.master.graduate.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Daniel
 * @since 2019-03-22
 */
public interface IUserService {

    User doLogin(String username, String password);

}
