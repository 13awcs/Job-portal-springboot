package com.example.Jobportal.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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

    private String status;
}
