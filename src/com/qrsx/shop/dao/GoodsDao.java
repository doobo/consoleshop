package com.qrsx.shop.dao;

import java.util.List;

import com.qrsx.shop.vo.Goods;

public interface GoodsDao {

	boolean initGoodsDao();

	boolean addGoods(Goods goods);

	boolean isExitGoods(Goods goods);

	boolean saveGoods();

	boolean updateGoods(Goods goods);

	boolean delGoods(Goods goods);

	Goods getGoodsById(int id);

	Goods getGoodsByName(String name);

	List<Goods> getAllGoods();

	void sortById();

	int getNextId();
}
