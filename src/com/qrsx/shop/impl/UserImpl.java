package com.qrsx.shop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.qrsx.shop.dao.UserDao;
import com.qrsx.shop.utils.FileUtils;
import com.qrsx.shop.utils.GZipUtils;
import com.qrsx.shop.vo.User;

public class UserImpl implements UserDao {

	private static final String userPath = "user.conf";
	private static List<User> list;

	{
		list = new ArrayList<User>();
		initUserDao();
	}

	/**
	 * ��ʼ��User�û���
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean initUserDao() {
		File file = new File(userPath);
		if (file.exists()) {
			list = (List<User>) GZipUtils.decompressObject(FileUtils.readInputStream(userPath));
			return true;
		} else {
			if (list.isEmpty())
				return false;
			FileUtils.writeOutputStream(userPath, GZipUtils.compressObject(list));
		}
		return false;
	}

	/**
	 * ����û����Զ���ӵ���id
	 */
	@Override
	public boolean addUser(User user) {
		if (isExitUser(user))
			return false;
		user.setId(getNextId());
		list.add(user);
		saveUser();
		return true;
	}

	/**
	 * �Ƿ������ͬ��user
	 */
	@Override
	public boolean isExitUser(User user) {
		if (list.isEmpty())
			return false;
		if (list.contains(user))
			return true;
		return false;
	}

	/**
	 * �����û����ļ�
	 */
	@Override
	public boolean saveUser() {
		FileUtils.writeOutputStream(userPath, GZipUtils.compressObject(list));
		return true;
	}

	/**
	 * ɾ���û�
	 */
	@Override
	public boolean delUser(User user) {
		if (list.isEmpty())
			return false;
		if (list.contains(user)) {
			list.remove(user);
			return true;
		}
		return false;
	}

	/**
	 * ͨ��ID��ȡ�û���Ϣ
	 */
	@Override
	public User getUserById(int id) {
		Iterator<User> it = list.iterator();
		User user = new User();
		while (it.hasNext()) {
			if (id == (user = it.next()).getId()) {
				return user;
			}
		}
		return null;
	}

	/**
	 * �����û�����
	 */

	@Override
	public boolean updateUser(User user) {
		if (list.contains(user)) {
			list.remove(user);
			list.add(user);
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ�����û�
	 */
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return list;
	}

	/**
	 * ����id����
	 */
	@Override
	public void sortById() {
		Collections.sort(list, new SortByID());
	}

	/**
	 * �õ���һ������id
	 */
	@Override
	public int getNextId() {
		if (list.isEmpty())
			return 0;
		sortById();
		return list.get(list.size() - 1).getId() + 1;
	}

	@Override
	public User getUserByName(String name) {
		if (list.isEmpty())
			return null;
		Iterator<User> it = list.listIterator();
		User user;
		while (it.hasNext()) {
			user = it.next();
			if (user.getName().equals(name))
				return user;
		}
		return null;
	}
}

/**
 * ������
 * 
 * @author ٤���ŷ�
 *
 */
class SortByID implements Comparator<User> {
	public int compare(User o1, User o2) {
		if (o1.getId() > o2.getId())
			return 1;
		return 0;
	}
}
