package com.qrsx.shop.dao;

import com.qrsx.shop.impl.GoodsImpl;

public class GoodsFactory {
	private static GoodsDao gd = null;

	public static GoodsDao getInstance() {
		if (gd == null) {
			gd = new GoodsImpl();
		}
		return gd;
	}
}
