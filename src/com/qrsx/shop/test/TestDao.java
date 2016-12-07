package com.qrsx.shop.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import org.junit.Test;

import com.qrsx.shop.dao.UserFactory;
import com.qrsx.shop.vo.User;

public class TestDao {

	private Scanner sc ;

	@Test
	public void testScanner(){
		System.out.println("���������������ÿո����");
		sc = new Scanner(System.in);
		String str = sc.nextLine();
		while(str.split(" ").length!=2){
			System.out.println("����������ݲ��Ϸ������ݸ�����Ϊ2������������");
			str = sc.nextLine();
		}
		
		String md5 = "D05E53DF";
		md5.toUpperCase();
		System.out.println(md5);
		System.out.println(Long.parseLong(md5, 16));
		for (String ss : str.split(" ")) {
				System.out.println(ss);
				try {
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
}
	
	
	
	
	
	
	
	
	//����õ�ָ����Χ������
	 public  int getIntRang(int min ,int max){
	    	return  (int) Math.round(Math.random()*(max-min)+min);
	    }
	 
	 @Test
	public void testRand(){
		int arr[]  = new int[10];
		for(int i=0;i<arr.length;i++){
			//�õ�0-100���������
			arr[i] =  getIntRang(0, 100);
		}
		//����
		Arrays.sort(arr);
		for (int i : arr) {
			System.out.print(i+"\t");
		}
		System.out.println();
		int ar[] = new int[5];
		for(int i = 0;i<ar.length;i++){
			//�õ�1��23������23��֮�������
			ar[i] = getIntRang(1, 23);
		}
		for (int i : ar) {
			System.out.print(i+"\t");
		}
	}
	
	 
	 
	 
	
	@Test
	public void testBook(){
		ArrayList<Book> list = new ArrayList<>();
		list.add(new Book("book1", 89));
		list.add(new Book("book2", 75));
		list.add(new Book("book3", 99));
		list.add(new Book("book4", 23));
		list.add(new Book("book5", 56));
		Collections.sort(list);
		for (Book book : list) {
			System.out.println(book);
		}
	}
	
	
	@Test
	public void testGoods(){
		User  user = new User("admin", "admin");
		UserFactory.getInstance().addUser(user);
	}
	
	
	@Test
	public void test() {

		// UserFactory.getInstance().delUser(new User("google", "dksks"));
		//

		// UserFactory.getInstance().updateUser(new User("Google","569"));

		// UserFactory.getInstance().delUser(new User("Google",null));
		//
		// UserFactory.getInstance().addUser(new User("360", "222"));

		System.out.println(UserFactory.getInstance().addUser(new User("admin", "admin")));

		for (User user : UserFactory.getInstance().getAllUser()) {
			System.out.println(user);
		}
	}

	@Test
	public void testRegx() {
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

		String value = "aaa"; // ���Ȳ���
		System.out.println(value.matches(regex));

		value = "1111aaaa1111aaaaa"; // ̫��
		System.out.println(value.matches(regex));

		value = "111111111"; // ������
		System.out.println(value.matches(regex));

		value = "aaaaaaaaa"; // ����ĸ
		System.out.println(value.matches(regex));

		value = "####@@@@#"; // �����ַ�
		System.out.println(value.matches(regex));

		value = "1111aaaa"; // ������ĸ���
		System.out.println(value.matches(regex));

		value = "aaaa1111"; // ������ĸ���
		System.out.println(value.matches(regex));

		value = "aa1111aa"; // ������ĸ���
		System.out.println(value.matches(regex));

		value = "11aaaa11"; // ������ĸ���
		System.out.println(value.matches(regex));

		value = "aa11aa11"; // ������ĸ���
		System.out.println(value.matches(regex));
	}

	@Test
	public void testWork() {
		for (int d = 0; d < 4; d++) {// ��������
			for (int e = 4; e > d; e--) {// ���ƿհ�
				System.out.print(" ");
			}
			for (int f = 0; f <= 2 * d; f++) {// ����*
				System.out.print("*");
			}
			System.out.println(" ");
		}
	}

	@Test
	public void runnian() {
		int i = 2000;
		while (i <= 2100) {
			// ����������
			// 1. ���������ܱ�4������Ϊ����
			// 2. �ܱ�400������������
			if ((i % 100 != 0 && i % 4 == 0) || i % 400 == 0) {
				System.out.println(i);
			}
			i++;
		}
	}

	@Test
	public void getSacaner() {
		int input = -1;
		int arr[] = new int[10];
		int i = 0;
		System.out.println("������һ������,����0����");
		while(input!=0){
			input = getScannerToInt("");
			if(i>arr.length-1){
				int[] temp = arr;
 				arr = new int[2*10];
 				int j = 0;
 				for (int k : temp) {
					arr[j++] = k;
				}
 				arr[i++] = input;
			}else{
				arr[i++] = input;
			}
		}
		int count=0;
		for (int m : arr) {
			count = count +m;
		}
		System.out.println("������ܺ��ǣ�"+count);
		System.out.println("���е�ż���У�");
		for (int m : arr) {
			if(m!=0&&m%2==0){
				System.out.print(m+"\t");
			}
		}
	}

	public int getScannerToInt(String info) {
		int i = 0;
		System.out.print(info);
		sc = new Scanner(System.in);
		try {
			i = sc.nextInt();
			return i;
		} catch (Exception e) {
			System.out.println("������Ĳ�������������������");
			getScannerToInt("");
		}
		return i;
	}
}
 


