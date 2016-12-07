package com.qrsx.shop.service;

import java.util.Scanner;

public abstract class MainMethods {
	private static Scanner sc;

	// 获取输入的数据
	public static int getScannerToInt(String info) {
		sc = new Scanner(System.in);
		System.out.print(info);
		try {
			int i = sc.nextInt();
			return i;
		} catch (Exception e) {
			throw new ShopRunTimeException("你输入的不是整数!");
		}
	}

	// 获取输入的字符(不含空格，含空格需要另外实现)
	public static String getScannerToString(String info) {
		sc = new Scanner(System.in);
		System.out.print(info);
		return sc.next();
	}

	// 显示菜单项目
	public static void showMume() {
		int lx = 15;
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.print("欢迎进入电子书城");
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.println();

		System.out.println("\t\t1.注册");
		System.out.println("\t\t2.登录");
		System.out.println("\t\t3.查看商城");
		System.out.println("\t\t4.查看我订购的商品");
		System.out.println("\t\t5.管理员登录");
		System.out.println("\t\t6.退出系统");

		lx = 48;
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.println();

		System.out.print("请选择菜单项（1-6）：");
	}

	public static void showAdminMume() {
		int lx = 18;
		for (int i = 0; i < lx - 5; i++)
			System.out.print("*");
		System.out.print("管理员菜单");
		for (int i = 0; i < lx + 5; i++)
			System.out.print("*");
		System.out.println();

		System.out.println("\t\t1.添加商品");
		System.out.println("\t\t2.修改商品");
		System.out.println("\t\t3.删除商品");
		System.out.println("\t\t4.查看商品列表");
		System.out.println("\t\t5.查询商品");
		System.out.println("\t\t6.退出");

		lx = 48;
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.println();

	}

}
