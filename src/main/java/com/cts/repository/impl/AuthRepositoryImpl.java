/**
 * This class is used to validate the authenticated user.
 * 
 * @author 764432
 *
 */
package com.cts.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cts.model.User;
import com.cts.repository.iface.IAuthRepository;
import com.cts.util.RWExcelFileAuth;

@Repository("authRepository")
public class AuthRepositoryImpl implements IAuthRepository {

	@Autowired
	private RWExcelFileAuth rWExcelFileAuth;

	/**
	 * This method is used to validate the user credentials.
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String login(final User user) {

		final String res = rWExcelFileAuth.readExcel(user, "./src/main/resources/excel/user.xlsx");
		// return res.equals("User Looged in sucessfully") ? res : "invalid user
		// details";
		return "User Looged in sucessfully".equals(res) ? res : "invalid user details";

	}

	/**
	 * This method is used to write the user credential to excel.
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public String createUser(final User user) {

		return rWExcelFileAuth.writeExcel(user, "./src/main/resources/excel/user.xlsx");

	}
}
