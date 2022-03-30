package africa.semicolon.employeemanagement.data.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    @NotNull(message="Field can not be empty")
    private String fieldName;
    @NotNull(message="Field can not be empty")
    private String level;
}
