package com.guestbook.guestbookbackendsample.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.guestbook.guestbookbackendsample.dto.GuestEntryDto;
import com.guestbook.guestbookbackendsample.model.GuestEntry;
import com.guestbook.guestbookbackendsample.repository.GuestRepository;

/**
 * @author nagarjunamaddi
 * 
 *         Class processes all the Guest Activities for Entries Appoved by Guest
 *
 */
@Service
public class GuestService {

	private static final Logger _LOGGER = LoggerFactory.getLogger(GuestService.class);

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Method Saves the Guest comment as Guest Entry
	 * 
	 * @param guestEntryDto input Guest Entry
	 */
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

	/**
	 * Method Saves the input uploaded file to DB
	 * 
	 * @param userId user id by whom file has been uploaded
	 * @param file   input file stream
	 * @throws IOException exception
	 */
	public void saveGuestImage(String userId, MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		String originalName = file.getOriginalFilename();
		String name = file.getName();
		String contentType = file.getContentType();
		long size = file.getSize();

		_LOGGER.info("inputStream: " + inputStream);
		_LOGGER.info("originalName: " + originalName);
		_LOGGER.info("name: " + name);
		_LOGGER.info("contentType: " + contentType);
		_LOGGER.info("size: " + size);

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		_LOGGER.info("fileName : " + fileName);

		try {
			GuestEntry guestEntry = new GuestEntry();
			guestEntry.setImage(file.getBytes());
			guestEntry.setImageType(contentType);
			guestEntry.setImageName(originalName);
			guestEntry.setStatus("Pending");
			guestEntry.setUserId(Long.valueOf(userId));
			guestRepository.save(guestEntry);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Downloads the file which as uploaded by Guest by Guest Entry Id
	 * 
	 * @param id Guest Entry Id
	 * @return Guest Entry
	 */
	public GuestEntryDto downloadFile(Long id) {

		Optional<GuestEntry> optGuestEntry = guestRepository.findById(id);
		GuestEntry existingGuestEntry = optGuestEntry.get();

		GuestEntryDto guestEntryDto = convertGuestEntryToGuestEntryDto(existingGuestEntry);

		return guestEntryDto;
	}

	/**
	 * Method fetches all the Approved comments by Admin
	 * 
	 * @param id Guest Entry Id
	 * @return list of Approved entries
	 */
	public List<GuestEntryDto> viewAllApprovedGuestEntries(Long id) {
		List<GuestEntry> guestEntryList = guestRepository.findByUserIdAndStatus(id, "Approved");

		List<GuestEntryDto> guestEntryDtoList = new ArrayList<>();
		for (GuestEntry eachGuestEntry : guestEntryList) {
			guestEntryDtoList.add(convertGuestEntryToGuestEntryDto(eachGuestEntry));
		}

		return guestEntryDtoList;
	}

}
