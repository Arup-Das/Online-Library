package com.example.arup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.arup.model.UserInformationModel;
import com.example.arup.model.UserRegistrationModel;
import com.example.arup.service.UserInformationService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	@Autowired
	private UserInformationService userInformationService;
	
	@ModelAttribute("userInfo")
	public UserRegistrationModel userRegistrationModel() {
		return new UserRegistrationModel();
	}
	@GetMapping
	public String showRegistrationForm(Model model) {
		return "registration";
	}
	@PostMapping
	public String registerUserAccount(@ModelAttribute("userInfo") @Valid UserRegistrationModel userInfo, BindingResult result) {
		UserInformationModel existingUserInfo = userInformationService.findUserByEmail(userInfo.getEmail());
		if(existingUserInfo != null) {
			result.reject("email", "There is already an User registered with email "+userInfo.getEmail());
		}
		if (result.hasErrors()) {
            return "registration";
        }
		userInformationService.save(userInfo);
		return "redirect:/registration?success";
	}
}
