package com.guestbook.guestbookbackendsample.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import com.guestbook.guestbookbackendsample.service.GuestService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestapp/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	GuestService guestService;

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

	@PutMapping("/approveGuestEntryById/{id}")
	public ResponseEntity<Map<String, String>> approveGuestEntryById(@PathVariable Long id,
			@RequestBody String status) {
		System.out.println("approveGuestEntryById...");
		Map<String, String> response = adminService.approveGuestEntryById(id, status);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/rejectGuestEntryById/{id}")
	public ResponseEntity<Map<String, String>> rejectGuestEntryById(@PathVariable Long id, @RequestBody String status) {
		System.out.println("rejectGuestEntryById...");
		Map<String, String> response = adminService.rejectGuestEntryById(id, status);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {

		System.out.println("downloadImage....admin");
		// Load file from database
		GuestEntryDto guestEntryDto = guestService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(guestEntryDto.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + guestEntryDto.getImageName() + "\"")
				.body(new ByteArrayResource(guestEntryDto.getImage()));
	}

}
