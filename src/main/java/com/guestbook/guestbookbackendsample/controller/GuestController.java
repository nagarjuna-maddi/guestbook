package com.guestbook.guestbookbackendsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.service.GuestService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/guestbookapp/guest")
public class GuestController {

	@Autowired
	GuestService guestService;

	@PostMapping("/saveGuestEntry")
	public void saveGuestEntry(@RequestBody GuestEntryDto guestEntryDto) {
		System.out.println("saveGuestEntry.........23" + guestEntryDto);
		guestService.saveGuestEntry(guestEntryDto);
	}

}
