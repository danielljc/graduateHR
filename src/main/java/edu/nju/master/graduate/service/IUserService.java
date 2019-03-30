package edu.nju.master.graduate.service;

import edu.nju.master.graduate.entity.User;

import java.util.List;

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

    User findUserByUsername(String username);

    User insertUser(User user);

    User findUserByPhone(String phoneNumber);

    List<User> findByRole(Integer role);

}
