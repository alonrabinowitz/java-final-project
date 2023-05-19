package src;

public class Bullet {
    int x, y;
    float angle, xSpeed, ySpeed;
    public Bullet(float angle) {
        x = (int)(Math.random() * 2) * 650 - 5;
        y = (int)(Math.random() * 450);
        this.angle = angle;
        xSpeed = (float)(Math.cos(angle) * 5);
        ySpeed = (float)(Math.sin(angle) * 5);
    }
}
