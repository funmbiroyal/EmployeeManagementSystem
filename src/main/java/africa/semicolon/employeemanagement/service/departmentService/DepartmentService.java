package africa.semicolon.employeemanagement.service.departmentService;

import africa.semicolon.employeemanagement.data.dto.DepartmentRequest;
import africa.semicolon.employeemanagement.data.dto.DepartmentResponse;
import africa.semicolon.employeemanagement.data.model.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest request);
    Department updateDepartment(String departmentId);
    String deleteDepartmentByDepartmentId(String departmentId);
    String deleteDepartmentByDepartmentName(String departmentName);
    List<Department> findAllDepartment();
    Department findDepartmentById(String departmentId);
    Department findDepartmentByName(String departmentName);

}
