package edu.nju.master.graduate.utils;

import edu.nju.master.graduate.Enum.UserRoleEnums;
import edu.nju.master.graduate.entity.UrlRecord;
import edu.nju.master.graduate.entity.User;
import edu.nju.master.graduate.service.IUrlRecordService;
import edu.nju.master.graduate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasksUtils {

    @Autowired
    IUserService userService;

    @Autowired
    IUrlRecordService urlRecordService;

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat("HH:mm:ss");
    }

    /**
     * 第一次，延迟1秒，更新高级用户已检测的网址，第二次开始延迟一天
     */
    /*@Scheduled(initialDelay = 1000, fixedDelay = 1000 * 3600 * 24)
    public void updateUrlStatusInitially() {
        // 查询所有高级用户
        List<User> userList = userService.findByRole(UserRoleEnums.ADVANCED_USER);
        // 查询所有高级用户的历史检测网址
        List<UrlRecord> urlRecordList = new ArrayList<>();
        userList.parallelStream().forEach(u-> urlRecordList.addAll(urlRecordService.findByUserId(u.getId())));
        //更新已检测的网址状态
        urlRecordService.updateRecord(urlRecordList);
        System.out.println("Scheduling tasks initially: Update the urls that the administrator has inspected.");
        System.out.println("Scheduling tasks initially: The time now is " + dateFormat().format(new Date()));
    }*/

    /**
     * 每天凌晨0点，更新高级用户已检测的网
     * cron = "秒 分 时 * * ?"
     */
    @Scheduled(cron = "00 00 00 * * ?")
    public void updateUrlStatusDaily() {
        // 查询所有高级用户
        List<User> userList = userService.findByRole(UserRoleEnums.ADVANCED_USER);
        // 查询所有高级用户的历史检测网址
        List<UrlRecord> urlRecordList = new ArrayList<>();
        userList.parallelStream().forEach(u-> urlRecordList.addAll(urlRecordService.findByUserId(u.getId())));
        //更新已检测的网址状态
        urlRecordService.updateRecord(urlRecordList);
        System.out.println("Scheduling Tasks Daily: Update the urls that the administrator has inspected.");
        System.out.println("Scheduling Tasks Daily: The time now is " + dateFormat().format(new Date()));
    }
}
