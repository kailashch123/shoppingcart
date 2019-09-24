/**
 * This class is used to validate the authenticated user.
 * 
 * @author 764432
 *
 */
package com.cts.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.User;
import com.cts.repository.iface.IAuthRepository;
import com.cts.service.iface.IAuthService;

@Service("authService")
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private IAuthRepository authRepository;

	/**
	 * It is used to allow the user to login.
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String login(User user) {

		return authRepository.login(user);

	}

	/**
	 * It is used to create a user.
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String createUser(User user) {

		return authRepository.createUser(user);
	}
}
