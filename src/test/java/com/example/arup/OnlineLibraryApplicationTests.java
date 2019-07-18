package com.example.arup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.arup.model.UserInformationModel;
import com.example.arup.model.UserRegistrationModel;
import com.example.arup.service.MailService;
import com.example.arup.service.UserInformationService;
import com.example.arup.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineLibraryApplicationTests {
	@Autowired
	private UserInformationService userInformationService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailservice;

	@Test
	public void registerUserTest() {
		UserRegistrationModel newUser = new UserRegistrationModel();
		newUser.setFirstName("Arup Kumar");
		newUser.setLastName("Das");
		newUser.setEmail("arup.das@testmail.com");
		newUser.setPassword("12345");
		UserInformationModel registeredUser = userInformationService.save(newUser);
		System.out.println("registeredUser "+registeredUser);
	}
	
	@Test
	public void sendMailTest() {
		//mailservice.sendMail();
	}
}
