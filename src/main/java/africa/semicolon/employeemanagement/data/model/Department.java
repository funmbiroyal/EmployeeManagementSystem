package africa.semicolon.employeemanagement.data.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private JobDepartment fieldName;

    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Employee> employees;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Role jobRole;
}
