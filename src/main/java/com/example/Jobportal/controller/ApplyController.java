package com.example.Jobportal.controller;

import com.example.Jobportal.common.ResponseObject;
import com.example.Jobportal.model.Apply;
import com.example.Jobportal.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplyController {

    @Autowired
    ApplyRepository applyRepository;

    @GetMapping("/applies")
    public ResponseEntity<ResponseObject> getAllApply(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(applyRepository.findAll()));
    }

    @GetMapping("/applies/newest")
    public ResponseEntity<ResponseObject> getNewestApply(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(applyRepository.getNewestApply()));
    }

    @GetMapping("/applies/search")
    public ResponseEntity<ResponseObject> searchApplyByStatus(@RequestParam String status){
        List<Apply> applyList = applyRepository.searchApplyByStatus(status);
        if(applyList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("No result found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(applyList));
    }
}
