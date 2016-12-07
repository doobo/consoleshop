package com.qrsx.shop.test;

public class Book implements Comparable<Book> {

	private String bookName;
	private int price;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Book(String bookName, int price) {
		super();
		this.bookName = bookName;
		this.price = price;
	}
	public Book() {
	}
	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		return new Integer(this.price).compareTo(o.getPrice());
	}
	@Override
	public String toString() {
		return "Book [书名=" + bookName + ", 价格=" + price + "]";
	}
}
