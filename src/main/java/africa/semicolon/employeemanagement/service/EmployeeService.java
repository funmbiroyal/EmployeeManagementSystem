package africa.semicolon.employeemanagement.service;

import africa.semicolon.employeemanagement.data.dto.EmployeeDto;
import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto) throws EmployeeAlreadyExistsException;
    List<Employee> getAllEmployees();
    Employee updateEmployee(String email, JsonPatch jsonpatch) throws EmployeeDoesNotExistsException, JsonPatchException, JsonProcessingException, EmployeeRequestException;
    void deleteAllEmployees();
    String deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeByEmail(String name) throws EmployeeDoesNotExistsException;
    List<Employee> findAllEmployee();
    Optional<Employee> findEmployeeById(Long id) throws EmployeeDoesNotExistsException;

}
