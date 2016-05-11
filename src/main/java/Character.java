package main.java;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;

//import javax.swing.Box.Filler;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	public float x, y, radius;
	private String name;
	private String colour;
	private ArrayList<Character> targets = new ArrayList<Character>();
	boolean displayName = false;
	boolean isSelected = false;
	private int hi;
	
	private String r,g,b,a;
	private float floatR,floatG,floatB,floatA;
	
	private MainApplet parent;

	public Character(MainApplet parent, String name, float x, float y, String colour){
		this.name = name;
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.colour = colour;
	}

	public void display(){
		
		r = colour.substring(1,3);
		g = colour.substring(3,5);
		b = colour.substring(5,7);
		a = colour.substring(7,9);
		
		floatR = Integer.parseInt(r, 16);
		floatG = Integer.parseInt(g, 16);
		floatB = Integer.parseInt(b, 16);
		floatA = Integer.parseInt(a, 16);
		
		this.parent.fill(floatR, floatG, floatB, floatA);
		this.parent.ellipse(x, y, 20, 20);
		//this.parent.rect(x, y, 100, 10);
		//this.parent.fill(255,255,255);
		//this.parent.text(name, x, y+10);
		//System.out.println(this.x);
		//System.out.println(this.y);
	}
	
	public void displayName () {
		this.parent.fill(256, 256, 256);
		this.parent.rect(x, y, 100, 10);
		this.parent.fill(250, 130, 220);
		this.parent.text(name, x, y+10);
	}
	
	public void setX(float x) {
		this.x = x;
		//System.out.println(this.x);
	}
	
	public void setY(float y) {
		this.y = y;
		//System.out.println(this.y);
	}
	
	/*public void addTarget(Character target){
		this.targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}*/
	
}
