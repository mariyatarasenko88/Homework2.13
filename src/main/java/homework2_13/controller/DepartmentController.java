package homework2_13.controller;

import homework2_13.model.Employee;
import homework2_13.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService service;
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping(value = "/max-salary", params = "/departmentId")
    public Employee maxSalaryByDepartment(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return service.getEmployeeWithMaxSalary(departmentId);
    }
    @GetMapping(value = "/min-salary", params = "/departmentId")
    public Employee minSalaryByDepartment(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return service.getEmployeeWithMinSalary(departmentId);
    }
    @GetMapping(value = "/all", params = "/departmentId")
    public List<Employee> allEmployeeByDepartment(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return service.getAllEmployeeByDepartment(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> allEmployeeGroupByDepartment() {
        return service.getAllEmployeeGroupByDepartment();
    }
}
