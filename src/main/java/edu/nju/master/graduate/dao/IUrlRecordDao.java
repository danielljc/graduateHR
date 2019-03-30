package edu.nju.master.graduate.dao;

import edu.nju.master.graduate.entity.UrlRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUrlRecordDao extends JpaRepository<UrlRecord,Integer> {

    List<UrlRecord> findByUserId(Integer userId);

    Optional<UrlRecord> findById(Integer id);

    List<UrlRecord> findByWebsiteNameContainingAndUrlContaining(String keyword1, String keyword2);
}
