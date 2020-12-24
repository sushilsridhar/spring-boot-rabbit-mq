package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import util.CustomLocalDateSerializer;

import java.time.LocalDate;

@AllArgsConstructor
public class Employee {

    private String name;

    @JsonProperty("employee_id")
    private String employeeId;

    @JsonProperty("birth_date")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate birthDate;
}
