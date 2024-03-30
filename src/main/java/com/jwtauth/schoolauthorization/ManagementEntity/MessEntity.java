package com.jwtauth.schoolauthorization.ManagementEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessEntity {
    private Integer id;
    private String name;
    private String contactNumber;
    private String messType;
    private String location;
}
