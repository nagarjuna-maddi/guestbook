package com.guestbook.guestbookbackendsample.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.service.GuestService;

/**
 * @author nagarjunamaddi
 * 
 * Class manages the operation such as
 * Add Comment, View all the Guest Entries which are approved by Admin
 * for a logged in Guest
 * 
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/guestbookapp/guest")
public class GuestController {
	
    private static final Logger _LOGGER = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	GuestService guestService;
	
	@GetMapping("/viewAllApprovedGuestEntries/{id}")
	public List<GuestEntryDto> viewAllApprovedGuestEntries(@PathVariable Long id) {
		_LOGGER.info("Fetching all Guest Entries which are Approved by Admin");
		return guestService.viewAllApprovedGuestEntries(id);
	}

	@PostMapping("/saveGuestEntry")
	public void saveGuestEntry(@RequestBody GuestEntryDto guestEntryDto) {
		_LOGGER.info("Saving the GuestEntry : {}" + guestEntryDto);
		guestService.saveGuestEntry(guestEntryDto);
	}

	@PostMapping("/saveGuestImage")
	public ResponseEntity<Map<String, String>> saveGuestImage(@RequestParam("image") MultipartFile file, @RequestParam("userId") String userId)
			throws Exception {

		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}
		_LOGGER.info("multipart userId : "+userId);
		guestService.saveGuestImage(userId,file);

		Map<String, String> response = new HashMap<>();
		response.put("status", "SUCCESS");

		return ResponseEntity.ok(response);
	}

	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
		_LOGGER.info("DownloadImage Guest Entry Id : {}", id);
		// Load file from database
		GuestEntryDto guestEntryDto = guestService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(guestEntryDto.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + guestEntryDto.getImageName() + "\"")
				.body(new ByteArrayResource(guestEntryDto.getImage()));
	}

}
