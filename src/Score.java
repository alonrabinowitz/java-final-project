package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {
    int score, highScore;
    Scanner reader;
    public Score() {
        try {
            //Declaration is here because I need a different object in order to write to the file, and making it a local object will delete it when setup() finishes running
            File highScoreFile = new File("highscore.txt");
            if (highScoreFile.createNewFile()) {
                System.out.println("File created: " + highScoreFile.getName());
                highScore = 0;
            } else {
                System.out.println("The file " + highScoreFile.getName() + " already exists, reading from it.");
                reader = new Scanner(highScoreFile);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    System.out.println(data);
                    highScore = Integer.parseInt(data);
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred with the file.");
            e.printStackTrace();
        }
    }

    public void draw(Main main) {
        main.fill(255);
        main.rect(0, main.height - 50, main.width, 50);
        main.textSize(26);
        main.text("Score: " + score, 1100, 40);
        main.text("High Score: " + highScore, 550, 40);

        //this.writeScore();
    }

    public void reset() {
        if (this.score > this.highScore) {
            try {
                //Declaring a new object because we need to close it in order to save to the file
                FileWriter fileWriter = new FileWriter("highscore.txt");
                this.highScore = this.score;
                fileWriter.write(Integer.toString(this.highScore));
                fileWriter.close();
                System.out.println("New high score of " + this.score + " was written to file because it is larger than the previous high score of " + this.highScore);
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        this.score = 0;
    }

    /*public void writeScore() {
        if (score > highScore) {
            try {
                //Declaring a new object because we need to close it in order to save to the file
                FileWriter fileWriter = new FileWriter("highscore.txt");
                fileWriter.write(Integer.toString(highScore));
                fileWriter.close();
                System.out.println("New high score of " + score + " was written to file because it is larger than the previous high score of " + highScore);
                highScore = score;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
    */
}
