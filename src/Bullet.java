package src;

import processing.core.PImage;

public class Bullet {
    int x, y, width, height;
    float angle, xSpeed, ySpeed;
    PImage image;
    public Bullet(int x, int y, float angle, PImage image) {
        this.width = 10;
        this.height = 24;
        this.x = x - (this.width/2);
        this.y = y;
        this.angle = angle;
        this.xSpeed = (float)(Math.cos(angle) * 10);
        this.ySpeed = -(float)(Math.sin(angle) * 10);
        this.image = image;
    }

    public void act() {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }

    public void draw(Main main) {
        main.fill(255);
//        main.ellipse(this.x, this.y, this.width, this.height);

        main.image(this.image, this.x, this.y, this.width, this.height);
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
