package src;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int gameState;
	int shipSpawnCounter, bulletsLeft;
	PImage shipImg, turretImg, bulletImg;
	BG background;
	Score score;

	public void settings() {
		size(1280, 720);
	}

	public void setup() {
		gameState = 0;
		shipSpawnCounter = 0;
		bulletsLeft = 10;
		shipImg = loadImage("../assets/obj/alien_cropped.png");
		turretImg = loadImage("../assets/obj/turret_complete.png");
		turretImg.resize(100, 100);
		bulletImg = loadImage("../assets/obj/bullet.png");
		background = new BG(loadImage("../assets/BG/bldg_fg.png"), loadImage("../assets/BG/bldg_mg1.png"), loadImage("../assets/BG/bldg_mg2.png"), loadImage("../assets/BG/city.png"), loadImage("../assets/BG/hills.png"), loadImage("../assets/BG/farm.png"));
		score = new Score();
	}

	public void draw() {
		switch (gameState) {
			case 0:
				background(0);
				fill(255);
				textSize(50);
				textAlign(CENTER);
				text("Click to start", (float)width/2, (float)height/2);
				break;
			case 1:
				background(200, 130, 0);
				fill(255);
				rect(0, height - 50, width, 50);
				background.draw(this);
				image(turretImg, width / 2 - 50, height - 150);

				//Randomly spawn ships
				shipSpawnCounter += (int) (Math.random() * 10);
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

				for (int i = 0; i < bulletsLeft; i++) {
					image(bulletImg, 17 + 30 * i, 17, 20, 20);
				}

				score.draw(this);

				if (bulletsLeft == 0 && bulletList.size() == 0) gameState = 2;

				break;
			case 2:
				background(0);
				fill(255);
				textSize(50);
				textAlign(CENTER);
				text("Game Over", width / 2, height / 2);
				textSize(30);
				text("Click to restart", width / 2, height / 2 + 50);
				break;
		}
	}

	public void mouseReleased() {
		clickAction();
	}

	public void keyReleased() {
		if (key == ' ') {
			clickAction();
		}
	}

	private void clickAction() {
		switch (gameState) {
			case 0:
				gameState = 1;
				break;
			case 1:
				if (bulletsLeft > 0) {
					bulletsLeft--;
					bulletList.add(new Bullet(640, 570, 1, (float)(Math.PI / 2), bulletImg));
				}
				break;
			case 2:
				gameState = 1;
				shipList.clear();
				bulletList.clear();
				bulletsLeft = 10;
				score.reset();
				break;
		}
	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}
