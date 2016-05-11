package main.java;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.KeyEvent;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	//private String path = "";
	private String file = "main/resources/starwars-episode-1-interactions.json";
	
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private int ballinside = 0;
	private int index;
	private int[] inside = new int[50];
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		characters = new ArrayList<Character>();
		size(width, height);
		ballinside = 0;
		for(int i=0;i<50;i++) {
			inside[i]=0;
		}
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255) ;
		fill(255,255,255);
		ellipse(600, 325, 600, 600);
		for (int i = 0; i < characters.size(); i++) {
			characters.get(i).display();
		}
		//redraw();
		//number = 0;
		
	}

	private void loadData(){
		float x = 20,y = 20;
		data = loadJSONObject(file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");
		for (int i = 0; i < nodes.size(); i++) {
			String name = nodes.getJSONObject(i).getString("name");
			String colour = nodes.getJSONObject(i).getString("colour");
			Character a = new Character(this, name, x, y, colour);
			characters.add(a);
			if (y >= 600) {
				y = 20;
				x = x + 50;
			}
			else {
				y = y + 50;
			}
		}
		/*for (int i = 0; i < links.size(); i++) {
			int source = links.getJSONObject(i).getInt("source");
			int target = links.getJSONObject(i).getInt("target");
			this.characters.get(source).addTarget(this.characters.get(target));
		}*/
	}
	
	public void mousePressed() {
		float dis = 0;
		for (int i = 0; i < characters.size(); i++) {
			dis = (mouseX - characters.get(i).x) * (mouseX - characters.get(i).x) +
					(mouseY - characters.get(i).y) * (mouseY - characters.get(i).y);
			if (dis < 100) {
				characters.get(i).isSelected = true;
				index = i;
				break;
			}
		}
		redraw();
	}
	
	public void mouseDragged() {
		if (characters.get(index).isSelected) {
			characters.get(index).setX(mouseX);
			characters.get(index).setY(mouseY);
		}
	}
	
	public void mouseReleased() {
		if (characters.get(index).isSelected) {
			characters.get(index).isSelected = false;
			
			float dis = 0;
			dis = (600 - characters.get(index).x) * (600 - characters.get(index).x) +
					(325 - characters.get(index).y) * (325 - characters.get(index).y);
			if (dis < 90000) {
				ballinside ++;
				inside[index] = 1;
				int num = 1;
				for (int i = 0;i < 50; i++) {
					if (inside[i] == 1) {
						characters.get(i).setX(600 + 300 * cos((float) (6.28/ballinside * num)));
						characters.get(i).setY(325 + 300 * sin((float) (6.28/ballinside * num)));
						num++;
					}
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 49) {//1
			characters.clear();
			file = "main/resources/starwars-episode-1-interactions.json";
			loadData();
		}
		else if (e.getKeyCode() == 50) {//2
			characters.clear();
			file = "main/resources/starwars-episode-2-interactions.json";
			loadData();
		}
		else if (e.getKeyCode() == 51) {//3
			characters.clear();
			file = "main/resources/starwars-episode-3-interactions.json";
			loadData();
		}
		else if (e.getKeyCode() == 52) {//4
			characters.clear();
			file = "main/resources/starwars-episode-4-interactions.json";
			loadData();
		}
		else if (e.getKeyCode() == 53) {//5
			characters.clear();
			file = "main/resources/starwars-episode-5-interactions.json";
			loadData();
		}
		else if (e.getKeyCode() == 54) {//6
			characters.clear();
			file = "main/resources/starwars-episode-6-interactions.json";
			loadData();
		}
		else if (e.getKeyCode() == 55) {//7
			characters.clear();
			file = "main/resources/starwars-episode-7-interactions.json";
			loadData();
		}
	}
	
}
