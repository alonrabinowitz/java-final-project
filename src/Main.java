package src;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int shipSpawnCounter = 0;

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		for (int i = 0; i < 10; i++) {
			shipList.add(new Ship());
		}
	}

	public void draw() {
		background(0);

		shipSpawnCounter += (int)(Math.random() * 10);
		if (shipSpawnCounter > 100) {
			shipList.add(new Ship());
			shipSpawnCounter = 0;
		}

		for (int i = 0; i < shipList.size(); i++) {
			shipList.get(i).act();
			shipList.get(i).draw(this);
			if (shipList.get(i).isOffScreen(width, height)) {
				shipList.remove(i);
				i--;
			}
		}
	}


	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}