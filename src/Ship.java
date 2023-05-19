package src;

public class Ship {
    int x, y, speed, width, height;
    public Ship() {
        x = (int)(Math.random() * 2) * 650 - 5;
        y = (int)(Math.random() * 450) + 50;
        speed = (int)(Math.random() * 5) + 1;
        width = 40;
        height = 20;
    }

    public void act() {
        x += speed;
        if (x > 640) {
            x = -40;
        }
    }
}
