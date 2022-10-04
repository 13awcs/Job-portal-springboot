package com.example.Jobportal.controller;


import com.example.Jobportal.common.ResponseObject;
import com.example.Jobportal.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping("/candidates")
    public ResponseEntity<ResponseObject> getAllCandidate(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(candidateRepository.findAll()));
    }
}
