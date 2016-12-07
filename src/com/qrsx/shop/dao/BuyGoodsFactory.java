package com.qrsx.shop.dao;

import com.qrsx.shop.impl.BuyGoodsImpl;

public class BuyGoodsFactory {
	private static BuyGoodsDao bg = null;

	public static BuyGoodsDao getInstance() {
		if (bg == null) {
			bg = new BuyGoodsImpl();
		}
		return bg;
	}
}
