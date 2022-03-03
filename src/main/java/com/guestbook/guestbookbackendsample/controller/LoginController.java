package com.guestbook.guestbookbackendsample.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestbookapp/login")
public class LoginController {

	// @Autowired
	// AdminService adminService;

	@GetMapping("/loginUser")
	@ResponseBody
	public String loginUser() {
		System.out.println("login controller");
		return "Success4";
	}

}
