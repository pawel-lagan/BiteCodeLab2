package com.psk.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.psk.bank.model.User;
import com.psk.bank.repository.UserRepository;

@Controller
@RequestMapping("/registrationService")
public class ExercisesRedirectController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public String register(@RequestBody User user) {

		userRepository.save(user);

		return "redirect:/registrationService/showUserPage/" + user.getId();
	}

	@GetMapping("/showUserPage/{id}")
	public String showUserPage(Model model, @PathVariable("id") Long id) {

		model.addAttribute(userRepository.findOne(id));

		return "profile";
	}

}
