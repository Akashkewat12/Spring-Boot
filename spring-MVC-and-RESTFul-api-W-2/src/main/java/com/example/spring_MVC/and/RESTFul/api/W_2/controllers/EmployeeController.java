package com.example.spring_MVC.and.RESTFul.api.W_2.controllers;


import com.example.spring_MVC.and.RESTFul.api.W_2.dto.EmployeeDTO;
import com.example.spring_MVC.and.RESTFul.api.W_2.exception.ResourceNotFoundException;
import com.example.spring_MVC.and.RESTFul.api.W_2.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

//@ResponseBody // it is used to convert xml/json data  into the java object



@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){

        Optional<EmployeeDTO> employeeDTO=employeeService.getEmployeeById(id);
        return employeeDTO
                .map(employeeDTO1 ->
                        ResponseEntity.ok(employeeDTO1)).
                orElseThrow(()-> new ResourceNotFoundException("Emp not found with id : "+id));

    }



     @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(@RequestParam(required = false, name = "input age") Integer age,
                                        @RequestParam(required = false) String sortBy) {

        return  ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody  @Valid EmployeeDTO inputEmployee) {

        EmployeeDTO savedEmployee=employeeService.createNewEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid  EmployeeDTO employeeDTO, @PathVariable Long employeeId) {

        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteByEmployeeId(@PathVariable Long employeeId) {

        boolean getDeleted= employeeService.deleteByEmployeeId(employeeId);
        if(getDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
       // return ResponseEntity.ok(employeeService.deleteByEmployeeId(employeeId));
    }

    @PatchMapping(path = "/{employeeId}")
    public  ResponseEntity<EmployeeDTO> updatePartialEmployeeByID(@RequestBody Map<String, Object> updates,
                                                  @PathVariable Long employeeId) {

        EmployeeDTO employeeDTO=employeeService.updatePartialEmployeeByID(employeeId,updates);
        if (employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }



}
