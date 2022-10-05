package com.example.Jobportal.service;

import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;

import java.util.List;

public interface JobService {
    Job createJob(JobInputDto jobInputDto);
    Job editJob(Long id);
    Job deleteJob(Long id);
    List<JobOutputDto> findJobByCategory(String category);
    List<JobOutputDto> sortJobByDate();
}
