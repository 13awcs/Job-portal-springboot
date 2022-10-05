package com.example.Jobportal.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apply")
public class Apply {

    @EmbeddedId
    private CompositeKey id;

    @ManyToOne
    @JsonManagedReference
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id")
    private Candidate candidateApply;

    @ManyToOne
    @JsonManagedReference
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job jobApply;

    @Column(name = "status")
    private String status;

    @Column(name = "apply_date")
    private LocalDate applyDate;

    @Override
    public String toString() {
        return "Apply{" +
                "candidateApply=" + candidateApply +
                ", jobApply=" + jobApply +
                ", status='" + status + '\'' +
                '}';
    }
}
