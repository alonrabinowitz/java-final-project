package src;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int gameState;
	int shipSpawnCounter, bulletsLeft;
	PImage shipImg, turretImg, bulletImg, preview1, preview2;
	Score score;
	PFont JetBrainsMono;
	BG background;

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
		score = new Score();
		preview1 = loadImage("../assets/BG/preview1.png");
		preview2 = loadImage("../assets/BG/preview2.png");
		JetBrainsMono = createFont("../assets/fonts/JetBrainsMono-Regular.ttf", 32);
	}

	public void draw() {
		frameRate(120);
		switch (gameState) {
			case 0:
				background(0);
				fill(255);
				textSize(50);
				textAlign(CENTER);
				textFont(JetBrainsMono, 50);
				text("Select a Level", (float)width/2-10, (float)height/2-150);
				if(mouseX>=200 && mouseX <= 520 && mouseY >= 400 && mouseY <= 580) {
					rect(190, 390, 340, 200);
				}
				if(mouseX>=750 && mouseX<=1070 && mouseY >= 400 && mouseY <= 580){
					rect(740, 390, 340, 200);
				}
				image(preview1, 200, 400);
				image(preview2, 750, 400);
				break;
			case 1:
				background.draw(this);
				fill(0,150);
				rect(0,0, 1280, 50);
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
				text("High Score: " + Score.highScore, width/2,height/2+100);
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
				if (mouseX >= 200 && mouseX <= 520 && mouseY >= 400 && mouseY <= 580) {
					fill(43,43,43);
					rect(190, 390, 340, 200);
					background = new BG(loadImage("../assets/BG/city.png"));
				} else if (mouseX >= 750 && mouseX <= 1070 && mouseY >= 400 && mouseY <= 580){
					fill(43,43,43);
					rect(740, 390, 340, 200);
					background = new BG(loadImage("../assets/BG/farm.png"));
				}
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
