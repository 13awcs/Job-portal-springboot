package com.example.Jobportal.service.serviceImpl;

import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;
import com.example.Jobportal.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    @Override
    public Job createJob(JobInputDto jobInputDto) {
        return null;
    }

    @Override
    public Job editJob(Long id) {
        return null;
    }

    @Override
    public Job deleteJob(Long id) {
        return null;
    }

    @Override
    public List<JobOutputDto> findJobByCategory(String category) {
        return null;
    }

    @Override
    public List<JobOutputDto> sortJobByDate() {
        return null;
    }
}
