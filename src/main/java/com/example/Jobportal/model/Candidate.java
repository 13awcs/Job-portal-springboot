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

    @Column(name = "c_name")
    private String name;

    @Column(name = "major")
    private String major;

    @Column(name = "dOb")
    private Date dOb;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "c_level")
    private String level;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "link_cv")
    private String linkCv;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "candidateApply",cascade = CascadeType.ALL)
    @JsonManagedReference
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Apply> applies;
}
