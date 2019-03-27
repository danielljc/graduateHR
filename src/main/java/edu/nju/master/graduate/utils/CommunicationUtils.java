package edu.nju.master.graduate.utils;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @description: 通信工具类
 * @author: Daniel Li
 * @create: 2019-3-26
 */
public class CommunicationUtils {

    public static String getResultByUrls(List<String> urls) throws HttpServerErrorException {
        // 网址检测服务器ip地址及端口
        String url = "http://192.168.0.126:5001/";
        // Spring提供的用于访问Rest服务的客户端
        RestTemplate restTemplate = new RestTemplate();
        // POST方法传数据，并返回Json字符串
        String result = restTemplate.postForObject(url, urls, String.class);
        return result;
    }

    public static String getResultByFolderPath(String folderPath) throws HttpServerErrorException {
        // 图像批量检测服务器ip地址及端口
        String url = "http://192.168.0.126:5002/";
        // Spring提供的用于访问Rest服务的客户端
        RestTemplate restTemplate = new RestTemplate();
        // POST方法传数据，并返回Json字符串
        String result = restTemplate.postForObject(url, folderPath, String.class);
        return result;
    }
}
