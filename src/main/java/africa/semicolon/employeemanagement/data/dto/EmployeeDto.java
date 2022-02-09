package africa.semicolon.employeemanagement.data.dto;

import lombok.Data;


@Data
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
