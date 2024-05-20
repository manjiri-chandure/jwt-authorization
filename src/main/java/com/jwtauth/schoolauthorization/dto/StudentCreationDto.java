package com.jwtauth.schoolauthorization.dto;

import com.jwtauth.schoolauthorization.KafkaValidationgroup;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentCreationDto {
    @NotBlank(message = "Full name can't be blank & should be space between firstname & lastname", groups = KafkaValidationgroup.class)
    private String fullName;
    @NotNull(message = "gender can't be null", groups = KafkaValidationgroup.class)
    @Pattern(regexp = "male|female|Male|Female", message = "gender should be male/Male or female/Female", groups = KafkaValidationgroup.class)
    private String gender;
    @Min(value = 10, message = "age must be greater or equals to 10 & less than 26", groups = KafkaValidationgroup.class)
    @Max(value = 25, message = "age must be less or equals to 25 & greater than 9", groups = KafkaValidationgroup.class)
    @NotNull(message = "age can't be null", groups = KafkaValidationgroup.class)
    private Integer age;
}
