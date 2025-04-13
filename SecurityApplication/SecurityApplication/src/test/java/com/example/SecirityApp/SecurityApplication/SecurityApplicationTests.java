package com.example.SecirityApp.SecurityApplication;

import com.example.SecirityApp.SecurityApplication.entities.User;
import com.example.SecirityApp.SecurityApplication.sercices.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user=new User(4L, "akas@gmail.com", "123","akash");

		String token=jwtService.generateToken(user);

		System.out.println(token);

		Long id=jwtService.getUserIdFromToken(token);

		System.out.println(id);


	}

}
