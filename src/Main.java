package src;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int shipSpawnCounter, bulletsLeft;
	PImage shipImg, turretImg, bulletImg, background;

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		shipSpawnCounter = 0;
		bulletsLeft = 10;
		shipImg = loadImage("../assets/obj/alien_cropped.png");
		turretImg = loadImage("../assets/obj/turret_complete.png");
		turretImg.resize(100, 100);
		bulletImg = loadImage("../assets/obj/bullet.png");
	}

	public void draw() {
		background(0);
		fill(255);
		rect(0, height - 50, width, 50);
		image(turretImg, width/2 - 50, height - 150);

		//Randomly spawn ships
		shipSpawnCounter += (int)(Math.random() * 10);
		if (shipSpawnCounter > 80) {
			shipList.add(new Ship(shipImg));
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

		for (int i = 0; i < bulletsLeft; i++){
			image(bulletImg, 10+20*i, 10, 16, 16);
		}
	}

	public void mouseReleased() {
		if (bulletsLeft > 0) {
			bulletsLeft--;
			bulletList.add(new Bullet(640, 570, 1, (float) (Math.PI / 2), bulletImg));
		}
	}

	public void keyReleased() {
		if (key == ' ') {
			if (bulletsLeft > 0) {
				bulletList.add(new Bullet(640, 570, 1, (float)(Math.PI/2), bulletImg));
				bulletsLeft--;
			}
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}
