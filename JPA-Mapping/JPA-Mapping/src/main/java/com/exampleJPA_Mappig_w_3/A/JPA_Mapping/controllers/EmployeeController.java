package com.exampleJPA_Mappig_w_3.A.JPA_Mapping.controllers;

import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.entitis.EmployeeEntity;
import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @Autowired
        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @GetMapping("/{employeeId}")
        public EmployeeEntity getEmployeeById(@PathVariable Long employeeId) {
            return employeeService.getEmployeeById(employeeId);
        }

        @PostMapping
        public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity) {
            return employeeService.createNewEmployee(employeeEntity);
        }
}
