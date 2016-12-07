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
	 * 添加用户
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
	 * 判断是否存在用户
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
	 * 登录验证
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

	// 添加商品
	public static boolean addShop(String name, int price, int count) {
		if (GoodsFactory.getInstance().addGoods(new Goods(name, price, count))) {
			return true;
		}
		return false;
	}

	/**
	 * 查看商品列表
	 */
	public static void showGoodList() {
		List<Goods> list = GoodsFactory.getInstance().getAllGoods();
		if (list != null) {
			for (Goods goods : list) {
				System.out.printf("%8s", "商品ID\t商品名称 \t商品价格 \t商品数量\n");
				// System.out.printf("%4d%4s%4d%4d%s",goods.getId(),goods.getName(),goods.getPrice(),goods.getCount(),"\n");
				System.out.println(goods);
			}
		} else {
			System.out.println("管理员还没有添加商品！");
		}
	}

	// 删除商品
	public static boolean delGoods(int id) {
		Goods gd = new Goods();
		gd.setId(id);
		if (GoodsFactory.getInstance().delGoods(gd)) {
			return true;
		}
		return false;
	}

	// 更新商品列表
	public static boolean updeGoods(int id, String name, int price, int count) {
		Goods gd = new Goods(name, price, count);
		gd.setId(id);
		if (GoodsFactory.getInstance().updateGoods(gd)) {
			return true;
		}

		return false;
	}

	// 显示指定id的数据
	public static boolean showGoodsById(int id) {
		Goods gd = GoodsFactory.getInstance().getGoodsById(id);
		if (gd == null) {
			System.out.println("不存在该ID的商品");
			return false;
		} else {
			System.out.println("商品编号\t商品名称\t商品价格\t商品数量\t");
			System.out.println(gd);
			return true;
		}
	}

	// 显示指定名字的数据
	public static boolean showGoodsByName(String name) {
		Goods gd = GoodsFactory.getInstance().getGoodsByName(name);
		if (gd == null) {
			System.out.println("不存在该名字的商品");
			return false;
		} else {
			System.out.println("商品编号\t商品名称\t商品价格\t商品数量\t");
			System.out.println(gd);
			return true;
		}
	}

	// 修改商品的总数量
	public static boolean updateCount(int id, int count) {
		Goods gd = GoodsFactory.getInstance().getGoodsById(id);
		gd.setCount(count);
		if (GoodsFactory.getInstance().updateGoods(gd)) {
			return true;
		}
		return false;
	}

	// 更加商品的总量，判断购买商品是否超出限制
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

	// 修改商品，保存记录，排序商品
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
			System.out.println("你还么有购买任何商品，可以选择查看商城购买商品！");
			return false;
		}
		System.out.println("购买的所有商品如下");
		int allcount = 0;
		for (BuyGoods bg : temp) {
			System.out.println("商品ID:" + bg.getGid() + "\t商品名字：" + bg.getName() + "  购买数量:" + bg.getCount() + "  总价格："
					+ bg.getPrice());
			allcount = allcount + bg.getPrice();
		}
		System.out.println("所有的商品的总价为：" + allcount);
		return true;
	}
}
