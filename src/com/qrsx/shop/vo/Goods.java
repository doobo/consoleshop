package com.qrsx.shop.vo;

import java.io.Serializable;

public class Goods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int price;

	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
		Goods other = (Goods) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Goods(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}

	public Goods() {
	}

	@Override
	public String toString() {

		return id + "\t\t" + name + "\t\t" + price + "\t\t" + count;
	}
}
