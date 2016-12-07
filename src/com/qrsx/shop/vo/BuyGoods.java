package com.qrsx.shop.vo;

import java.io.Serializable;
import java.util.Date;

public class BuyGoods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int uid;
	private int gid;
	private String name;
	private int count;
	private int price;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BuyGoods(int uid, int gid, int count, Date createTime) {
		super();
		this.uid = uid;
		this.gid = gid;
		this.count = count;
		this.createTime = createTime;
	}

	public BuyGoods() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyGoods other = (BuyGoods) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "购买者ID: " + uid + "  商品的ID： " + gid + "  购买数量:  " + count + "  总价: " + price + "  购买时间：  " + createTime;
	}

}
