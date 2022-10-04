package com.example.Jobportal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
    private Date deadline;

    @Column(name = "description")
    private String description;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;


    @OneToMany(mappedBy = "jobApply",cascade = CascadeType.ALL)
    @JsonManagedReference
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Apply> applies;
}
