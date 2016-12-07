package com.qrsx.shop.dao;

import java.util.List;

import com.qrsx.shop.vo.BuyGoods;

public interface BuyGoodsDao {

	boolean initBuyGoodsDao();

	boolean addBuyGoods(BuyGoods cls);

	boolean isExitBuyGoods(BuyGoods cls);

	boolean saveBuyGoods();

	boolean updateBuyGoods(BuyGoods cls);

	boolean delBuyGoods(BuyGoods cls);

	BuyGoods getBuyGoodsById(int id);

	BuyGoods getBuyGoodsByName(String name);

	List<BuyGoods> getAllBuyGoods();

	void sortById();

	int getNextId();
}
