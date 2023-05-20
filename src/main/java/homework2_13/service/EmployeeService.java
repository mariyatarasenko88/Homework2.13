package homework2_13.service;

import homework2_13.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Employee add(String firstName, String lastNme, Integer department, Double salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> findAll();

    Map<String, Employee> getEmployees();
}
