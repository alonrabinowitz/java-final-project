package src;

import processing.core.PImage;

public class Ship {
    int x, y, speed, width, height;
    PImage image;
    public Ship(PImage image) {
        this.speed = (int)(Math.random() * 2) + 3;
        this.width = 97;
        this.height = 39;

        //Randomly spawn on left or right side of screen
        if (Math.random() > .5) {
            this.x = -this.width;
        } else {
            this.x = 1280;
            this.speed *= -1;
        }
        this.y = (int)(Math.random() * 9) * 50 + 50;
        this.image = image;
    }

    public boolean act(Main main) {
        this.x += this.speed;

        main.image(this.image, this.x, this.y, this.width, this.height);
        if (this.isOffScreen(main.width, main.height)) {
            main.shipList.remove(this);
            return true;
        }
        return false;
    }

    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
