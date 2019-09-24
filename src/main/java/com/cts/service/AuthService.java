/**
 * This class is used to validate the authenticated user.
 * 
 * @author 764432
 *
 */
package com.cts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.User;
import com.cts.repository.impl.AuthRepository;

@Service
public class AuthService {

	@Autowired
	private AuthRepository authFileRepo;

	/**
	 * It is used to allow the user to login.
	 * 
	 * @param user
	 * @return
	 */
	public String login(User user) {

		return authFileRepo.login(user);

	}

	/**
	 * It is used to create a user.
	 * 
	 * @param user
	 * @return
	 */
	public String createUser(User user) {

		return authFileRepo.createUser(user);
	}
}
