package com.guestbook.guestbookbackendsample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.model.GuestEntry;
import com.guestbook.guestbookbackendsample.repository.AdminRepository;

@Service
public class AdminService {

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
	public GuestEntryDto updateGuestEntry(GuestEntryDto guestEntryDto) {
		Optional<GuestEntry> optionalExistingGuestEntry = adminRepository.findById(guestEntryDto.getGuestEntryId());

		GuestEntry existingGuestEntry = optionalExistingGuestEntry.get();

		existingGuestEntry.setComment(guestEntryDto.getComment());

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
		return guestEntryDto;
	}

}
