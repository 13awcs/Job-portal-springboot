package com.example.Jobportal.dto.Mapping;

import com.example.Jobportal.dto.inputDto.JobEditDto;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobMapping {

    public static JobOutputDto jobInputToOutput(Job job){
        JobOutputDto jobOutputDto = new JobOutputDto();
        jobOutputDto.setId(job.getId());
        jobOutputDto.setTitle(job.getTitle());
        jobOutputDto.setCategory(job.getCategory());
        jobOutputDto.setAmount(job.getAmount());
        jobOutputDto.setType(job.getType());
        jobOutputDto.setLevel(job.getLevel());
        jobOutputDto.setDeadline(job.getDeadline());
        jobOutputDto.setDescription(job.getDescription());
        jobOutputDto.setCompanyName(job.getCompanyName());
        jobOutputDto.setSalary(job.getSalary());
        jobOutputDto.setLocation(job.getLocation());
        jobOutputDto.setStatus(job.getStatus());
        jobOutputDto.setCreateAt(job.getCreateAt());

        return jobOutputDto;

    }

    public static List<JobOutputDto> toJobOutPutList(List<Job> jobs){
        List<JobOutputDto> jobOutputDtos = new ArrayList<>();
        for(Job job : jobs){
            jobOutputDtos.add(jobInputToOutput(job));
        }
        return jobOutputDtos;
    }

    public static Job toJob(JobInputDto jobInputDto){
        Job job = new Job();
        job.setTitle(jobInputDto.getTitle());
        job.setCategory(jobInputDto.getCategory());
        job.setAmount(jobInputDto.getAmount());
        job.setType(jobInputDto.getType());
        job.setLevel(jobInputDto.getLevel());
        job.setDeadline(jobInputDto.getDeadline());
        job.setDescription(jobInputDto.getDescription());
        job.setCompanyName(jobInputDto.getCompanyName());
        job.setSalary(jobInputDto.getSalary());
        job.setLocation(jobInputDto.getLocation());
        job.setStatus(jobInputDto.getStatus());
        job.setCreateAt(jobInputDto.getCreateAt());

        return job;

    }
    public static Job toEditJob(JobEditDto jobInputDto){
        Job job = new Job();
        job.setTitle(jobInputDto.getTitle());
        job.setCategory(jobInputDto.getCategory());
        job.setAmount(jobInputDto.getAmount());
        job.setType(jobInputDto.getType());
        job.setLevel(jobInputDto.getLevel());
        job.setDeadline(jobInputDto.getDeadline());
        job.setDescription(jobInputDto.getDescription());
        job.setCompanyName(jobInputDto.getCompanyName());
        job.setSalary(jobInputDto.getSalary());
        job.setLocation(jobInputDto.getLocation());
        job.setStatus(jobInputDto.getStatus());
        job.setCreateAt(jobInputDto.getCreateAt());

        return job;

    }



}
