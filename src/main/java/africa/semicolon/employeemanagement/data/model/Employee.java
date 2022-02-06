package africa.semicolon.employeemanagement.data.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique=true, nullable = false)
    private String email;

    @Column(nullable = false)
    private int age;

    public Employee(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Employee() {

    }
}
