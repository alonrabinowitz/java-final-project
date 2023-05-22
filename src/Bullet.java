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
        //xSpeed is cos of angle, ySpeed is sin of angle
        //ySpeed is negative because processing switches + and - for y compared to normal graphs
        this.xSpeed = (float)(Math.cos(angle) * 10);
        this.ySpeed = -(float)(Math.sin(angle) * 10);
        this.image = image;
    }

    public boolean act(Main main) {
        this.x += this.xSpeed;
        this.y += this.ySpeed;

        main.image(this.image, this.x, this.y, this.width, this.height);
        if (this.isOffScreen(main.width, main.height)) {
            main.bulletList.remove(this);
            return true;
        }
        return false;
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
