import processing.core.*;

import java.util.ArrayList;

public class RunGraphicalGame extends PApplet {
	GameBoard game;
	Display display;
	int clickCounter = 0;
	ArrayList<Integer> possible;
	int startRow, startCol, targetRow, targetCol;

	public void settings() {
		size(640, 550);
	}

	public void setup() {
		// Create a game object
		game = new GameBoard(8, 8);
		possible = new ArrayList<>();
		// Create the display
		// parameters: (10,10) is upper left of display
		// (400, 400) is the width and height
		display = new Display(this, 10, 10, 400, 400);

		display.setColor(1, color(117, 150, 86));
		display.setColor(2, color(238, 238, 210));

		display.setImage(4, "photos/blackKing.png");
		display.setImage(-3, "photos/whitepawn.png");
		display.setImage(3, "photos/blackpawn.png");
		//display.setImage(0, "");

		display.initializeWithGame(game);
	}

	@Override
	public void draw() {
		background(200);

		display.drawColorGrid(game.getBoard());

		display.drawImageGrid(game.getGrid()); // display the game

		//showing the possible moves on the screen
		if(possible.size()>0 && clickCounter == 1){
			for(int i = 0; i < possible.size(); i +=2){
				display.highlightLocation(possible.get(i), possible.get(i +1),game);
			}
		}
	}

	public void mouseReleased() {

		clickCounter ++;
		if(clickCounter == 1) {
			Location loc = display.gridLocationAt(mouseX, mouseY);
			 startRow = loc.getRow();
			 startCol = loc.getCol();
			 //to calculate where possible moves are
			 possible = game.highlightPossible(startRow,startCol);
		}else if(clickCounter == 2){
			Location loc = display.gridLocationAt(mouseX, mouseY);
			 targetRow = loc.getRow();
			 targetCol = loc.getCol();
			game.move(startRow,startCol,targetRow,targetCol);
			clickCounter = 0;
		}


	}

	// main method to launch this Processing sketch from computer
	public static void main(String[] args) {
		PApplet.main(new String[] { "RunGraphicalGame" });
	}
}