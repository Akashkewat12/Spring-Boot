package com.example.spring_MVC.and.RESTFul.api.W_2.repositiries;

import com.example.spring_MVC.and.RESTFul.api.W_2.entitis.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

//    List<EmployeeEntity> findById(String name);
//    List<EmployeeEntity> findByAge(Integer age);
}
