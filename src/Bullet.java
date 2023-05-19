package src;

public class Bullet {
    int x, y, width, height;
    float angle, xSpeed, ySpeed;
    public Bullet(float angle) {
        x = (int)(Math.random() * 2) * 650 - 5;
        y = (int)(Math.random() * 450);
        this.angle = angle;
        xSpeed = (float)(Math.cos(angle) * 5);
        ySpeed = (float)(Math.sin(angle) * 5);
        width = 10;
        height = 10;
    }
    public boolean isOffScreen(int screenWidth, int screenHeight) {
        return this.x < -this.width || this.x > screenWidth || this.y < -this.height || this.y > screenHeight;
    }
}
