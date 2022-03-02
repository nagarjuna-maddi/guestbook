package com.guestbook.guestbookbackendsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.guestbookbackendsample.service.AdminService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestapp/login/")

public class LoginController {

	//@Autowired
	//AdminService adminService;

	@GetMapping("/loginUser")
	public String loginUser() {
		System.out.println("login controller");
		return "";
	}
	
}
