package com.qrsx.shop.service;

import java.util.Scanner;

public abstract class MainMethods {
	private static Scanner sc;

	// ��ȡ���������
	public static int getScannerToInt(String info) {
		sc = new Scanner(System.in);
		System.out.print(info);
		try {
			int i = sc.nextInt();
			return i;
		} catch (Exception e) {
			throw new ShopRunTimeException("������Ĳ�������!");
		}
	}

	// ��ȡ������ַ�(�����ո񣬺��ո���Ҫ����ʵ��)
	public static String getScannerToString(String info) {
		sc = new Scanner(System.in);
		System.out.print(info);
		return sc.next();
	}

	// ��ʾ�˵���Ŀ
	public static void showMume() {
		int lx = 15;
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.print("��ӭ����������");
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.println();

		System.out.println("\t\t1.ע��");
		System.out.println("\t\t2.��¼");
		System.out.println("\t\t3.�鿴�̳�");
		System.out.println("\t\t4.�鿴�Ҷ�������Ʒ");
		System.out.println("\t\t5.����Ա��¼");
		System.out.println("\t\t6.�˳�ϵͳ");

		lx = 48;
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.println();

		System.out.print("��ѡ��˵��1-6����");
	}

	public static void showAdminMume() {
		int lx = 18;
		for (int i = 0; i < lx - 5; i++)
			System.out.print("*");
		System.out.print("����Ա�˵�");
		for (int i = 0; i < lx + 5; i++)
			System.out.print("*");
		System.out.println();

		System.out.println("\t\t1.�����Ʒ");
		System.out.println("\t\t2.�޸���Ʒ");
		System.out.println("\t\t3.ɾ����Ʒ");
		System.out.println("\t\t4.�鿴��Ʒ�б�");
		System.out.println("\t\t5.��ѯ��Ʒ");
		System.out.println("\t\t6.�˳�");

		lx = 48;
		for (int i = 0; i < lx; i++)
			System.out.print("*");
		System.out.println();

	}

}
