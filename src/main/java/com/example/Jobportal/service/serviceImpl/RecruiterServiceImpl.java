package com.example.Jobportal.service.serviceImpl;

import com.example.Jobportal.dto.LoginDto;
import com.example.Jobportal.dto.RegisterDto;
import com.example.Jobportal.model.Recruiter;
import com.example.Jobportal.repository.RecruiterRepository;
import com.example.Jobportal.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    RecruiterRepository recruiterRepository;

    PasswordEncoder passwordEncoder;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Recruiter registerRecruiter(RegisterDto registerDto) {
        Recruiter recruiter = new Recruiter();
        recruiter.setUsername(registerDto.getUsername());
        recruiter.setEmail(registerDto.getEmail());
        recruiter.setUsername(registerDto.getUsername());
        recruiter.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        return recruiterRepository.save(recruiter);
    }

    @Override
    public Recruiter loadRecruiterByUsername(String username) {
        Recruiter recruiter = recruiterRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User not found with username : "+username));
        return new Recruiter(recruiter.getId(),recruiter.getName(),recruiter.getDob(),recruiter.getAddress(),recruiter.getPhone(),recruiter.getEmail(),
                recruiter.getAvatar(),recruiter.getCompanyName(),recruiter.getUsername(),recruiter.getPassword());
//        Recruiter recruiter = recruiterRepository.

    }
}
