package src;

import processing.core.PImage;

public class Bullet {
    int x, y, width, height, bulletNum;
    float angle, xSpeed, ySpeed;
    PImage image;
    public Bullet(int x, int y, int bulletNum, float angle, PImage image) {
        this.width = 24;
        this.height = 24;
        this.x = x - (this.width/2);
        this.y = y;
        this.bulletNum = bulletNum;
        this.angle = angle;
        //xSpeed is cos of angle, ySpeed is sin of angle
        //ySpeed is negative because processing switches + and - for y compared to normal graphs
        this.xSpeed = (float)(Math.cos(angle) * 15);
        this.ySpeed = -(float)(Math.sin(angle) * 15);
        this.image = image;
    }

    public boolean act(Main main) {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
        main.image(this.image, this.x, this.y, this.width, this.height);
        if (this.isOffScreen(main.width, main.height) || this.collisions(main)) {
            main.bulletList.remove(this);
            return true;
        }
        return false;
    }

    public boolean collisions(Main main) {
        for (int i = 0; i < main.shipList.size(); i++) {
            Ship ship = main.shipList.get(i);
            if (this.isColliding(ship)) {
                main.shipList.remove(i);
                main.shipSpawnCounter += 10;
                this.duplicate(main);
                return true;
            }
        }

        return false;
    }

    public void duplicate(Main main) {
        for (int i = 0; i < this.bulletNum + 1; i++) {
            main.bulletList.add(new Bullet(this.x, this.y, this.bulletNum + 1, (float)(i * 2 * Math.PI / (this.bulletNum + 1)), main.bulletImg));
        }
    }

    public boolean isColliding(Ship ship) {
        return this.x < ship.x + ship.width && this.x + this.width > ship.x && this.y < ship.y + ship.height && this.y + this.height > ship.y;
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
