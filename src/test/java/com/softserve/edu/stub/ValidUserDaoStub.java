package com.softserve.edu.stub;

import com.softserve.edu.dao.IUserDao;

public class ValidUserDaoStub implements IUserDao {

	public String getIPAddress() {
		return "aaa.123";
	}

	public String getIPAddress(String text) {
		return "aaa.123";
	}
}
