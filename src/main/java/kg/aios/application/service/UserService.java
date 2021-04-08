package kg.aios.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kg.aios.application.model.User;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public User findByUsername(String username) {

		logger.warn("Not implemented yet!");

		throw new RuntimeException("Not implemented yet!");
	}
}
