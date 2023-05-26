package src;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int shipSpawnCounter, bulletsLeft;
	PImage shipImg, turretImg, bulletImg, turretBaseImg, turretCanonImg;
	BG background;
	Score score;

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		shipSpawnCounter = 0;
		bulletsLeft = 10;
		shipImg = loadImage("../assets/obj/alien_cropped.png");
		turretImg = loadImage("../assets/obj/turret_complete.png");
		turretImg.resize(100, 100);
		turretBaseImg = loadImage("../assets/obj/turret_base.png");
		turretCanonImg = loadImage("../assets/obj/turret_cannon.png");
		bulletImg = loadImage("../assets/obj/bullet.png");
		background = new BG(loadImage("../assets/BG/bldg_fg.png"), loadImage("../assets/BG/bldg_mg1.png"), loadImage("../assets/BG/bldg_mg2.png"), loadImage("../assets/BG/city.png"), loadImage("../assets/BG/hills.png"), loadImage("../assets/BG/farm.png"));
		score = new Score();
	}

	public void draw() {
		background(200,130,0);
		fill(255);
		rect(0, height - 50, width, 50);
		background.draw(this);
//		image(turretBaseImg, width/2 - 50, height - 150, 100, 100);
		image(turretImg, width/2 - 50, height - 150);
//		drawTurret(width/2 - 50, height - 150);
//		drawTurret(500, 300);

		//Randomly spawn ships
		shipSpawnCounter += (int)(Math.random() * 10);
		if (shipSpawnCounter > 50) {
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
			image(bulletImg, 17 + 30 * i, 17, 20, 20);
		}

		score.draw(this);
	}

	public void mouseReleased() {
		if (bulletsLeft > 0) {
			bulletsLeft--;
			bulletList.add(new Bullet(640, 570, 1, (float)(Math.PI / 2), bulletImg));
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

	public void drawTurret(int x, int y) {
		if(mouseX > x){
			translate(x+50, y+100);
//			rotate((float) Math.PI/2 + (float)(Math.atan((float)(mouseY - y+50)/(mouseX - x+50))));
			rotate((float) ((2 * Math.PI) - Math.atan((double) (mouseX - (x + 50)) / ((y+100)-mouseY))));
			image(turretCanonImg, -50, -100, 100, 100);
//			rotate(-((float) Math.PI/2 + (float)(Math.atan((float)(mouseY - y+50)/(mouseX - x+50)))));
			rotate((float) -((2 * Math.PI) - Math.atan((double) (mouseX - (x + 50)) / ((y+100)-mouseY))));
			translate(-x-50, -y-100);
		}
//		else if (mouseX < x) {
//			translate(x+50, y+100);
//			rotate((float) Math.PI/2 + (float)(Math.atan((float)(mouseY - y+50)/(mouseX - x+50))));
//			image(turretCanonImg, -50, -100, 100, 100);
//			rotate(-((float) Math.PI/2 + (float)(Math.atan((float)(mouseY - y+50)/(mouseX - x+50)))));
//			translate(-x-50, -y-100);
//		} else {
//			translate(x+50, y+100);
//			rotate((float) Math.PI/2);
//			image(turretCanonImg, -50, -100, 100, 100);
//			rotate(-((float) Math.PI/2));
//			translate(-x-50, -y-100);
//		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}
