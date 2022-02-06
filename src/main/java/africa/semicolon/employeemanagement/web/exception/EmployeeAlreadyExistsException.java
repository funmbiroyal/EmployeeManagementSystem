package africa.semicolon.employeemanagement.web.exception;

public class EmployeeAlreadyExistsException extends Throwable {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
