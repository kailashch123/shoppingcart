package com.cts.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.model.User;

@RunWith(SpringRunner.class)
public class RWExcelFileTest {

	@InjectMocks
	RWExcelFile rwExcelFile;

	@Test
	public void readExcelTest() {
		String filepath = "./src/main/resources/excel/login.xlsx";
		rwExcelFile.readExcel(filepath);
	}

	@Test
	public void writeExcelTest() {
		User user = new User();
		user.setFirstName("Sri");
		user.setLastName("RaM");
		user.setPassword("123yyd");
		user.setUserId("testUser");
		String writeExcelResponse = rwExcelFile.writeExcel(user);
		assertEquals("User Registered Successfully", writeExcelResponse);
	}

}
