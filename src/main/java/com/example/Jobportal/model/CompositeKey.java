package com.example.Jobportal.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompositeKey implements Serializable {
    @Column(name = "candidate_id")
    Long candidateId;

    @Column(name = "job_id")
    Long jobId;



}
