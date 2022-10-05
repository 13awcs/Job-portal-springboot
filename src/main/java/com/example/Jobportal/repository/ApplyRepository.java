package com.example.Jobportal.repository;

import com.example.Jobportal.model.Apply;
import com.example.Jobportal.model.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, CompositeKey> {

    @Query(value = "select * from apply p order by p.apply_date DESC ",nativeQuery = true)
    List<Apply> getNewestApply();


    @Query(value = "select * from apply p where p.status like :status", nativeQuery = true)
    List<Apply> searchApplyByStatus(String status);
}
