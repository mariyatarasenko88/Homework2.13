package homework2_13;

import homework2_13.exception.EmployeeAlreadyAddedException;
import homework2_13.exception.EmployeeNotFoundException;
import homework2_13.exception.EmptyValueException;
import homework2_13.model.Employee;
import homework2_13.service.EmployeeService;
import homework2_13.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static homework2_13.EmployeeServiceImplTextConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplTest {
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    public static Stream<Arguments> provideParamsForTests() {
        return Stream.of(
                Arguments.of(FIRSTNAME_ANDREY, LASTNAME_KLIMENKO, DEPARTMENT_ONE, SALARY_10000),
                Arguments.of(FIRSTNAME_IRINA, LASTNAME_KOZLOVA, DEPARTMENT_THREE, SALARY_NEGATIVE_10000),
                Arguments.of(FIRSTNAME_VLADIMIR, LASTNAME_SHKOTOV, DEPARTMENT_TWO, SALARY_BIGNUMBER)
        );
    }

    @Test
    public void addingElementThatInMap() throws EmployeeAlreadyAddedException {
        employeeService.add(FIRSTNAME_ANDREY, LASTNAME_KLIMENKO, DEPARTMENT_ONE, SALARY_10000);
        Throwable thrown = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.add(FIRSTNAME_ANDREY, LASTNAME_KLIMENKO, DEPARTMENT_ONE, SALARY_10000);
        });
        assertNotNull(thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectAddEntryToMapEmployees(String firstName, String lastName, Integer department, Double salary) {
        Employee expectedEmployee = new Employee(firstName, lastName, department, salary);
        Map<String, Employee> expected = new HashMap<>();
        expected.put(expectedEmployee.getFullName(), expectedEmployee);

        Employee actualEmployee = employeeService.add(firstName, lastName, department, salary);
        Map<String, Employee> actual = employeeService.getEmployees();
        actual.put(actualEmployee.getFullName(), actualEmployee);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectRemoveEntryFromMapEmployees(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        String firstNameTest = firstName + "test";
        String lastNameTest = lastName + "test";
        Employee employeeTest = new Employee(firstNameTest, lastNameTest);

        Map<String, Employee> expected = new HashMap<>();
        expected.put(employeeTest.getFullName(), employeeTest);

        Map<String, Employee> actual = employeeService.getEmployees();
        actual.put(employee.getFullName(), employee);
        actual.put(employeeTest.getFullName(), employeeTest);
        employeeService.remove(firstName, lastName);

        assertEquals(expected, actual);
    }

    @Test
    public void deletingEmployeeThatNotInMap() {
        Map<String, Employee> employees = employeeService.getEmployees();
        Employee employee = employees.remove("Non-existent user");
        assertNull(employee);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectFindEmployeeInMap(String firstName, String lastName) {
        Employee expected = new Employee(firstName, lastName);
        Map<String, Employee> employees = employeeService.getEmployees();
        employees.put(expected.getFullName(), expected);

        Employee actual = employeeService.find(firstName, lastName);

        assertEquals(expected, actual);
    }

    @Test
    public void EmployeeNotFoundInMap() throws EmployeeNotFoundException {
        Throwable thrown = assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("Not", "Found"));
    }

    @Test
    public void shouldCorrectIfEnteredInvalidValues() throws EmptyValueException {
        Throwable thrown = assertThrows(EmptyValueException.class, () -> employeeService.find("111", "Клименко"));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTests")
    public void shouldCorrectPrintListFromValuesMapEmployees(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        Map<String, Employee> employees = employeeService.getEmployees();
        employees.put(employee.getFullName(), employee);

        List<Employee> expected = new ArrayList<>(List.of(employee));

        List<Employee> actual = employeeService.findAll();

        assertEquals(expected, actual);
    }
}
