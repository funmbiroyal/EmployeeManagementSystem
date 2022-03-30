package africa.semicolon.employeemanagement.data.dto;

import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String departmentName;
    private Integer employeeSalary;
    private Boolean isSuspended;
}
