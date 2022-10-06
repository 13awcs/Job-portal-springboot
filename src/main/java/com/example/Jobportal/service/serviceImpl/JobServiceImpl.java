package com.example.Jobportal.service.serviceImpl;

import com.example.Jobportal.common.exceptions.ResourceNotFoundException;
import com.example.Jobportal.dto.Mapping.JobMapping;
import com.example.Jobportal.dto.inputDto.JobEditDto;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;
import com.example.Jobportal.model.Recruiter;
import com.example.Jobportal.repository.JobRepository;
import com.example.Jobportal.repository.RecruiterRepository;
import com.example.Jobportal.service.JobService;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.event.spi.PreCollectionUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RecruiterRepository recruiterRepository;

    @Override
    public Job createJob(JobInputDto jobInputDto) {

        Recruiter recruiter = recruiterRepository.findById(jobInputDto.getRecruiterId())
                .orElseThrow(()-> new ResourceNotFoundException("Not found recruiter id "+jobInputDto.getRecruiterId()));
        Job job = JobMapping.toJob(jobInputDto);
        job.setRecruiter(recruiter);
        return jobRepository.save(job);

    }

    @Override
    @Transactional
    public JobOutputDto editJob(Long id, JobInputDto jobInputDto) {
        Job job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found job id "+id));
        job = JobMapping.toJob(jobInputDto);
        job.setId(id);
        return JobMapping.jobInputToOutput(jobRepository.save(job));
    }

    @Override
    public String deleteJob(Long id) {

        Job job = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found job id "+id));
        jobRepository.deleteById(id);
        return "Deleted successfully job id "+id;
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
