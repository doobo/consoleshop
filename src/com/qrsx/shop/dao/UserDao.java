package com.qrsx.shop.dao;

import java.util.List;

import com.qrsx.shop.vo.User;

public interface UserDao {

	boolean initUserDao();

	boolean addUser(User user);

	boolean isExitUser(User user);

	boolean saveUser();

	boolean updateUser(User user);

	boolean delUser(User user);

	User getUserById(int id);

	User getUserByName(String name);

	List<User> getAllUser();

	void sortById();

	int getNextId();
}
