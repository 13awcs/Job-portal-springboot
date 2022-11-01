package com.example.Jobportal.repository;

import com.example.Jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    @Query(value = "select * from job j where j.recruiter_id = :id order by j.create_at desc ",nativeQuery = true)
    List<Job> sortJobsByDate(Long id);

}
