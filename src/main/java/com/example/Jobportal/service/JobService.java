package com.example.Jobportal.service;

import com.example.Jobportal.dto.inputDto.JobEditDto;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job createJob(JobInputDto jobInputDto);
//    Job editJob(Long id, Job job);
    Job editJob(Long id, JobEditDto jobEditDto);
    String deleteJob(Long id);
    List<JobOutputDto> findJobByCategory(String category);
    List<JobOutputDto> sortJobByDate(Long id);
    Job getDetailJob(Long id);
}
