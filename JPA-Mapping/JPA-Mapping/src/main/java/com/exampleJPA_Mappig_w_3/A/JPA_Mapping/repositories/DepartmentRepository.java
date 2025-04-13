package com.exampleJPA_Mappig_w_3.A.JPA_Mapping.repositories;

import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.entitis.DepartmentEntity;
import com.exampleJPA_Mappig_w_3.A.JPA_Mapping.entitis.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
