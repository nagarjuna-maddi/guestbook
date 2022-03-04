package com.guestbook.guestbookbackendsample.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * @author nagarjunamaddi
 * 
 * Class manages the operation such as
 * Approve, Reject, Update, Delete for all the Guest Entries for Admin
 * 
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/guestapp/admin")
public class AdminController {
	
    private static final Logger _LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	@Autowired
	GuestService guestService;

	/**
	 * Method fetches all the Guest Entries for Admin
	 * 
	 * @return List of GuestEntries
	 */
	@GetMapping("/viewAllGuestEntries")
	public List<GuestEntryDto> viewAllGuestEntries() {
		_LOGGER.info("Fetching All Guest Entries for Admin");
		return adminService.viewAllGuestEntries();
	}

	/**
	 * Method updates the Guest Entries for Admin
	 * 
	 * @return Updated Entity
	 */
	@PutMapping("/updateGuestEntry/{id}")
	public ResponseEntity<GuestEntryDto> updateGuestEntry(@PathVariable Long id,
			@RequestBody GuestEntryDto guestEntryDto) {
		_LOGGER.info("Updating Guest Entry for Guest Entry Id : {}", id);
		GuestEntryDto updatedGuestEntryDto = adminService.updateGuestEntry(id, guestEntryDto);
		return ResponseEntity.ok(updatedGuestEntryDto);
	}

	/**
	 * Method fetches the specific GuestEntry object by Id
	 * 
	 * @return Guest Entry Entity
	 */
	@GetMapping("/getGuestEntryById/{id}")
	public ResponseEntity<GuestEntryDto> getGuestEntryById(@PathVariable Long id) {
		_LOGGER.info("Fetching Guest Entry by Id : {}", id);
		GuestEntryDto updatedGuestEntryDto = adminService.getGuestEntryById(id);
		return ResponseEntity.ok(updatedGuestEntryDto);
	}

	/**
	 * Method deletes the specific GuestEntry object by Id
	 * 
	 * @return Deleted status
	 */
	@DeleteMapping("/deleteGuestEntry/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteGuestEntry(@PathVariable Long id) {
		_LOGGER.info("Deleting Guest Entry by Id : {}", id);
		Map<String, Boolean> response = adminService.deleteGuestEntry(id);

		return ResponseEntity.ok(response);

	}

	/**
	 * Method Approves the specific GuestEntry object by Id
	 * 
	 * @return Approved status
	 */
	@PutMapping("/approveGuestEntryById/{id}")
	public ResponseEntity<Map<String, String>> approveGuestEntryById(@PathVariable Long id,
			@RequestBody String status) {
		_LOGGER.info("Approving Guest Entry by Id : {}", id);
		Map<String, String> response = adminService.approveGuestEntryById(id, status);
		return ResponseEntity.ok(response);
	}

	/**
	 * Method Rejects the specific GuestEntry object by Id
	 * 
	 * @return Rejected status
	 */
	@PutMapping("/rejectGuestEntryById/{id}")
	public ResponseEntity<Map<String, String>> rejectGuestEntryById(@PathVariable Long id, @RequestBody String status) {
		_LOGGER.info("Rejecting Guest Entry by Id : {}", id);
		Map<String, String> response = adminService.rejectGuestEntryById(id, status);
		return ResponseEntity.ok(response);
	}

	/**
	 * Method downloads the image uploaded by the guest
	 * 
	 * @return stream of file for download at client
	 */
	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {

		_LOGGER.info("Downloading file by Guest Entry Id : {}", id);
		// Load file from database
		GuestEntryDto guestEntryDto = guestService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(guestEntryDto.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + guestEntryDto.getImageName() + "\"")
				.body(new ByteArrayResource(guestEntryDto.getImage()));
	}

}
