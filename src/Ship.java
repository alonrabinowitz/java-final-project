package src;

public class Ship {
    int x, y, speed, width, height;
    public Ship() {
        x = (int)(Math.random() * 2) * 700 - ((int)(Math.random() * 50) + 50);
        y = (int)(Math.random() * 450) + 50;
        speed = (int)(Math.random() * 3) + 3;
        width = 40;
        height = 20;
    }

    public void act() {
        x += speed;
    }

    public void draw(Main main) {
        main.fill(255);
        main.rect(this.x, this.y, this.width, this.height);
    }
    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
