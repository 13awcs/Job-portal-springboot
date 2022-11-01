package com.example.Jobportal.controller;


import com.example.Jobportal.common.ResponseObject;
import com.example.Jobportal.model.Candidate;
import com.example.Jobportal.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping("/candidates")
    public List<Candidate> getAllCandidate(){
        return candidateRepository.findAll();
    }

    @GetMapping("/candidate/{id}")
    public Optional<Candidate> getCandidateById(@RequestParam Long id){
        return candidateRepository.findById(id);
    }

    @GetMapping("/candidates/search")
    public ResponseEntity<List<Candidate>> searchCandidate(@RequestParam String keyword){
        //List<Candidate> candidates = candidateRepository.searchCandidate(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(candidateRepository.searchCandidate(keyword));
//        if(candidates.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("No result found"));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(candidates));
    }
}
