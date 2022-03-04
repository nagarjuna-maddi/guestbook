package com.guestbook.guestbookbackendsample.controller;

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

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestbookapp/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping("/loginUser")
	@ResponseBody
	public String loginUser() {
		System.out.println("login controller");
		return "Success4";
	}

	@PostMapping("/validateUser")
	public ResponseEntity<UserDto> validateUser(@RequestBody UserDto userDto) {
		System.out.println("validateUser.........2" + userDto);
		UserDto validUserDto = loginService.validateUser(userDto);
		return ResponseEntity.ok(validUserDto);
	}

}
