package src;

public class Bullet {
    int x, y, width, height;
    float angle, xSpeed, ySpeed;
    public Bullet(int x, int y, float angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.xSpeed = (float)(Math.cos(angle) * 10);
        this.ySpeed = -(float)(Math.sin(angle) * 10);
        this.width = 10;
        this.height = 10;
    }
    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }

    public void act() {
        this.x += this.xSpeed;
        this.y += this.ySpeed;
    }

    public void draw(Main main) {
        main.fill(255);
        main.ellipse(this.x, this.y, this.width, this.height);
    }
}
