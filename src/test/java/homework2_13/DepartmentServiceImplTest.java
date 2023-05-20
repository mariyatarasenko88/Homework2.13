package homework2_13;

import homework2_13.model.Employee;
import homework2_13.service.DepartmentServiceImpl;
import homework2_13.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyMap;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    Map<String, Employee> employees = new HashMap<>();

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        assertNotNull(employeeService);
        Employee employee1 = new Employee("Андрей", "Клименко", 1, 30000.0);
        Employee employee2 = new Employee("Владимир", "Шкотов", 3, 50000.0);
        Employee employee3 = new Employee("Анна", "Ильина", 2, 35000.0);
        Employee employee4 = new Employee("Ирина", "Козлова", 2, 70000.0);
        employees.put(employee1.getFullName(), employee1);
        employees.put(employee2.getFullName(), employee2);
        employees.put(employee3.getFullName(), employee3);
        employees.put(employee4.getFullName(), employee4);
    }

    @Test
    public void shouldCorrectIfEmployeeWithMaxSalaryFrom2Department() {
        Mockito.when(employeeService.findAll()).thenReturn(new ArrayList<>(employees.values()));
        Employee expected = departmentService.getEmployeeWithMaxSalary(2);
        Employee actual = new Employee("Ирина", "Козлова", 2, 70000.0);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCorrectIfEmployeeWithMinSalaryFrom2Department() {
        Mockito.when(employeeService.findAll()).thenReturn(new ArrayList<>(employees.values()));
        Employee expected = departmentService.getEmployeeWithMinSalary(2);
        Employee actual = new Employee("Анна", "Ильина", 2, 35000.0);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldCorrectWhenGetAllEmployeeByDepartment() {
        Mockito.when(employeeService.findAll()).thenReturn(new ArrayList<>(employees.values()));
        List<Employee> expected = departmentService.getAllEmployeeByDepartment(2);

        Employee employee1 = new Employee("Анна", "Ильина", 2, 35000.0);
        Employee employee2 = new Employee("Ирина", "Козлова", 2, 70000.0);

        List<Employee> actual = new ArrayList<>();
        actual.add(employee1);
        actual.add(employee2);

        assertEquals(expected, actual);
    }
}
