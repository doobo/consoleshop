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
		System.out.println("请输入两个数，用空格隔开");
		sc = new Scanner(System.in);
		String str = sc.nextLine();
		while(str.split(" ").length!=2){
			System.out.println("你输入的数据不合法，数据个数不为2，请重新输入");
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
	
	
	
	
	
	
	
	
	//随机得到指定范围的整数
	 public  int getIntRang(int min ,int max){
	    	return  (int) Math.round(Math.random()*(max-min)+min);
	    }
	 
	 @Test
	public void testRand(){
		int arr[]  = new int[10];
		for(int i=0;i<arr.length;i++){
			//得到0-100的随机整数
			arr[i] =  getIntRang(0, 100);
		}
		//排序
		Arrays.sort(arr);
		for (int i : arr) {
			System.out.print(i+"\t");
		}
		System.out.println();
		int ar[] = new int[5];
		for(int i = 0;i<ar.length;i++){
			//得到1到23（包含23）之间的整数
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

		String value = "aaa"; // 长度不够
		System.out.println(value.matches(regex));

		value = "1111aaaa1111aaaaa"; // 太长
		System.out.println(value.matches(regex));

		value = "111111111"; // 纯数字
		System.out.println(value.matches(regex));

		value = "aaaaaaaaa"; // 纯字母
		System.out.println(value.matches(regex));

		value = "####@@@@#"; // 特殊字符
		System.out.println(value.matches(regex));

		value = "1111aaaa"; // 数字字母组合
		System.out.println(value.matches(regex));

		value = "aaaa1111"; // 数字字母组合
		System.out.println(value.matches(regex));

		value = "aa1111aa"; // 数字字母组合
		System.out.println(value.matches(regex));

		value = "11aaaa11"; // 数字字母组合
		System.out.println(value.matches(regex));

		value = "aa11aa11"; // 数字字母组合
		System.out.println(value.matches(regex));
	}

	@Test
	public void testWork() {
		for (int d = 0; d < 4; d++) {// 控制行数
			for (int e = 4; e > d; e--) {// 控制空白
				System.out.print(" ");
			}
			for (int f = 0; f <= 2 * d; f++) {// 控制*
				System.out.print("*");
			}
			System.out.println(" ");
		}
	}

	@Test
	public void runnian() {
		int i = 2000;
		while (i <= 2100) {
			// 闰年计算规则：
			// 1. 非整百年能被4整除的为闰年
			// 2. 能被400整除的是闰年
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
		System.out.println("请输入一个整数,输入0结束");
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
		System.out.println("输入的总和是："+count);
		System.out.println("其中的偶数有：");
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
			System.out.println("你输入的不是整数！请重新输入");
			getScannerToInt("");
		}
		return i;
	}
}
 


