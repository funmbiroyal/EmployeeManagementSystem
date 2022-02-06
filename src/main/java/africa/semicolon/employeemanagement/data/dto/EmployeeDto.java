package africa.semicolon.employeemanagement.data.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
