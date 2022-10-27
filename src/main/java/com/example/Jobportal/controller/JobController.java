package com.example.Jobportal.controller;


import com.example.Jobportal.common.ResponseObject;
import com.example.Jobportal.dto.inputDto.JobInputDto;
import com.example.Jobportal.dto.outputDto.JobOutputDto;
import com.example.Jobportal.model.Job;
import com.example.Jobportal.service.JobService;
import com.example.Jobportal.service.serviceImpl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController{

    @Autowired
    JobService jobService;

    @GetMapping("/jobs")
    public List<JobOutputDto> getAllJobs(){
        return jobService.sortJobByDate();

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

}
