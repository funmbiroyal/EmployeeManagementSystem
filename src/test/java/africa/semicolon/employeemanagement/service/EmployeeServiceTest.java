package africa.semicolon.employeemanagement.service;

import africa.semicolon.employeemanagement.data.dto.EmployeeDto;
import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts={"/db/insert.sql"})
class EmployeeServiceTest {

    @Autowired
    @Qualifier("initialService")
    EmployeeService employeeService;

    EmployeeDto employeeDtoOne;
    EmployeeDto employeeDtoTwo;

    @BeforeEach
    void setUp() {
        employeeDtoOne = new EmployeeDto();
        employeeDtoOne.setFirstName("Jada");
        employeeDtoOne.setLastName("Godson");
        employeeDtoOne.setEmail("jada@gmail.com");
        employeeDtoOne.setAge(1);

        employeeDtoTwo = new EmployeeDto();
        employeeDtoTwo.setFirstName("Jahzeal");
        employeeDtoTwo.setLastName("Chiemerie");
        employeeDtoTwo.setEmail("jahzeal@gmail.com");
        employeeDtoTwo.setAge(3);

    }


    @Test
    @DisplayName("Employee can create an account")
    void createEmployee() throws EmployeeAlreadyExistsException {
        EmployeeDto employeeDtoThree = new EmployeeDto();
        employeeDtoThree.setFirstName("Love");
        employeeDtoThree.setLastName("Godson");
        employeeDtoThree.setEmail("love@gmail.com");
        employeeDtoThree.setAge(25);

        assertThat(employeeDtoThree).isNotNull();

        Employee employee = employeeService.createEmployee(employeeDtoThree);
        log.info("Employee created is :: {}", employee);

        assertThat(employee.getFirstName()).isEqualTo("Love");
        assertThat(employee.getLastName()).isEqualTo("Godson");
        assertThat(employee.getEmail()).isEqualTo("love@gmail.com");
        assertThat(employee.getAge()).isEqualTo(25);

    }

    @Test
    void getAllEmployees() {
        employeeService.getAllEmployees();
    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteAllEmployees() {
        employeeService.deleteAllEmployees();
       assertEquals(0,employeeService.findAllEmployee().size());
    }

    @Test
    void deleteEmployeeById() throws EmployeeDoesNotExistsException {
        Employee employee = new Employee();

        employee = employeeService.findEmployeeByEmail("precious@gmail.com").orElse(null);

        assert employee != null;
        assertThat(employee.getFirstName()).isEqualTo("Precious");
        assertThat(employee.getLastName()).isEqualTo("Lois");
        assertThat(employee.getId()).isEqualTo(1L);

        employeeService.deleteEmployeeById(1L);
        assertThrows(EmployeeDoesNotExistsException.class, ()->employeeService.findEmployeeByEmail("precious@gmail.com"));

        Optional<Employee> emp = employeeService.findEmployeeById(1L);
        assertEquals(true, employeeService.findEmployeeById(1L).equals("null"));

    }

    @Test
    void findEmployeeByEmail() throws EmployeeDoesNotExistsException {
        Employee employee = new Employee();

        employee = employeeService.findEmployeeByEmail("precious@gmail.com").orElse(null);

        assert employee != null;
        assertThat(employee.getFirstName()).isEqualTo("Precious");
        assertThat(employee.getLastName()).isEqualTo("Lois");
    }
}