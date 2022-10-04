package com.example.Jobportal.controller;

import com.example.Jobportal.common.ResponseObject;
import com.example.Jobportal.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyController {

    @Autowired
    ApplyRepository applyRepository;

    @GetMapping("/applies")
    public ResponseEntity<ResponseObject> getAllApply(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(applyRepository.findAll()));
    }
}
