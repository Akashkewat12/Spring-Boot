package com.spring.production_ready_feature;

import com.spring.production_ready_feature.clients.EmployeeClient;
import com.spring.production_ready_feature.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductionReadyFeatureApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployees() {
		List<EmployeeDTO>  employeeDTOList=employeeClient.getAllEmployees();
		System.out.println(employeeDTOList);
	}

	@Test
	@Order(2)
	void getEmployeeByIdTest() {
		EmployeeDTO employeeDTO=employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	@Order(1)
	void createNewEmployeeTest() {

		EmployeeDTO employeeDTO=new EmployeeDTO(null, "Akash", "abc@gmail.com",
				20, "USER", 5000.00, LocalDate.of(2020,12,2),true);
		EmployeeDTO savedEmployeeDTO=employeeClient.createNewEmployee(employeeDTO);
		System.out.println(savedEmployeeDTO);
	}


}
