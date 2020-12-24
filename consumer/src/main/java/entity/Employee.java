package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.CustomLocalDateDeserializer;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private String name;

    @JsonProperty("employee_id")
    private String employeeId;

    @JsonProperty("birth_date")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate birthDate;
}
