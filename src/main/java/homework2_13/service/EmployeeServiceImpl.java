package homework2_13.service;

import homework2_13.exception.EmployeeAlreadyAddedException;
import homework2_13.exception.EmployeeNotFoundException;
import homework2_13.exception.EmptyValueException;
import homework2_13.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }
    @Override
    public Employee add(String firstName, String lastName, Integer department, Double salary) {
        validateInputTextValue(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в базе");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }
    @Override
    public Employee remove(String firstName, String lastName) {
        validateInputTextValue(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        employees.remove(employee.getFullName());
        return employee;
    }
    @Override
    public Employee find(String firstName, String lastName) {
        validateInputTextValue(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник с такими данными не найден");
    }
    @Override
    public List<Employee> findAll() {
        return new ArrayList<Employee>(employees.values());
    }
    private void validateInputTextValue(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmptyValueException("Передано пустое значение");
        }
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return employees;
    }
}
