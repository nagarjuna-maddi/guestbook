package com.guestbook.guestbookbackendsample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.guestbookbackendsample.dto.UserDto;
import com.guestbook.guestbookbackendsample.service.LoginService;

/**
 * @author nagarjunamaddi
 * 
 * Class Validates the logged in user details
 *
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestbookapp/login")
public class LoginController {
	
	private static final Logger _LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@GetMapping("/loginUser")
	@ResponseBody
	public String loginUser() {
		_LOGGER.info("login controller");
		return "Success";
	}

	/**
	 * Method validates the logged in User details
	 * @param userDto User Details
	 * @return valid User Details
	 */
	@PostMapping("/validateUser")
	public ResponseEntity<UserDto> validateUser(@RequestBody UserDto userDto) {
		_LOGGER.info("validating User Details : {}", userDto);
		UserDto validUserDto = loginService.validateUser(userDto);
		return ResponseEntity.ok(validUserDto);
	}

}
