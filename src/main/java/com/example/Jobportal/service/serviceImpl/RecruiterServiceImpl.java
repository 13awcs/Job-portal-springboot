package com.example.Jobportal.service.serviceImpl;

import com.example.Jobportal.dto.RegisterDto;
import com.example.Jobportal.dto.inputDto.RecruiterInput;
import com.example.Jobportal.model.Recruiter;
import com.example.Jobportal.repository.RecruiterRepository;
import com.example.Jobportal.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
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
        //recruiter.setPassword(this.passwordEncoder.encode(registerDto.getPassword()));
        recruiter.setPassword(registerDto.getPassword());
        return recruiterRepository.save(recruiter);
    }

    @Override
    public Recruiter loadRecruiterByUsername(String username) {
        Recruiter recruiter = recruiterRepository.findByUsername(username);
        return recruiter;
//        Recruiter recruiter = recruiterRepository.

    }

    @Override
    public Recruiter editProfile(Long id, RecruiterInput recruiterInput) {
        Recruiter recruiter = new Recruiter();
        recruiter.setAvatar(recruiterInput.getAvatar());
        recruiter.setEmail(recruiterInput.getEmail());
        recruiter.setPhone(recruiterInput.getPhone());
        recruiter.setCompanyName(recruiterInput.getCompanyName());
        recruiter.setId(id);
        return recruiterRepository.save(recruiter);
    }
}
