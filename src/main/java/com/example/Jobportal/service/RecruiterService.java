package com.example.Jobportal.service;

import com.example.Jobportal.dto.LoginDto;
import com.example.Jobportal.dto.RegisterDto;
import com.example.Jobportal.dto.inputDto.RecruiterInput;
import com.example.Jobportal.model.Recruiter;

public interface RecruiterService {
    Recruiter registerRecruiter(RegisterDto registerDto);
    Recruiter loadRecruiterByUsername(String username);
    Recruiter editProfile(Long id, RecruiterInput recruiterInput);
}
