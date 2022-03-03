package com.guestbook.guestbookbackendsample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/guestapp/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping("/viewAllGuestEntries")
	public List<GuestEntryDto> viewAllGuestEntries() {
		System.out.println("viewAllGuestEntries.........");
		return adminService.viewAllGuestEntries();
	}

	@PutMapping("/updateGuestEntry/{id}")
	public ResponseEntity<GuestEntryDto> updateGuestEntry(@PathVariable Long id,
			@RequestBody GuestEntryDto guestEntryDto) {

		GuestEntryDto updatedGuestEntryDto = adminService.updateGuestEntry(id, guestEntryDto);
		return ResponseEntity.ok(updatedGuestEntryDto);
	}

	@GetMapping("/getGuestEntryById/{id}")
	public ResponseEntity<GuestEntryDto> getGuestEntryById(@PathVariable Long id) {
		GuestEntryDto updatedGuestEntryDto = adminService.getGuestEntryById(id);
		return ResponseEntity.ok(updatedGuestEntryDto);
	}

	@DeleteMapping("/deleteGuestEntry/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteGuestEntry(@PathVariable Long id) {

		Map<String, Boolean> response = adminService.deleteGuestEntry(id);

		return ResponseEntity.ok(response);

	}

}
