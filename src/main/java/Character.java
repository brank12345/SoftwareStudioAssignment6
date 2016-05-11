package main.java;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

import javax.xml.soap.Text;

//import javax.swing.Box.Filler;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public float x, y, radius;
	private String name;
	private String colour;//character的顏色
	private ArrayList<Character> targets = new ArrayList<Character>();
	boolean isSelected = false;//求是否被滑鼠壓到得值
	
	private String r,g,b,a;//RGB的字串
	private float floatR,floatG,floatB,floatA;//記住RGB轉成10進位的值
	
	private MainApplet parent;

	public Character(MainApplet parent, String name, float x, float y, String colour){
		//建構一個新的
		this.name = name;
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.colour = colour;
		this.isSelected = false;
	}

	public void display(){
		//把RGb三色分別找出來，還有透明度a
		r = colour.substring(1,3);
		g = colour.substring(3,5);
		b = colour.substring(5,7);
		a = colour.substring(7,9);
		//從字串轉成16進位再轉成10進位
		floatR = Integer.parseInt(r, 16);
		floatG = Integer.parseInt(g, 16);
		floatB = Integer.parseInt(b, 16);
		floatA = Integer.parseInt(a, 16);
		//把剛剛分割好的RGBa帶入
		this.parent.fill(floatR, floatG, floatB, floatA);
		this.parent.ellipse(x, y, 20, 20);
		//在上面印出是哪一集
		this.parent.fill(0, 0, 0);
		this.parent.textSize(26);
		this.parent.text(MainApplet.msg,300,50);
	}
	//當改變位置時，可以設定x座標
	public void setX(float x) {
		this.x = x;
		//System.out.println(this.x);
	}
	//當改變位置時，可以設定y座標
	public void setY(float y) {
		this.y = y;
		//System.out.println(this.y);
	}
	//增加target的內容
	public void addTarget(Character target){
		this.targets.add(target);
	}
	//回傳target的內容
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
}
