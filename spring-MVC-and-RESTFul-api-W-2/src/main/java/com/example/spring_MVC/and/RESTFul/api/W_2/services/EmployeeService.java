package com.example.spring_MVC.and.RESTFul.api.W_2.services;

import com.example.spring_MVC.and.RESTFul.api.W_2.dto.EmployeeDTO;
import com.example.spring_MVC.and.RESTFul.api.W_2.entitis.EmployeeEntity;
import com.example.spring_MVC.and.RESTFul.api.W_2.exception.ResourceNotFoundException;
import com.example.spring_MVC.and.RESTFul.api.W_2.repositiries.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;



    public Optional<EmployeeDTO> getEmployeeById(Long id) {

    return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }


    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity-> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }


    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity=modelMapper.map(inputEmployee, EmployeeEntity.class);

        EmployeeEntity savedEmployeeEntity=employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }


    public void isExistByEmployeeId(Long employeeId) {

        boolean exists=employeeRepository.existsById(employeeId);
        if(!exists)  throw  new ResourceNotFoundException("Emp not found with emp Id :"+employeeId);

    }
    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistByEmployeeId(employeeId);
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteByEmployeeId(Long employeeId) {
        isExistByEmployeeId(employeeId);
       employeeRepository.deleteById(employeeId);
        return true;
    }


    public EmployeeDTO updatePartialEmployeeByID(Long employeeId, Map<String, Object> updates) {
       isExistByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

       return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

    }
}
