package com.example.Jobportal.repository;

import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long> {

    @Query(value = "select * from job j order by j.create_at desc ",nativeQuery = true)
    List<JobOutputDto> sortJobByDate();
}
