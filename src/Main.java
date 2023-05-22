package src;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	PImage ship, turret, bullet, background;
	int shipSpawnCounter, bulletsLeft;

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		ship = loadImage("../assets/alien_cropped.png");
		turret = loadImage("../assets/turret_complete.png");
		turret.resize(100, 100);
		bullet = loadImage("../assets/bullet.png");
		shipSpawnCounter = 0;
		bulletsLeft = 10;
	}

	public void draw() {
		background(0);
		fill(255);
		rect(0, height - 50, width, 50);
		image(turret, width/2 - 50, height - 150);

		//Randomly spawn ships
		shipSpawnCounter += (int)(Math.random() * 10);
		if (shipSpawnCounter > 200) {
			shipList.add(new Ship(ship));
			shipSpawnCounter = 0;
		}

		//Act and draw ships
		for (int i = 0; i < shipList.size(); i++) {
			Ship ship = shipList.get(i);
			ship.act(this);
			if (ship.act(this)) i--;
		}

		//Act and draw bullets
		for (int i = 0; i < bulletList.size(); i++) {
			Bullet bullet = bulletList.get(i);
			if (bullet.act(this)) i--;
		}

		for(int i = 0; i < bulletsLeft; i++){
			image(bullet, 10+20*i, 10, 10, 24);
		}
	}

	public void mouseReleased() {
		bulletList.add(new Bullet(640, 570, (float)(Math.PI/2), bullet));
		bulletsLeft--;
	}

	public void keyReleased() {
		if (key == ' ') {
			bulletList.add(new Bullet(640, 570, (float)(Math.PI/2), bullet));
			bulletsLeft--;
		}
	}


	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}