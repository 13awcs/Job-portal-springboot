package com.example.Jobportal.dto.inputDto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecruiterInput {
    private Long id;
    private String avatar;
    private String companyName;
    private String email;
    private String phone;
}
