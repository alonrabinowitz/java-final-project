package src;

import processing.core.PImage;
import java.util.ArrayList;

public class BG {
    ArrayList <Image> bgElements = new ArrayList<>();
    public BG(PImage fg1, PImage mg1, PImage mg2, PImage bg1, PImage bg2, PImage bg3){
        bgElements.add(new Image(bg1, (int)(Math.random()*1200), 100));
        bgElements.add(new Image(bg2, 0, 100));
        bgElements.add(new Image(mg2, (int)(Math.random()*1200), 520));
        bgElements.add(new Image(mg1, (int)(Math.random()*1200), 450));
        bgElements.add(new Image(fg1, (int)(Math.random()*1200), 250));
        bgElements.add(new Image(bg3, -5, 150));
    }
    public void draw(Main main){
        for (Image bgElement : bgElements) {
            bgElement.draw(main);
        }
    }
}

class Image {
    PImage img;
    int x, y;
    public Image(PImage img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }
    public void draw(Main main){
        main.image(this.img, this.x, this.y);
    }
}