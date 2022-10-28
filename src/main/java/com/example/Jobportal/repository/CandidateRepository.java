package com.example.Jobportal.repository;

import com.example.Jobportal.model.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    @Query(value = "select * from candidate c where c.name like %:keyword% or c.category like %:keyword% or c.address like %:keyword%",nativeQuery = true)
    List<Candidate> searchCandidate(String keyword);

}
