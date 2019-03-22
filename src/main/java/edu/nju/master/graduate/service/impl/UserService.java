package edu.nju.master.graduate.service.impl;

import edu.nju.master.graduate.dao.IUserDao;
import edu.nju.master.graduate.entity.User;
import edu.nju.master.graduate.exception.BusinessException;
import edu.nju.master.graduate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Daniel
 * @since 2019-03-22
 */
@Service
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Override
    public User doLogin(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user != null)
            return user;
        else
            throw new BusinessException("用户名/密码错误");
    }
}
