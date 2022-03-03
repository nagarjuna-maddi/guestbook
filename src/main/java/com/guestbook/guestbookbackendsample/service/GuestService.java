package com.guestbook.guestbookbackendsample.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

	public void saveGuestImage(MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		String originalName = file.getOriginalFilename();
		String name = file.getName();
		String contentType = file.getContentType();
		long size = file.getSize();

		System.out.println("inputStream: " + inputStream);
		System.out.println("originalName: " + originalName);
		System.out.println("name: " + name);
		System.out.println("contentType: " + contentType);
		System.out.println("size: " + size);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println("fileName : " + fileName);

		try {

			GuestEntry guestEntry = new GuestEntry();
			guestEntry.setImage(file.getBytes());
			guestEntry.setImageType(contentType);
			guestEntry.setImageName(originalName);
			guestEntry.setStatus("Pending");
			guestRepository.save(guestEntry);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public GuestEntryDto downloadFile(Long id) {

		Optional<GuestEntry> optGuestEntry = guestRepository.findById(id);
		GuestEntry existingGuestEntry = optGuestEntry.get();

		GuestEntryDto guestEntryDto = convertGuestEntryToGuestEntryDto(existingGuestEntry);

		return guestEntryDto;

	}

	public List<GuestEntryDto> viewAllApprovedGuestEntries(Long id) {
		List<GuestEntry> guestEntryList = guestRepository.findByUserIdAndStatus(id,"Approved");

		List<GuestEntryDto> guestEntryDtoList = new ArrayList<>();
		for (GuestEntry eachGuestEntry : guestEntryList) {
			guestEntryDtoList.add(convertGuestEntryToGuestEntryDto(eachGuestEntry));
		}

		return guestEntryDtoList;
	}

}
