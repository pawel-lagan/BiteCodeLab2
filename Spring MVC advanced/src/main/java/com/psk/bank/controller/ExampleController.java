package com.psk.bank.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.psk.bank.exceptions.ResourceNotFoundException;
import com.psk.bank.model.User;
import com.psk.bank.repository.UserRepository;

@Controller
@ComponentScan(basePackageClasses = UserRepository.class)
public class ExampleController {

	@Autowired
	private UserRepository userRepository;
	
	

	private String VIEW_PAGE = "infoPage";

	@RequestMapping(value = "/modelAndView", method = RequestMethod.GET)
	public ModelAndView modelAndView() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName(VIEW_PAGE);
		mav.addObject("message", "modelAndView");

		return mav;
	}

	
	@RequestMapping(value = "getUserWithId/{id}", method = RequestMethod.GET)
	public String getUserWithId(Model model, @PathVariable("id") Long id) {

		model.addAttribute("message", userRepository.findOne(id).toString());

		return VIEW_PAGE;
	}
	

	


	@PostMapping("/registerUser")
	public String registerUser(Model model, @RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "date", required = false) String date) {

		
		userRepository.save(new User(id, name, LocalDateTime.parse(date)));
		
		model.addAttribute("message", "User with id "+id+" added successfully");

		return VIEW_PAGE;
	}
	


	////////// @PathVariable, @RequestParam

	@RequestMapping(value = "pathVariableExample/{id}/{name}", method = RequestMethod.GET)
	public String pathVariableExample(@PathVariable("id") long id, @PathVariable("name") String name, Model model) {

		model.addAttribute("message", id + " " + name);

		return VIEW_PAGE;
	}
	
	@RequestMapping(value = "pathVariableExample2&{id}&{name}", method = RequestMethod.GET)
	public String pathVariableExample2(@PathVariable("id") long id, @PathVariable("name") String name, Model model) {

		model.addAttribute("message", id + " " + name);

		return VIEW_PAGE;
	}
	
	


	/////////////////////////////@ResponseBody

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody String addUser(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "date", required = false) String date) {

		userRepository.save(new User(id, name, LocalDateTime.parse(date)));

		return "User added successfully";
	}

	@RequestMapping(value = "/deleteUserWithGivenId/{id}", method = RequestMethod.DELETE)
	public @ResponseBody User deleteUserWithGivenId(@PathVariable("id") Long id) {

		return userRepository.deleteOne(id);
	}

	@RequestMapping(value = "/getUserWithGivenId/{id}", method = RequestMethod.GET)
	public @ResponseBody User getUserWithGivenId(@PathVariable("id") Long id) throws ResourceNotFoundException {

		User user = userRepository.findOne(id);
		
		return user;
	}
	
	
	
	
	@PostMapping(value = "/addUserWithRequestBody")
	public @ResponseBody String addUserWithRequestBody(@RequestBody User user) {
		userRepository.save(user);
		return "User added successfully";
	}

	@GetMapping("/getUserWithGivenIdRequestParam")
	public @ResponseBody User reqParam(Model model, @RequestParam(value = "id", required = true) Long id) {

		return userRepository.findOne(id);
	}
	
	
}
