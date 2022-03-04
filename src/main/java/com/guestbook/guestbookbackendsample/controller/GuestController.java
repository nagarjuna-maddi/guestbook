package com.guestbook.guestbookbackendsample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/guestbookapp/guest")
public class GuestController {

	@Autowired
	GuestService guestService;
	
	@GetMapping("/viewAllApprovedGuestEntries/{id}")
	public List<GuestEntryDto> viewAllApprovedGuestEntries(@PathVariable Long id) {
		System.out.println("viewAllApprovedGuestEntries.........");
		return guestService.viewAllApprovedGuestEntries(id);
	}

	@PostMapping("/saveGuestEntry")
	public void saveGuestEntry(@RequestBody GuestEntryDto guestEntryDto) {
		System.out.println("saveGuestEntry.........23" + guestEntryDto);
		guestService.saveGuestEntry(guestEntryDto);
	}

	@PostMapping("/saveGuestImage")
	public ResponseEntity<Map<String, String>> saveGuestImage(@RequestParam("image") MultipartFile file, @RequestParam("userId") String userId)
			throws Exception {

		if (file == null) {
			throw new RuntimeException("You must select the a file for uploading");
		}
System.out.println("multipart userId : "+userId);
		guestService.saveGuestImage(userId,file);

		Map<String, String> response = new HashMap<>();
		response.put("status", "SUCCESS");

		return ResponseEntity.ok(response);
	}

	@GetMapping("/downloadImage/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
		System.out.println("downloadImage....guest");
		// Load file from database
		GuestEntryDto guestEntryDto = guestService.downloadFile(id);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(guestEntryDto.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + guestEntryDto.getImageName() + "\"")
				.body(new ByteArrayResource(guestEntryDto.getImage()));
	}

}
