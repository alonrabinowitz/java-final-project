package src;

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends PApplet {
	ArrayList<Bullet> bulletList = new ArrayList<>();
	ArrayList<Ship> shipList = new ArrayList<>();
	int shipSpawnCounter, bulletsLeft, score, highScore;
	PImage shipImg, turretImg, bulletImg;
	Scanner reader;
	BG background;

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
		background = new BG(loadImage("../assets/BG/bldg_fg.png"), loadImage("../assets/BG/bldg_mg1.png"), loadImage("../assets/BG/bldg_mg2.png"), loadImage("../assets/BG/city.png"), loadImage("../assets/BG/hills.png"), loadImage("../assets/BG/farm.png"));
		score = 0;
		try {
//			Declaration is here because I need a different object in order to write to the file, and making it a local object will delete it when setup() finishes running
			File highScoreFile = new File("highscore.txt");
			if (highScoreFile.createNewFile()) {
				System.out.println("File created: " + highScoreFile.getName());
				highScore = 0;
			} else {
				System.out.println("The file " + highScoreFile.getName() + " already exists, reading from it.");
				reader = new Scanner(highScoreFile);
				while (reader.hasNextLine()) {
					String data = reader.nextLine();
					System.out.println(data);
					highScore = Integer.parseInt(data);
				}
				reader.close();
			}
		}catch (IOException e){
			System.out.println("An error occurred with the file.");
			e.printStackTrace();
		}
	}

	public void draw() {
		background(200,130,0);
		fill(255);
		rect(0, height - 50, width, 50);
		background.draw(this);
		image(turretImg, width/2 - 50, height - 150);

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

		fill(255);
		rect(0, height - 50, width, 50);
		textSize(26);
		text("Score: " + score, 1100, 40);
		text("High Score: " + highScore, 550, 40);

		if (score > highScore){
			try {
				//Declaring a new object because we need to close it in order to save to the file
				FileWriter fileWriter = new FileWriter("highscore.txt");
				fileWriter.write(Integer.toString(highScore));
				fileWriter.close();
				System.out.println("New high score of " + score + " was written to file because it is larger than the previous high score of " + highScore);
				highScore = score;
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
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

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] {"src.Main"});
	}
}
