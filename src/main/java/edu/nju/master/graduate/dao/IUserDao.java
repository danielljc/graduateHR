package edu.nju.master.graduate.dao;

import edu.nju.master.graduate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User,Integer> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

}
