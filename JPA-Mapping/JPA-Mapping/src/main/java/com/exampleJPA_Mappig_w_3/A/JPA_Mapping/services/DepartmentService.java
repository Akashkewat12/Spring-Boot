package com.exampleJPA_Mappig_w_3.A.JPA_Mapping.services;

import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.entitis.DepartmentEntity;
import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.entitis.EmployeeEntity;
import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.repositories.DepartmentRepository;
import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    //@Autowired
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }


    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
     //  Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);
    //   return employeeEntity.map(employee ->employee.getManagedDepartment()).orElse(null);

      EmployeeEntity employeeEntity=EmployeeEntity.builder().id(employeeId).build();
       return departmentRepository.findByManager(employeeEntity);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

//        return departmentEntity.flatMap(department ->
//                employeeEntity.map(employee -> {
//                   department.getWorkers().add(employee);
//                   return departmentRepository.save(department);
//                })).orElse(null);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);

                    department.getWorkers().add(employee);
                    return department;
                })).orElse(null);

    }

    public DepartmentEntity assignFreelancerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {

                  employee.getFreelanceDepartment().add(department);
                  employeeRepository.save(employee);

                  department.getFreelancers().add(employee);
                  return  department;
                })).orElse(null);
    }
}
