package homework2_13.controller;

import homework2_13.model.Employee;
import homework2_13.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
        private final EmployeeService service;
        public EmployeeController(EmployeeService service) {
            this.service = service;
        }
        @GetMapping("/add")
        public Employee addEmployee(@RequestParam(value = "firstname", required = false) String firstName,
                                    @RequestParam(value = "lastname", required = false) String lastName,
                                    @RequestParam(value = "department", required = false) Integer department,
                                    @RequestParam(value = "salary", required = false) Double salary) {
            return service.add(firstName, lastName, department, salary);
        }
        @GetMapping("/remove")
        public Employee removeEmployee(@RequestParam(value = "firstname", required = false) String firstName,
                                       @RequestParam(value = "lastname", required = false) String lastName) {
            return service.remove(firstName, lastName);
        }
        @GetMapping("/find")
        public Employee findEmployee(@RequestParam(value = "firstname", required = false) String firstName,
                                     @RequestParam(value = "lastname", required = false) String lastName) {
            return service.find(firstName, lastName);
        }
        @GetMapping()
        public List<Employee> findAll() {
            return service.findAll();
        }
}
