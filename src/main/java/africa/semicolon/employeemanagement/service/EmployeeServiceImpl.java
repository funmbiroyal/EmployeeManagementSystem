package africa.semicolon.employeemanagement.service;

import africa.semicolon.employeemanagement.data.dto.EmployeeDto;
import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.data.repository.EmployeeRepository;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("initialService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws EmployeeAlreadyExistsException {
        if(employeeDto == null) throw new IllegalArgumentException("Employee records can not be empty");

        Optional<Employee> foundEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(foundEmployee.isPresent()) throw new EmployeeAlreadyExistsException("Employee with email "
                +employeeDto.getEmail()+ " already exists");

        Employee employee = new Employee();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setAge(employeeDto.getAge());

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(String email, JsonPatch jsonpatch) throws EmployeeDoesNotExistsException,
            JsonPatchException, JsonProcessingException, EmployeeRequestException {

        Optional<Employee> query = findEmployeeByEmail(email);

        assert (query.isPresent());
        Employee foundEmployee = query.get();
        try{
            foundEmployee = applyPatchToEmployee(jsonpatch, foundEmployee);
            return employeeRepository.save(foundEmployee);
        }catch(JsonPatchException | JsonProcessingException e){
            throw new EmployeeRequestException("Update failed");
        }
    }

    private Employee applyPatchToEmployee(JsonPatch jsonpatch, Employee employee) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = jsonpatch.apply(objectMapper.convertValue(employee, JsonNode.class));
        return objectMapper.treeToValue(jsonNode, Employee.class);
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    @Override
    public String deleteEmployeeById(Long id) {
        if(id == null) throw new IllegalArgumentException("employee with id" + " "+ id +" " + "does not exist");

        if(employeeRepository.findById(id).isPresent()) employeeRepository.deleteById(id);

        return "Successfully deleted employee with id " + id;
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) throws EmployeeDoesNotExistsException {
        if(email == null) throw new IllegalArgumentException("Employee with the email "+ email + "is empty");

        Optional<Employee> foundEmployee = employeeRepository.findByEmail(email);
        if(foundEmployee.isEmpty()) throw new EmployeeDoesNotExistsException("Employee with the email " + email + " doesn't exist");

        return foundEmployee;
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) throws EmployeeDoesNotExistsException {
        if(id== null) throw new IllegalArgumentException("Employee with the id "+ id +" is empty");

        Optional<Employee> found = employeeRepository.findById(id);

        if(found.isEmpty()) throw new EmployeeDoesNotExistsException("Employee with the id " + id + " doesn't exist");

        return found;
    }


}
