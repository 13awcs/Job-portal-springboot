package com.example.Jobportal.controller;


import com.example.Jobportal.common.ResponseObject;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;
import com.example.Jobportal.repository.JobRepository;
import com.example.Jobportal.service.JobService;
import com.example.Jobportal.service.serviceImpl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class JobController{

    @Autowired
    JobService jobService;

    @Autowired
    JobRepository jobRepository;

//    @GetMapping("/jobs")
//    public ResponseEntity<List<JobOutputDto>> getAllJobs(){
//        return ResponseEntity.status(HttpStatus.OK).body(jobService.sortJobByDate());
//
//    }
    @GetMapping("/jobs/recruiter/{id}")
    public List<Job> getAllJobsByRecruiterId(@PathVariable Long id){
        return jobService.sortJobByDate(id);

    }

    @GetMapping("/jobs")
    public List<Job> getAllJobs(){
        return jobRepository.findAll();

    }

    @PostMapping("/jobs/create")
    public ResponseEntity<ResponseObject> createJob(@RequestBody JobInputDto jobInputDto){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Create job successfully !",jobService.createJob(jobInputDto)));

    }

    @PutMapping("/jobs/edit/{id}")
    public ResponseEntity<ResponseObject> editJob(@PathVariable Long id,@RequestBody JobInputDto jobInputDto){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Edit job successfully !",jobService.editJob(id,jobInputDto)));
    }

    @DeleteMapping("/jobs/delete/{id}")
    public ResponseEntity<ResponseObject> deleteJob(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(jobService.deleteJob(id)));
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Optional<Job>> getJobById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getDetailJob(id));
    }

}
