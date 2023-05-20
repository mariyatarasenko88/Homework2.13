package homework2_13.model;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
public class Employee {
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer department;
    private Double salary;

    public Employee(String firstName, String lastName, Integer department, Double salary) {
        this(firstName, lastName);
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }


}

