package africa.semicolon.employeemanagement.data.model;

public class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private int age;


    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder setAge(int age) {
        this.age = age;
        return this;
    }


    public Employee build() {
        return new Employee(firstName, lastName, email, age);
    }
}
