package com.guestbook.guestbookbackendsample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.exception.ResourceNotFoundException;
import com.guestbook.guestbookbackendsample.model.GuestEntry;
import com.guestbook.guestbookbackendsample.repository.AdminRepository;

@Service
public class AdminService {

	private static final String ADMIN_APPROVE = "Approved";
	private static final String ADMIN_REJECT = "Rejected";

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * @return
	 */
	public List<GuestEntryDto> viewAllGuestEntries() {

		Iterable<GuestEntry> guestEntryList = adminRepository.findAll();

		List<GuestEntryDto> guestEntryDtoList = new ArrayList<>();
		for (GuestEntry eachGuestEntry : guestEntryList) {
			guestEntryDtoList.add(convertGuestEntryToGuestEntryDto(eachGuestEntry));
		}

		return guestEntryDtoList;
	}

	/**
	 * @param guestEntryDto
	 * @return
	 */
	public GuestEntryDto updateGuestEntry(Long id, GuestEntryDto guestEntryDto) {
		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));

		existingGuestEntry.setComment(guestEntryDto.getComment());
		// existingGuestEntry.setImage(guestEntryDto.getImage());

		GuestEntry updatedGuestEntry = adminRepository.save(existingGuestEntry);

		GuestEntryDto updatedGuestEntryDto = convertGuestEntryToGuestEntryDto(updatedGuestEntry);

		return updatedGuestEntryDto;

	}

	/**
	 * @param guestEntry
	 */
	public void approveGuestEntries(GuestEntry guestEntry) {
		adminRepository.save(guestEntry);
	}

	public void removeGuestEntries() {
	}

	private GuestEntryDto convertGuestEntryToGuestEntryDto(GuestEntry guestEntry) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		GuestEntryDto guestEntryDto = modelMapper.map(guestEntry, GuestEntryDto.class);
		guestEntryDto.setUserName(guestEntry.getUser().getUserName());
		return guestEntryDto;
	}

	public GuestEntryDto getGuestEntryById(Long guestEntryId) {
		System.out.println("AdminService..1");
		Optional<GuestEntry> optExistingGuestEntry = adminRepository.findById(guestEntryId);
		GuestEntry existingGuestEntry = optExistingGuestEntry.get();
		GuestEntryDto updatedGuestEntryDto = convertGuestEntryToGuestEntryDto(existingGuestEntry);
		System.out.println("AdminService..2");
		return updatedGuestEntryDto;
	}

	public Map<String, Boolean> deleteGuestEntry(Long id) {
		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));

		adminRepository.delete(existingGuestEntry);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}

	public Map<String, String> approveGuestEntryById(Long id, String status) {

		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));

		existingGuestEntry.setStatus(ADMIN_APPROVE);

		adminRepository.save(existingGuestEntry);

		Map<String, String> response = new HashMap<>();
		response.put("status", ADMIN_APPROVE);
		return response;
	}

	public Map<String, String> rejectGuestEntryById(Long id, String status) {

		GuestEntry existingGuestEntry = adminRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Guest Entry not exist with id :" + id));
		existingGuestEntry.setStatus(ADMIN_REJECT);
		adminRepository.save(existingGuestEntry);
		Map<String, String> response = new HashMap<>();
		response.put("status", ADMIN_REJECT);
		return response;
	}

}
