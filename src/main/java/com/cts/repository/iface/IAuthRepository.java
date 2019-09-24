package com.cts.repository.iface;

import com.cts.model.User;

public interface IAuthRepository {
	
	String login(User user);

	String createUser(User user);
}
