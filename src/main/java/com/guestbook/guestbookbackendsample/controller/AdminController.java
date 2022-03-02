package com.guestbook.guestbookbackendsample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.service.AdminService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestapp/")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/admin/viewAllGuestEntries")
	public List<GuestEntryDto> viewAllGuestEntries() {
		return adminService.viewAllGuestEntries();
	}

	@PutMapping("/admin/updateGuestEntry/{guestId}")
	public ResponseEntity<GuestEntryDto> updateGuestEntry(@PathVariable Long guestId,
			@RequestBody GuestEntryDto guestEntryDto) {

		GuestEntryDto updatedGuestEntryDto = adminService.updateGuestEntry(guestEntryDto);
		return ResponseEntity.ok(updatedGuestEntryDto);
	}

}
