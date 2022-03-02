package com.guestbook.guestbookbackendsample;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
public class GuestbookBackendSampleApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(GuestbookBackendSampleApplication.class, args);
	}

}
