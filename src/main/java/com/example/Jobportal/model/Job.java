package com.example.Jobportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "amount")
    private int amount;

    @Column(name = "type")
    private String type;

    @Column(name = "level")
    private String level;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private LocalDate createAt;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;


    @OneToMany(mappedBy = "jobApply",cascade = CascadeType.ALL)
    @JsonBackReference
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Apply> applies;
}
