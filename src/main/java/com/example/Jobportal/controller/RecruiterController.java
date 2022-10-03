package com.example.Jobportal.controller;

import com.example.Jobportal.dto.LoginDto;
import com.example.Jobportal.dto.RegisterDto;
import com.example.Jobportal.model.Recruiter;
import com.example.Jobportal.repository.RecruiterRepository;
import com.example.Jobportal.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RecruiterController{
    @Autowired
    RecruiterService recruiterService;

    @Autowired
    RecruiterRepository recruiterRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        if(recruiterRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is already taken !", HttpStatus.BAD_REQUEST);
        }
        if(recruiterRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Email is already taken !", HttpStatus.BAD_REQUEST);
        }
        recruiterService.registerRecruiter(registerDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        List<Recruiter> recruiters = recruiterRepository.findAll();
        for (Recruiter recruiter : recruiters) {
            if (recruiter.getUsername().equalsIgnoreCase(loginDto.getUsername()) && recruiter.getPassword().equalsIgnoreCase(loginDto.getPassword())) {
                recruiterService.loadRecruiterByUsername(loginDto.getUsername());
                return new ResponseEntity<>("Login succsessfully !", HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("Username or password is wrong !", HttpStatus.BAD_REQUEST);

    }

}
