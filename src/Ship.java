package src;

public class Ship {
    int x, y, speed, width, height;
    public Ship() {
        this.speed = (int)(Math.random() * 3) + 3;
        this.width = 40;
        this.height = 20;
        if (Math.random() > .5) {
            this.x = -this.width;
        } else {
            this.x = 1280;
            this.speed *= -1;
        }
        this.y = (int) (Math.random() * 450) + 50;
    }

    public void act() {
        this.x += this.speed;
    }

    public void draw(Main main) {
        main.fill(255);
        main.rect(this.x, this.y, this.width, this.height);
    }
    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
