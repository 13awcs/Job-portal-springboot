package com.example.Jobportal.service;

import com.example.Jobportal.dto.inputDto.JobEditDto;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job createJob(JobInputDto jobInputDto);
    JobOutputDto editJob(Long id, JobInputDto jobInputDto);
    String deleteJob(Long id);
    List<JobOutputDto> findJobByCategory(String category);
    List<Job> sortJobByDate(Long id);

    Optional<Job> getDetailJob(Long id);
}
