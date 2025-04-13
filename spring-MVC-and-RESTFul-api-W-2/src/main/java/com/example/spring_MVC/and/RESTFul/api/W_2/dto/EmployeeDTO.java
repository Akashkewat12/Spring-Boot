package com.example.spring_MVC.and.RESTFul.api.W_2.dto;

import com.example.spring_MVC.and.RESTFul.api.W_2.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of emp can't be blank")
    @Size(min = 3, max = 10 , message = "Num of char should range b/w 3-10")
    private String name;

    @NotBlank(message = "email can't be null")
    @Email(message = "email should be valid  email")
    private String email;

    @NotNull(message = "age of emp can't be blank")
    @Max(value = 80 , message = "Age can't be greater 80 ")
    @Min(value = 18, message = "age of emp can't be less then 18")
    @Positive(message = "Enter only positive no")
    private Integer age;

    @NotBlank(message = "Role  of emp can't be blank")
   // @Pattern(regexp = "^(ADMIN|USER)$", message = "role of emp can be user or admin")
    @EmployeeRoleValidation
    private String role; //ADMIN, USER

    @NotNull(message = "salary of emp can't be empty")
    @Positive(message = "salary of emp should be positive")
    @Digits(integer = 6, fraction = 2,message = "salary can be in the form of XXXXXX.YY")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50",message = "salary should be greater then 100.50  ")
    private Double salary;

    @PastOrPresent(message = "date of joining field in emp can't be future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

}


// f @Valid anotation work on EmpDto