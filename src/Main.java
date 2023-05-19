package src;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int shipSpawnCounter = 0;
	PImage ship, turret, bullet, background;

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		ship = loadImage("../assets/alien_cropped.png");
		turret = loadImage("../assets/turret_complete.png");
//		bullet = loadImage("bullet.png");
	}

	public void draw() {
		background(0);

		shipSpawnCounter += (int)(Math.random() * 10);
		if (shipSpawnCounter > 200) {
			shipList.add(new Ship(ship));
			shipSpawnCounter = 0;
		}

		for (int i = 0; i < shipList.size(); i++) {
			shipList.get(i).act();
			shipList.get(i).draw(this);
			if(destroyOffscreenShip(i)){
				i--;
			}
		}
	}

	public boolean destroyOffscreenShip(int i){
		if (shipList.get(i).isOffScreen(width, height)) {
			shipList.remove(i);
			return true;
		}
		return false;
	}


	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}