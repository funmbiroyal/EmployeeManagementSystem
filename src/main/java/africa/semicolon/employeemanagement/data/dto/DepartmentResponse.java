package africa.semicolon.employeemanagement.data.dto;

import africa.semicolon.employeemanagement.data.model.Level;
import africa.semicolon.employeemanagement.data.model.Role;

public class DepartmentResponse {
    private Long id;
    private String fieldName;
    private String description;
    private Level employeeJobLevel;
    private Role jobRole;
}
