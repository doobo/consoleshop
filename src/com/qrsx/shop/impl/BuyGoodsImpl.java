package com.qrsx.shop.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.qrsx.shop.dao.BuyGoodsDao;
import com.qrsx.shop.utils.FileUtils;
import com.qrsx.shop.utils.GZipUtils;
import com.qrsx.shop.vo.BuyGoods;

public class BuyGoodsImpl implements BuyGoodsDao {

	private static final String PATH = "buygoods.conf";
	private static List<BuyGoods> list;

	{
		list = new ArrayList<BuyGoods>();
		initBuyGoodsDao();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean initBuyGoodsDao() {
		File file = new File(PATH);
		if (file.exists()) {
			list = (List<BuyGoods>) GZipUtils.decompressObject(FileUtils.readInputStream(PATH));
			return true;
		} else {
			if (list.isEmpty())
				return false;
			FileUtils.writeOutputStream(PATH, GZipUtils.compressObject(list));
		}
		return false;
	}

	/**
	 * ������Ʒ
	 */
	@Override
	public boolean addBuyGoods(BuyGoods cls) {
		cls.setId(getNextId());
		if (isExitBuyGoods(cls))
			return false;
		list.add(cls);
		saveBuyGoods();
		return true;
	}

	/**
	 * �Ƿ������ͬ��cls
	 */
	@Override
	public boolean isExitBuyGoods(BuyGoods cls) {
		if (list.isEmpty())
			return false;
		if (list.contains(cls))
			return true;
		return false;
	}

	/**
	 * �����û����ļ�
	 */
	@Override
	public boolean saveBuyGoods() {
		FileUtils.writeOutputStream(PATH, GZipUtils.compressObject(list));
		return true;
	}

	/**
	 * ɾ���û�
	 */
	@Override
	public boolean delBuyGoods(BuyGoods cls) {
		if (list.isEmpty())
			return false;
		if (list.contains(cls)) {
			list.remove(cls);
			saveBuyGoods();
			return true;
		}
		return false;
	}

	/**
	 * ͨ��ID��ȡ�û���Ϣ
	 */
	@Override
	public BuyGoods getBuyGoodsById(int id) {
		Iterator<BuyGoods> it = list.iterator();
		BuyGoods cls = new BuyGoods();
		while (it.hasNext()) {
			if (id == (cls = it.next()).getId()) {
				return cls;
			}
		}
		return null;
	}

	/**
	 * �����û�����
	 */

	@Override
	public boolean updateBuyGoods(BuyGoods cls) {
		if (list.contains(cls)) {
			list.remove(cls);
			list.add(cls);
			saveBuyGoods();
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ�����û�
	 */
	@Override
	public List<BuyGoods> getAllBuyGoods() {
		// TODO Auto-generated method stub
		sortById();
		return list;
	}

	/**
	 * ����id����
	 */
	@Override
	public void sortById() {
		Collections.sort(list, new SortByID3());
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
	public BuyGoods getBuyGoodsByName(String name) {
		return null;
	}
}

/**
 * ������
 * 
 * @author ٤���ŷ�
 *
 */
class SortByID3 implements Comparator<BuyGoods> {
	public int compare(BuyGoods o1, BuyGoods o2) {
		return new Integer(o1.getId()).compareTo(o2.getId());
	}
}