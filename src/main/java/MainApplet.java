package main.java;

import java.util.ArrayList;
import java.util.Random;

import controlP5.ControlP5;
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
	private int ballinside = 0;//記住球的數量
	private int index;//記住被滑鼠壓到的characters
	private int[] inside = new int[50];//記住每個characters是否圓內
	private ControlP5  cP5;
	private final static int width = 1200, height = 650;
	public static String msg = "Star War 1";//設定哪一集
	
	public void setup() {
		//設定初始化
		characters = new ArrayList<Character>();
		size(width, height);
		//圓內球的數量為0
		ballinside = 0;
		//每個characters圓內都設為0(false)
		for(int i=0;i<50;i++) {
			inside[i]=0;
		}
		cP5 = new ControlP5(this);
		//設為第一集
		msg = "Star War 1";
		//增加2個按鈕
		cP5.addButton("add").setLabel("ADD ALL").setPosition(width*4/5,height/4).setSize(200,50);
		cP5.addButton("clear").setLabel("CLEAR").setPosition(width*4/5,height*4/5).setSize(200,50);
		
		smooth();
		loadData();
		
	}
	
	public void add(){//當add按下去的時候
		//把每個球在圓內的直都設為1(代表都在圓上)
		for(int i = 0;i < characters.size(); i++){
			inside[i] = 1; 
		}
		//圓內的球的數量和characters的總數量一樣
		ballinside = characters.size()-1;
		int num = 1;
		//把所有的點平均畫在圓上
		for (int i = 0;i < characters.size(); i++) {
			if (inside[i] == 1) {
				characters.get(i).setX(600 + 300 * cos((float) (6.28/ballinside * num)));
				characters.get(i).setY(325 + 300 * sin((float) (6.28/ballinside * num)));
				num++;
			}
		}
	}
	
	public void clear(){//當clear按下去的時候
		//把每個球在圓內的直都設為0(代表都在原位)
		for(int i = 0;i < characters.size(); i++){
			inside[i] = 0; 
		}
		//圓內的球的數量設為0
		ballinside = 0;
		int x = 20,y = 20;
		//把球的xy座標設為一開始的值
		for(int i = 0;i < characters.size(); i++){
			characters.get(i).setX(x);
			characters.get(i).setY(y);
			if (y >= 600) {
				y = 20;
				x = x + 50;
			}
			else {
				y = y + 50;
			}
		}
	}

	public void draw() {
		//畫白色的底
		background(255) ;
		//畫右邊的圓
		fill(255,255,255);
		ellipse(600, 325, 600, 600);
		//畫每個角色
		for (int i = 0; i < characters.size(); i++) {
			strokeWeight(1);
			//把每個characters畫出來
			characters.get(i).display();
			//如果有球在圓裡，並且裡面的數量不只一個
			if (inside[i] == 1 && ballinside > 1) {
				//把裡面的每一個link都查一次
				for(int j = 0; j < links.size();j++){
					int s = links.getJSONObject(j).getInt("source");
					int t = links.getJSONObject(j).getInt("target");
					//source跟i一樣，代表你找到圓內的characters
					if (i == s) {
						//如果target也在圓內(inside[t] == 1)，就把2個連起來，必且讀到value，設定粗細
						if (inside[t] == 1) {
							strokeWeight(links.getJSONObject(j).getInt("value"));
							line(characters.get(s).x, characters.get(s).y,
									characters.get(t).x, characters.get(t).y);
							strokeWeight(1);
							
						}
					}
				}
				
			}
		}
		
	}

	private void loadData(){
		//讀檔，和Lab8一樣
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
		for (int i = 0; i < links.size(); i++) {
			int source = links.getJSONObject(i).getInt("source");
			int target = links.getJSONObject(i).getInt("target");
			this.characters.get(source).addTarget(this.characters.get(target));
		}
	}
	
	public void mousePressed() {//當滑鼠壓下去的時候
		float dis = 0;
		for (int i = 0; i < characters.size(); i++) {
			//算距離
			dis = (mouseX - characters.get(i).x) * (mouseX - characters.get(i).x) +
					(mouseY - characters.get(i).y) * (mouseY - characters.get(i).y);
			//當距離小於半徑時
			if (dis < 100) {
				//設為被選到，使得能夠跟著滑鼠走
				characters.get(i).isSelected = true;
				index = i;
				break;
			}
		}
		redraw();
	}
	
<<<<<<< HEAD
	public void mouseDragged() {//當滑鼠壓下去時，characters會跟著滑鼠
		if (characters.get(index).isSelected) {
			characters.get(index).setX(mouseX);
			characters.get(index).setY(mouseY);
		}
	}
	
	public void mouseReleased() {//當滑鼠放下時要做的事情
=======
	public void mouseReleased() {
>>>>>>> 2ef7f031a3b38c50f3ed4d50382d98542fc1212c
		if (characters.get(index).isSelected) {
			characters.get(index).isSelected = false;
			
			float dis = 0;
			//圓圈跟那顆球的距離
			dis = (600 - characters.get(index).x) * (600 - characters.get(index).x) +
					(325 - characters.get(index).y) * (325 - characters.get(index).y);
			//距離小於半徑的時候
			if (dis < 90000) {
				//裡面球的數量加一
				ballinside ++;
				//設參數記住哪些球再圓圈內
				inside[index] = 1;
				int num = 1;
				for (int i = 0;i < 50; i++) {
					//當期在圓圈裡，從圓最左邊，逆時針平均角度一個一個加上去
					if (inside[i] == 1) {
						characters.get(i).setX(600 + 300 * cos((float) (6.28/ballinside * num)));
						characters.get(i).setY(325 + 300 * sin((float) (6.28/ballinside * num)));
						num++;
					}
				}
			}
			
		}
	}
	
<<<<<<< HEAD
	public void keyPressed(KeyEvent e) {//設定哪一集，設定file的檔名，並起要初始化值
		if (e.getKeyCode() == 49) {//1
			characters.clear();
			file = "main/resources/starwars-episode-1-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 1";
		}
		else if (e.getKeyCode() == 50) {//2
			characters.clear();
			file = "main/resources/starwars-episode-2-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 2";
		}
		else if (e.getKeyCode() == 51) {//3
			characters.clear();
			file = "main/resources/starwars-episode-3-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 3";
		}
		else if (e.getKeyCode() == 52) {//4
			characters.clear();
			file = "main/resources/starwars-episode-4-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 4";
		}
		else if (e.getKeyCode() == 53) {//5
			characters.clear();
			file = "main/resources/starwars-episode-5-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 5";
		}
		else if (e.getKeyCode() == 54) {//6
			characters.clear();
			file = "main/resources/starwars-episode-6-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 6";
		}
		else if (e.getKeyCode() == 55) {//7
			characters.clear();
			file = "main/resources/starwars-episode-7-interactions.json";
			loadData();
			for(int i=0;i<50;i++) {
				inside[i]=0;
			}
			ballinside = 0;
			msg = "Star War 7";
		}
	}
=======
>>>>>>> 2ef7f031a3b38c50f3ed4d50382d98542fc1212c
	
}
