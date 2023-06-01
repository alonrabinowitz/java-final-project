package src;

import processing.core.PImage;
import java.util.ArrayList;

public class BG {
    ArrayList <Image> bgElements = new ArrayList<>();
    public BG(PImage bg1){
        bgElements.add(new Image(bg1, 0, 0));
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