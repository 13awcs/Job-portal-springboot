package com.example.Jobportal.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "candidate")
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "major")
    private String major;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "category")
    private String category;

    @Column(name = "level")
    private String level;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "link_cv")
    private String linkCv;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "candidateApply",cascade = CascadeType.ALL)
    @JsonBackReference
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Apply> applies;
}
