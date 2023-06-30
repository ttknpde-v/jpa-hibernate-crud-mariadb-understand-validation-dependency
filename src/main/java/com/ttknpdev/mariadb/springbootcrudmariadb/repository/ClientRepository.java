package com.ttknpdev.mariadb.springbootcrudmariadb.repository;

import com.ttknpdev.mariadb.springbootcrudmariadb.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,String> {
        @Query(value = "select * from company.client where `state` = :state ", nativeQuery = true)
        List<Client> readsAllByStatus(@Param("state") Integer state);
        @Transactional
        @Modifying(clearAutomatically = true)
        @Query(value = "delete from company.client where `state` = :state" , nativeQuery = true)
        int deleteAllByState(@Param("state") Integer state);
}
