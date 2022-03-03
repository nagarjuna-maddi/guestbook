package com.guestbook.guestbookbackendsample.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.model.GuestEntry;
import com.guestbook.guestbookbackendsample.repository.GuestRepository;

@Service
public class GuestService {

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	ModelMapper modelMapper;

	public void saveGuestEntry(GuestEntryDto guestEntryDto) {
		GuestEntry guestEntry = convertGuestEntryDtoToGuestEntry(guestEntryDto);
		guestRepository.save(guestEntry);
	}
	
	private GuestEntryDto convertGuestEntryToGuestEntryDto(GuestEntry guestEntry) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		GuestEntryDto guestEntryDto = modelMapper.map(guestEntry, GuestEntryDto.class);
		return guestEntryDto;
	}
	
	private GuestEntry convertGuestEntryDtoToGuestEntry(GuestEntryDto guestEntryDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		GuestEntry guestEntry = modelMapper.map(guestEntryDto, GuestEntry.class);
		return guestEntry;
	}
}
