package africa.semicolon.employeemanagement.data.repository;

import africa.semicolon.employeemanagement.data.dto.EmployeeDto;
import africa.semicolon.employeemanagement.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    Employee updateEmployee(Long employeeId, EmployeeDto employeeDto);
    Optional<Employee> findByEmail(String email);
    void deleteByEmail(String email);
    void deleteById(Long id);

//    void deleteById(Employee employee);
}
