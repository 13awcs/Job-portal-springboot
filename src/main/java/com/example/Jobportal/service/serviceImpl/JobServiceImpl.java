package com.example.Jobportal.service.serviceImpl;

import com.example.Jobportal.common.exceptions.ResourceNotFoundException;
import com.example.Jobportal.dto.Mapping.JobMapping;
import com.example.Jobportal.dto.inputDto.JobEditDto;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;
import com.example.Jobportal.model.Recruiter;
import com.example.Jobportal.repository.ApplyRepository;
import com.example.Jobportal.repository.JobRepository;
import com.example.Jobportal.repository.RecruiterRepository;
import com.example.Jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RecruiterRepository recruiterRepository;

    @Autowired
    ApplyRepository applyRepository;

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
    public Job editJob(Long id, JobEditDto jobEditDto) {
        Job jobFromDb = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found job id "+id));

        Job jobEdit = new Job();
        jobEdit.setId(id);
        jobEdit.setTitle(jobEditDto.getTitle());
        jobEdit.setCategory(jobFromDb.getCategory());
        jobEdit.setAmount(jobFromDb.getAmount());
        jobEdit.setType(jobFromDb.getType());
        jobEdit.setLevel(jobEditDto.getLevel());
        jobEdit.setDeadline(jobFromDb.getDeadline());
        jobEdit.setDescription(jobFromDb.getDescription());
        jobEdit.setCompanyName(jobFromDb.getCompanyName());
        jobEdit.setSalary(jobEditDto.getSalary());
        jobEdit.setLocation(jobEditDto.getLocation());
        jobEdit.setStatus(jobFromDb.getStatus());
        jobEdit.setCreateAt(jobFromDb.getCreateAt());
        jobEdit.setRecruiter(jobFromDb.getRecruiter());

        return jobRepository.save(jobEdit);
    }
//    public Job editJob(Long id, Job job) {
//
//        Job jobFromDB = jobRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found job id "+id));
//        jobFromDB.setTitle(job.getTitle());
//        jobFromDB.setCategory(job.getCategory());
//        jobFromDB.setAmount(job.getAmount());
//        jobFromDB.setType(job.getType());
//        jobFromDB.setLevel(job.getLevel());
//        jobFromDB.setDeadline(job.getDeadline());
//        jobFromDB.setDescription(job.getDescription());
//        jobFromDB.setCompanyName(job.getCompanyName());
//        jobFromDB.setSalary(job.getSalary());
//        jobFromDB.setLocation(job.getLocation());
//        jobFromDB.setStatus(job.getStatus());
//        jobFromDB.setCreateAt(job.getCreateAt());
//        jobFromDB.setRecruiter(job.getRecruiter());
//        jobFromDB.setId(id);
//
//        //return JobMapping.jobInputToOutput(jobRepository.save(job));
//        return jobRepository.save(jobFromDB);
//    }

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
    public List<JobOutputDto> sortJobByDate(Long id) {

        List<JobOutputDto> jobOutputDtos = new ArrayList<>();

        List<Job> jobs = jobRepository.sortJobsByDate(id);
        if(jobs.isEmpty()){
            new ResourceNotFoundException("No result !");
        }
        for(Job job : jobs){
            int applyAmount = applyRepository.countApply(job.getId());
            JobOutputDto jobOutputDto = JobMapping.jobInputToOutput(job);
            jobOutputDto.setApplyAmount(applyAmount);
            jobOutputDtos.add(jobOutputDto);
        }

        return jobOutputDtos;
    }

    @Override
    public Job getDetailJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow();
//        JobOutputDto jobOutputDto = JobMapping.jobInputToOutput(job);
//
//        int applyAmount = applyRepository.countApply(job.getId());
//        jobOutputDto.setApplyAmount(applyAmount);

        return job;
    }
}
