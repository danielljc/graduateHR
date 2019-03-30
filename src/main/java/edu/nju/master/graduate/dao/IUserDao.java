package edu.nju.master.graduate.dao;

import edu.nju.master.graduate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserDao extends JpaRepository<User,Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByPhoneNumber(String phoneNumber);

    List<User> findByRole(Integer role);

}
