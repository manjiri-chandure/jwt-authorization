package com.jwtauth.schoolauthorization.ManagementEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HostelEntity {
    private Integer id;
    private String name;
    private String hostelType;
    private String location;
}
