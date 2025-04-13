package com.spring.production_ready_feature.clients.impl;

import com.spring.production_ready_feature.advice.ApiResponse;
import com.spring.production_ready_feature.clients.EmployeeClient;
import com.spring.production_ready_feature.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

   private final RestClient restClient;
   Logger log= LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.trace("trying to retrive all emp in getAllEmp");
        try {
            log.info("attempting to call the rest client metnod in getAllemp");
            ApiResponse<List<EmployeeDTO>> employeeDtoList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("successfully retrived the emp in getAllEmp");
            log.trace("retrived emp list in getAllemp : {}", employeeDtoList.getData());
            return employeeDtoList.getData();
        } catch (Exception e) {
            log.error("exception occured in getallemployee", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
       try {
           log.trace("trying to get emp by id in getEmpByid with id {} :" ,employeeId);
           ApiResponse<EmployeeDTO> employeeResponse=restClient.get()
                   .uri("employees/{employeeId}", employeeId)
                   .retrieve()
                   .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                       log.error(new String(response.getBody().readAllBytes()));
                       throw  new RuntimeException("could not create the employee");
                   }))
                   .body(new ParameterizedTypeReference<>() {
                   });
           return employeeResponse.getData();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("trying to create emp with information  :",employeeDTO );
        try {
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse=restClient.post()
                    .uri("employee")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        log.debug("4xxxClient error occurred during createNewEmp ");
                        log.error(new String(response.getBody().readAllBytes()));
                        throw  new RuntimeException("could not create the employee");
                    }))
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("Successfully create a new emp : {}" , employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();

        } catch (Exception e) {
            log.error("exception occurred during creation of emp ");
            throw new RuntimeException(e);
        }

    }
}
