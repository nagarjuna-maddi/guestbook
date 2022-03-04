package com.guestbook.guestbookbackendsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guestbook.guestbookbackendsample.dto.UserDto;
import com.guestbook.guestbookbackendsample.model.User;
import com.guestbook.guestbookbackendsample.repository.LoginRepository;
import com.guestbook.guestbookbackendsample.util.ModelMapperUtil;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public UserDto validateUser(UserDto userDto) {

		User validUser = loginRepository.findByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());

		UserDto validUserDto = ModelMapperUtil.convertUserToUserDto(validUser);
		
		return validUserDto;
	}

}
