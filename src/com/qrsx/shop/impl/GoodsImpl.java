package com.qrsx.shop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.qrsx.shop.dao.GoodsDao;
import com.qrsx.shop.utils.FileUtils;
import com.qrsx.shop.utils.GZipUtils;
import com.qrsx.shop.vo.Goods;

public class GoodsImpl implements GoodsDao {
	private static final String PATH = "goods.conf";
	private static List<Goods> list;

	{
		list = new ArrayList<Goods>();
		initGoodsDao();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean initGoodsDao() {
		File file = new File(PATH);
		if (file.exists()) {
			list = (List<Goods>) GZipUtils.decompressObject(FileUtils.readInputStream(PATH));
			return true;
		} else {
			if (list.isEmpty())
				return false;
			FileUtils.writeOutputStream(PATH, GZipUtils.compressObject(list));
		}
		return false;
	}

	/**
	 * 增加商品
	 */
	@Override
	public boolean addGoods(Goods cls) {
		sortById();
		cls.setId(getNextId());
		if (isExitGoods(cls))
			return false;
		list.add(cls);
		saveGoods();
		return true;
	}

	/**
	 * 是否存在相同的cls
	 */
	@Override
	public boolean isExitGoods(Goods cls) {
		if (list.isEmpty())
			return false;
		if (list.contains(cls))
			return true;
		return false;
	}

	/**
	 * 保存用户到文件
	 */
	@Override
	public boolean saveGoods() {
		sortById();
		FileUtils.writeOutputStream(PATH, GZipUtils.compressObject(list));
		return true;
	}

	/**
	 * 删除用户
	 */
	@Override
	public boolean delGoods(Goods cls) {
		if (list.isEmpty())
			return false;
		if (list.contains(cls)) {
			list.remove(cls);
			saveGoods();
			return true;
		}
		return false;
	}

	/**
	 * 通过ID获取用户信息
	 */
	@Override
	public Goods getGoodsById(int id) {
		Iterator<Goods> it = list.iterator();
		Goods cls = new Goods();
		while (it.hasNext()) {
			if (id == (cls = it.next()).getId()) {
				return cls;
			}
		}
		return null;
	}

	/**
	 * 更新用户数据
	 */

	@Override
	public boolean updateGoods(Goods cls) {
		if (list.contains(cls)) {
			list.remove(cls);
			list.add(cls);
			saveGoods();
			sortById();
			return true;
		}
		return false;
	}

	/**
	 * 获取所有用户
	 */
	@Override
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		sortById();
		return list;
	}

	/**
	 * 按照id排序
	 */
	@Override
	public void sortById() {
		Collections.sort(list, new SortByID1());
	}

	/**
	 * 得到下一个递增id
	 */
	@Override
	public int getNextId() {
		if (list.isEmpty())
			return 0;
		sortById();
		return list.get(list.size() - 1).getId() + 1;
	}

	@Override
	public Goods getGoodsByName(String name) {
		if (list.isEmpty())
			return null;
		Iterator<Goods> it = list.listIterator();
		Goods cls;
		while (it.hasNext()) {
			cls = it.next();
			if (cls.getName().equals(name))
				return cls;
		}
		return null;
	}
}

/**
 * 排序函数
 * 
 * @author 伽蓝古风
 *
 */
class SortByID1 implements Comparator<Goods> {
	public int compare(Goods o1, Goods o2) {
		return new Integer(o1.getId()).compareTo(o2.getId());
	}
}