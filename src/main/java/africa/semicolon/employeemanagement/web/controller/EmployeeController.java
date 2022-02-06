package africa.semicolon.employeemanagement.web.controller;

import africa.semicolon.employeemanagement.data.dto.EmployeeDto;
import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.service.EmployeeService;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("create")
    public ResponseEntity<?> createNewEmployee(EmployeeDto employeeDto){
        try{
            Employee employee = employeeService.createEmployee(employeeDto);
            return ResponseEntity.ok().body(employee);
        } catch (EmployeeAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("getRecords/")
    public ResponseEntity<?> getAllEmployeesRecord(){
        try{
            List<Employee> employee = employeeService.findAllEmployee();
            return ResponseEntity.ok().body(employee);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "update/{email}", consumes = {"application/json-patch+json"})
    public ResponseEntity<?> updateEmployeeRecord(@PathVariable String email, @RequestBody JsonPatch jsonpatch){
        try{
            Employee employee = employeeService.updateEmployee(email, jsonpatch);
            return ResponseEntity.ok().body(employee);
        }catch(EmployeeDoesNotExistsException | JsonPatchException |
                EmployeeRequestException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/")
    public ResponseEntity<?> deleteEmployeeRecord(Long id){
        try{
            String employee = employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("single/")
    public ResponseEntity<?> getSingleEmployee(Long id) throws EmployeeDoesNotExistsException {
        try{
            Optional<Employee> employee = employeeService.findEmployeeById(id);
            return ResponseEntity.ok().body(employee);
        }catch(IllegalArgumentException | EmployeeDoesNotExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
