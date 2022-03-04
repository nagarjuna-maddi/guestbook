package com.guestbook.guestbookbackendsample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestbook.guestbookbackendsample.controller.AdminController;
import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.exception.ResourceNotFoundException;
import com.guestbook.guestbookbackendsample.model.GuestEntry;
import com.guestbook.guestbookbackendsample.repository.AdminRepository;

/**
 * @author nagarjunamaddi
 * 
 * Class processes all the Admin Activities for Entries added by Guest
 *
 */
@Service
public class AdminService {

	private static final Logger _LOGGER = LoggerFactory.getLogger(AdminController.class);

	private static final String ADMIN_APPROVE = "Approved";
	private static final String ADMIN_REJECT = "Rejected";

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Method Fetches all Guest Entries to manage
	 * 
	 * @return list of Guest Entries
	 */
	public List<GuestEntryDto> viewAllGuestEntries() {
		_LOGGER.info("Fetching all Guest Entries");
		Iterable<GuestEntry> guestEntryList = adminRepository.findAll();

		List<GuestEntryDto> guestEntryDtoList = new ArrayList<>();
		for (GuestEntry eachGuestEntry : guestEntryList) {
			guestEntryDtoList.add(convertGuestEntryToGuestEntryDto(eachGuestEntry));
		}

		return guestEntryDtoList;
	}

	/**
	 * Method Updates all Guest Entries
	 * 
	 * @return updated Guest Entry
	 */
	public GuestEntryDto updateGuestEntry(Long id, GuestEntryDto guestEntryDto) {
		_LOGGER.info("Updating Guest Entry : {}", guestEntryDto);
		
		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));

		existingGuestEntry.setComment(guestEntryDto.getComment());

		GuestEntry updatedGuestEntry = adminRepository.save(existingGuestEntry);

		GuestEntryDto updatedGuestEntryDto = convertGuestEntryToGuestEntryDto(updatedGuestEntry);

		return updatedGuestEntryDto;
	}

	private GuestEntryDto convertGuestEntryToGuestEntryDto(GuestEntry guestEntry) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		GuestEntryDto guestEntryDto = modelMapper.map(guestEntry, GuestEntryDto.class);
		guestEntryDto.setUserName(guestEntry.getUser().getUserName());
		return guestEntryDto;
	}

	/**
	 * Method Fetching the specific Guest Entry to manage
	 * 
	 * @return list of Guest Entries
	 */
	public GuestEntryDto getGuestEntryById(Long guestEntryId) {
		_LOGGER.info("Getting Guest Entry : {}", guestEntryId);
		Optional<GuestEntry> optExistingGuestEntry = adminRepository.findById(guestEntryId);
		GuestEntry existingGuestEntry = optExistingGuestEntry.get();
		GuestEntryDto updatedGuestEntryDto = convertGuestEntryToGuestEntryDto(existingGuestEntry);
		return updatedGuestEntryDto;
	}

	/**
	 * Method Deletes the Guest Entry
	 * 
	 * @return Status of Deletion
	 */
	public Map<String, Boolean> deleteGuestEntry(Long id) {
		_LOGGER.info("Deleting Guest Entry : {}", id);
		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));

		adminRepository.delete(existingGuestEntry);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

	/**
	 * Method Approves the Guest Entry
	 * 
	 * @return Status of Approval
	 */
	public Map<String, String> approveGuestEntryById(Long id, String status) {
		_LOGGER.info("Appoving Guest Entry : {}", id);
		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));

		existingGuestEntry.setStatus(ADMIN_APPROVE);

		adminRepository.save(existingGuestEntry);

		Map<String, String> response = new HashMap<>();
		response.put("status", ADMIN_APPROVE);
		return response;
	}

	/**
	 * Method Rejects the Guest Entry
	 * 
	 * @return Status of Rejection
	 */
	public Map<String, String> rejectGuestEntryById(Long id, String status) {
		_LOGGER.info("Rejecting Guest Entry : {}", id);
		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));
		existingGuestEntry.setStatus(ADMIN_REJECT);
		adminRepository.save(existingGuestEntry);
		Map<String, String> response = new HashMap<>();
		response.put("status", ADMIN_REJECT);
		return response;
	}

}
