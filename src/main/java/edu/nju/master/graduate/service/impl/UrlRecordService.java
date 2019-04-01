package edu.nju.master.graduate.service.impl;

import edu.nju.master.graduate.dao.IUrlRecordDao;
import edu.nju.master.graduate.entity.UrlRecord;
import edu.nju.master.graduate.exception.BusinessException;
import edu.nju.master.graduate.service.IUrlRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Daniel
 * @title: UrlRecordService
 * @description: 记录 服务实现类
 * @since 2019-03-30 15:39
 */
@Service
public class UrlRecordService implements IUrlRecordService {

    @Autowired
    IUrlRecordDao urlRecordDao;

    @Override
    public List<UrlRecord> findByUserId(Integer userId) {
        return urlRecordDao.findByUserId(userId);
    }

    @Override
    public UrlRecord findByRecordId(Integer recordId) {
        return urlRecordDao.findById(recordId)
                .orElseThrow(() -> new BusinessException("无此记录"));
    }

    @Override
    public void deleteByRecordId(Integer recordId) {
        urlRecordDao.deleteById(recordId);
    }

    @Override
    public List<UrlRecord> searchByKeyword(String keyword) {
        return urlRecordDao.findByWebsiteNameContainingOrUrlContaining(keyword, keyword);
    }

    @Override
    public void updateRecord(List<UrlRecord> urlRecordList) {

    }
}
