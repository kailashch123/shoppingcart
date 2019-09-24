package com.cts.service.iface;

import com.cts.model.User;

public interface IAuthService {

	String login(User user);

	String createUser(User user);
}
