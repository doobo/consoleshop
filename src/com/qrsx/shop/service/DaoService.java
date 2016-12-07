package com.qrsx.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qrsx.shop.dao.BuyGoodsFactory;
import com.qrsx.shop.dao.GoodsFactory;
import com.qrsx.shop.dao.UserFactory;
import com.qrsx.shop.vo.BuyGoods;
import com.qrsx.shop.vo.Goods;
import com.qrsx.shop.vo.User;

public class DaoService {

	/**
	 * ����û�
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public static boolean addUser(String name, String pwd) {
		if (UserFactory.getInstance().addUser(new User(name, pwd))) {
			return true;
		}
		return false;

	}

	/**
	 * �ж��Ƿ�����û�
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isExitUser(String name) {
		if (UserFactory.getInstance().isExitUser(new User(name, null))) {
			return true;
		}
		return false;
	}

	/**
	 * ��¼��֤
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	public static User tologin(String name, String pwd) {
		User user = UserFactory.getInstance().getUserByName(name);
		if (user.getPwd().equals(pwd))
			return user;
		return null;
	}

	// �����Ʒ
	public static boolean addShop(String name, int price, int count) {
		if (GoodsFactory.getInstance().addGoods(new Goods(name, price, count))) {
			return true;
		}
		return false;
	}

	/**
	 * �鿴��Ʒ�б�
	 */
	public static void showGoodList() {
		List<Goods> list = GoodsFactory.getInstance().getAllGoods();
		if (list != null) {
			for (Goods goods : list) {
				System.out.printf("%8s", "��ƷID\t��Ʒ���� \t��Ʒ�۸� \t��Ʒ����\n");
				// System.out.printf("%4d%4s%4d%4d%s",goods.getId(),goods.getName(),goods.getPrice(),goods.getCount(),"\n");
				System.out.println(goods);
			}
		} else {
			System.out.println("����Ա��û�������Ʒ��");
		}
	}

	// ɾ����Ʒ
	public static boolean delGoods(int id) {
		Goods gd = new Goods();
		gd.setId(id);
		if (GoodsFactory.getInstance().delGoods(gd)) {
			return true;
		}
		return false;
	}

	// ������Ʒ�б�
	public static boolean updeGoods(int id, String name, int price, int count) {
		Goods gd = new Goods(name, price, count);
		gd.setId(id);
		if (GoodsFactory.getInstance().updateGoods(gd)) {
			return true;
		}

		return false;
	}

	// ��ʾָ��id������
	public static boolean showGoodsById(int id) {
		Goods gd = GoodsFactory.getInstance().getGoodsById(id);
		if (gd == null) {
			System.out.println("�����ڸ�ID����Ʒ");
			return false;
		} else {
			System.out.println("��Ʒ���\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����\t");
			System.out.println(gd);
			return true;
		}
	}

	// ��ʾָ�����ֵ�����
	public static boolean showGoodsByName(String name) {
		Goods gd = GoodsFactory.getInstance().getGoodsByName(name);
		if (gd == null) {
			System.out.println("�����ڸ����ֵ���Ʒ");
			return false;
		} else {
			System.out.println("��Ʒ���\t��Ʒ����\t��Ʒ�۸�\t��Ʒ����\t");
			System.out.println(gd);
			return true;
		}
	}

	// �޸���Ʒ��������
	public static boolean updateCount(int id, int count) {
		Goods gd = GoodsFactory.getInstance().getGoodsById(id);
		gd.setCount(count);
		if (GoodsFactory.getInstance().updateGoods(gd)) {
			return true;
		}
		return false;
	}

	// ������Ʒ���������жϹ�����Ʒ�Ƿ񳬳�����
	public static boolean isOverCountGoods(int id, int count) {
		Goods gd = GoodsFactory.getInstance().getGoodsById(id);
		if (count < 0)
			return true;
		if (gd == null)
			return true;
		if (gd.getCount() < count) {
			return true;
		}
		return false;
	}

	// �޸���Ʒ�������¼��������Ʒ
	public static boolean updateAll(User user, int gid, int count) {
		// System.out.println(user);
		Goods gd = GoodsFactory.getInstance().getGoodsById(gid);
		gd.setCount(gd.getCount() - count);
		// System.out.println(gd);
		GoodsFactory.getInstance().updateGoods(gd);
		GoodsFactory.getInstance().sortById();
		BuyGoods bg = new BuyGoods(user.getId(), gid, count, new Date());
		bg.setPrice(gd.getPrice() * count);
		bg.setName(gd.getName());
		// System.out.println(bg);
		BuyGoodsFactory.getInstance().addBuyGoods(bg);
		return true;
	}

	public static boolean showAllBuy(int uid) {
		List<BuyGoods> list = BuyGoodsFactory.getInstance().getAllBuyGoods();
		List<BuyGoods> temp = new ArrayList<BuyGoods>();
		for (BuyGoods gb : list) {
			if (gb.getUid() == uid)
				temp.add(gb);
		}
		if (temp.isEmpty()) {
			System.out.println("�㻹ô�й����κ���Ʒ������ѡ��鿴�̳ǹ�����Ʒ��");
			return false;
		}
		System.out.println("�����������Ʒ����");
		int allcount = 0;
		for (BuyGoods bg : temp) {
			System.out.println("��ƷID:" + bg.getGid() + "\t��Ʒ���֣�" + bg.getName() + "  ��������:" + bg.getCount() + "  �ܼ۸�"
					+ bg.getPrice());
			allcount = allcount + bg.getPrice();
		}
		System.out.println("���е���Ʒ���ܼ�Ϊ��" + allcount);
		return true;
	}
}
