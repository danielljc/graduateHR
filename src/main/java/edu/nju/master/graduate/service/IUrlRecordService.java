package edu.nju.master.graduate.service;

import edu.nju.master.graduate.entity.UrlRecord;

import java.util.List;

/**
 * <p>
 * 记录表 服务类
 * </p>
 *
 * @author Daniel
 * @since 2019-03-30
 */
public interface IUrlRecordService {

    List<UrlRecord> findByUserId(Integer userId);

    UrlRecord findByRecordId(Integer recordId);

    void deleteByRecordId(Integer recordId);

    List<UrlRecord> searchByKeyword(String keyword);

    /**
     * 未实现
     * @param urlRecordList
     */
    void updateRecord(List<UrlRecord> urlRecordList);
}
