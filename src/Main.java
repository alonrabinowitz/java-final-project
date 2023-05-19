package src;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		for (int i = 0; i < 10; i++) {
			shipList.add(new Ship());
		}
	}

	public void draw() {

	}


	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}