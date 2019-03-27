package edu.nju.master.graduate.utils;

import edu.nju.master.graduate.entity.UrlRecord;
import edu.nju.master.graduate.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Daniel
 * @title: FileUtils
 * @description: 文件工具类
 * @date 2019-03-26 19:38
 */
public class FileUtils {

    public static String basePath = "D:/upload";

    /**
     * 解析前端上传的文件，返回网址列表
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static List<UrlRecord> getUrlsFromMultipartFile(MultipartFile multipartFile) throws IOException {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名前缀，防止生成的临时文件重复
        final File tempFile = File.createTempFile(UUID.randomUUID() + fileName, suffix);
        // MultipartFile to File
        multipartFile.transferTo(tempFile);

        // 按行读取文件内容，并映射到UrlRecord对象
        List<String> urls = org.apache.commons.io.FileUtils.readLines(tempFile, "UTF-8");
        List<UrlRecord> records = urls.parallelStream()
                .map(url->{
                    UrlRecord urlRecord = new UrlRecord();
                    urlRecord.setUrl(url);
                    return urlRecord;
                }).collect(Collectors.toList());

        // 方法结束时，删除临时文件
        deleteFile(tempFile);
        return records;
    }

    private static void deleteFile(File... files) {
        for (File file : files)
            if (file.exists())
                file.delete();
    }


    /**
     * 在basePath下保存上传的文件夹
     * @param basePath
     * @param files
     */
    public static String saveMultiFile(String basePath, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            throw new BusinessException("找不到此文件夹");
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            String filePath = basePath + "/" + file.getOriginalFilename();
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return "保存成功";
    }

    /**
     * 确保目录存在，不存在则创建
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }
}
