package com.qrsx.shop.test;

public class TestWork {
	public static void main(String[] args) {
		Box box = new Box();
		box.setFruit(new Apple("粉红", 200));
		box.showColorAndHeight();
	}
}

class Box{
	private Fruit fruit;

	public Fruit getFruit() {
		return fruit;
	}

	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
	public void showColorAndHeight(){
		if(fruit==null){
			System.out.println("你还没有添加任何水果！");
		}else{
			System.out.println(fruit.name+"\t"+fruit.color+"\t"+fruit.weight);
		}
	}
}

class Fruit{
	String name;
	int weight;
	String color;
}

class Apple extends Fruit{
	public Apple(String color,int weight){
		super.name = "Apple";
		super.color = color;
		super.weight = weight;
	}
}

class Orange extends Fruit{
	public Orange(String color,int weight){
		super.name = "Orange";
		super.color = color;
		super.weight = weight;
	}
}

class Pear extends Fruit{
	public Pear(String color,int weight){
		super.name = "Pear";
		super.color = color;
		super.weight = weight;
	}
}

