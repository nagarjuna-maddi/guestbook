package com.guestbook.guestbookbackendsample.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.guestbook.guestbookbackendsample.dto.UserDto;
import com.guestbook.guestbookbackendsample.model.User;

/**
 * @author nagarjunamaddi 
 * 
 * Class to be used to perform conversions for Entity to Dto and vice-versa
 *
 */
public class ModelMapperUtil {
	public static UserDto convertUserToUserDto(User user) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	public static User convertUserToUserDto(UserDto userDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		User user = modelMapper.map(userDto, User.class);
		return user;
	}
}
