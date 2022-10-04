package com.example.Jobportal.repository;

import com.example.Jobportal.model.Apply;
import com.example.Jobportal.model.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, CompositeKey> {
}
