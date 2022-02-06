package africa.semicolon.employeemanagement.web.controller;

import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.data.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts="/db/insert.sql")
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test employee api")
    void employeeApiTest() throws Exception {
        mockMvc.perform(get("/api/employee/")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void createNewEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("Toye");
        employee.setLastName("David");
        employee.setEmail("lalaland@gmail.com");
        employee.setAge(28);

        String requestBody = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/api/employee/create")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().is(200))
                .andDo(print());

    }

    @Test
    void getAllEmployeesRecord() {
    }

    @Test
    void updateEmployeeRecord() {
    }

    @Test
    void deleteEmployeeRecord() {
    }

    @Test
    void getSingleEmployee() {
    }
}