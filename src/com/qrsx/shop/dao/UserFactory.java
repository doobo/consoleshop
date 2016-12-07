package com.qrsx.shop.dao;

import com.qrsx.shop.impl.UserImpl;

public class UserFactory {

	private static UserDao userdao = null;

	public static UserDao getInstance() {
		if (userdao == null) {
			userdao = new UserImpl();
		}
		return userdao;
	}

}
