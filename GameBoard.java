import java.util.ArrayList;

public class GameBoard {
	private int[][] grid, board; //grid stores pieces, board is for aesthetics of a checkerboard
	public GameBoard(int width, int height) {
		grid = new int[height][width];
		board = new int[height][width];

		// Initialize checkerboard on board (shown behind grid)
		for(int r = 0; r < height; r ++){
			for(int c = 0; c < width; c ++){
				if(r%2 == 0){
					if(c%2 == 0){
						board[r][c] = 1;
					}else{
						board[r][c] = 2;
					}
				}else{
					if(c%2 == 0){
						board[r][c] = 2;
					}else{
						board[r][c] = 1;
					}
				}
			}
		}
		grid[0][3] = 4;
	}


	public boolean move(int startRow, int startCol, int targetRow, int targetCol) {
		System.out.println("DEBUGGING: You want to move from " + startRow + ", " + startCol + " to " + targetRow + ", " + targetCol);
		return true;
	}

	public ArrayList<Integer> highlightPossible(int row, int col){
		ArrayList<Integer> possible = new ArrayList<>();
		return possible;
	}


	public boolean isGameOver() {

		return false;
	}

	public int[][] getGrid() {
		return grid;
	}
	public int[][] getBoard() {
		return board;
	}

	public boolean isInGrid(int row, int col) {
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}
}