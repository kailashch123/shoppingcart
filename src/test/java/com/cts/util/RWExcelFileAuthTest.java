package com.cts.util;

import static org.junit.Assert.assertEquals;

import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.User;

@RunWith(SpringRunner.class)
public class RWExcelFileAuthTest {

	private RWExcelFileAuth rwExcelFileAuth;

	@Test
	public void readExcelTest() {

		rwExcelFileAuth = new RWExcelFileAuth();
		String res = rwExcelFileAuth.readExcel(getUser(), "./src/test/resources/excel/user.xlsx");
		String expected = "User Looged in sucessfully";
		assertEquals(expected, res);

	}

	@Test
	public void writeExcel() {

		rwExcelFileAuth = new RWExcelFileAuth();
		String res = rwExcelFileAuth.writeExcel(getUser(), "./src/test/resources/excel/user.xlsx");
		String expected = "User Registered Successfully";
		assertEquals(expected, res);

	}
	@Test
	public void readExcelTestElse() {

		rwExcelFileAuth = new RWExcelFileAuth();
		String res = rwExcelFileAuth.readExcel(getUser(), "./src/test/resources/excel/user_test.xlsx");
		String expected = "";
		assertEquals(expected, res);

	}
	
	@Test ( expected = ComparisonFailure.class)
	public void readExcelTestException() {

		rwExcelFileAuth = new RWExcelFileAuth();
		String res = rwExcelFileAuth.readExcel(getUser(), "./src/main/resources/excel/nofile.xlsx");
		String expected = "exception ";
		assertEquals(expected, res);

	}
	@Test ( expected = ComparisonFailure.class)
	public void writeExcelException() {

		rwExcelFileAuth = new RWExcelFileAuth();
		String res = rwExcelFileAuth.writeExcel(getUser(), "./src/main/resources/excel/nofile.xlsx");
		String expected = "User Registered Successfully";
		assertEquals(expected, res);

	}

	private User getUser() {
		User user = new User();
		user.setUserId("user112");
		user.setFirstName("giri");
		user.setLastName("t");
		user.setPassword("passwor");
		return user;
	}
}
