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
		turret.resize(100, 100);
//		bullet = loadImage("bullet.png");
	}

	public void draw() {
		background(0);
		fill(255);
		rect(0, height - 50, width, 50);
		image(turret, width/2 - 50, height - 150);

		shipSpawnCounter += (int)(Math.random() * 10);
		if (shipSpawnCounter > 200) {
			shipList.add(new Ship(ship));
			shipSpawnCounter = 0;
		}

		for (int i = 0; i < shipList.size(); i++) {
			shipList.get(i).act();
			shipList.get(i).draw(this);
			if (destroyOffscreenShips(i)) {
				i--;
			}
		}

		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).act();
			bulletList.get(i).draw(this);
			if (destroyOffscreenBullets(i)) {
				i--;
			}
		}
	}

	public boolean destroyOffscreenShips(int i) {
		if (shipList.get(i).isOffScreen(width, height)) {
			shipList.remove(i);
			return true;
		}
		return false;
	}

	public boolean destroyOffscreenBullets(int i) {
		if (bulletList.get(i).isOffScreen(width, height)) {
			bulletList.remove(i);
			return true;
		}
		return false;
	}

	public void mouseReleased() {
		bulletList.add(new Bullet(640, 570, (float)(Math.PI/2)));
	}

	public void keyReleased() {
		if (key == ' ') {
			bulletList.add(new Bullet(640, 570, (float)(Math.PI/2)));
		}
	}


	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}