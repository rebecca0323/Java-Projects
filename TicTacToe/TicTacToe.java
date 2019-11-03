//Made by Rebecca Zhu and Priya Shokeen on 6/11/19
//the purpose is to make the board game object and the methods that goes along with it like the constructor for the game board

//the package allows methods and constructors in the same package to be used in different files
package tictac;

//tic tac toe class 
public class TicTacToe {
	
	//variables that are instances used in the constructor
	private String[][] gameBoard;
	private boolean gameOver;
	//variable that is the mark of the person that wins
	private String winnerName;

	//constructor that makes the tictactoe objects
	public TicTacToe() {
		gameOver = false; //sets gameOver to be false
		gameBoard = new String[3][3]; //creates a new 2-D array with three rows and three columns

		//loops through the game board and sets every spot to a space so basically clears the game board
		for (int i = 0; i < gameBoard.length; i++) { //i represents each row in the game board
			for (int j = 0; j < gameBoard[i].length; j++) { //j represents each column in the game board
				gameBoard[i][j] = " "; //sets the spot in the 2D array to a space
			}
		}
	}
	
	//getter that returns the winnerName once checkWinner sets a winner name
	public String getWinner() {
		return winnerName;
	}

	//getter that returns what is on the board currently corresponding to the spot
	private String getBoardSpot(int a) { //takes in an argument that is the spot
		if (a == 1) {
			return gameBoard[0][0]; //returns the mark in the first spot using layout from the instructions in the driver
		}
		else if (a == 2) {
			return gameBoard[0][1]; //returns the mark in the second spot
		}
		else if (a == 3) {
			return gameBoard[0][2]; //returns the mark in the third spot
		}
		else if (a == 4) {
			return gameBoard[1][0]; //returns the mark in the fourth spot
		}
		else if (a == 5) {
			return gameBoard[1][1]; //returns the mark in the fifth spot
		}
		else if (a == 6) {
			return gameBoard[1][2]; //returns the mark in the sixth spot
		}
		else if (a == 7) {
			return gameBoard[2][0]; //returns the mark in the seventh spot
		}
		else if (a == 8) {
			return gameBoard[2][1]; //returns the mark in the eight spot
		}
		else {
			return gameBoard[2][2]; //returns the mark in the ninth spot
		}
	}
	
	//method that prints the game board
	public void printBoard() {
		System.out.println(""); //skips a line
		for (int i = 0; i < gameBoard.length; i++) { //i represents each row in the game board, enhanced for loop allows it to loop through all the rows in the game board
			for (int j = 0; j < gameBoard[i].length; j++) { //j is the columns in the game board, enhanced for loop allows it to loop through all the columns in the game board
				if (gameBoard[i][j].equals(" ")) { //this sets all the spot to an _ in from the constructor in the beginning
					System.out.print("_");
				} else { //puts the mark set in each spot
					System.out.print(gameBoard[i][j]);
				}
				if (j < 2) { //this prints a vertical line after each mark so that there is 2 vertical lines in every row
					System.out.print("|");
				} else { //if j is 2 or more since it starts at 0, then it needs to start a new line
					System.out.println(""); //moves onto the next line
				}
			}
		}
	}

	//method that checks if the number of the spot entered is in the range of 1-9 inclusive
	public boolean checkMove(int a) {
		//if statement with the range
		if (a > 0 && a < 10) {
			//check if the spot is free
			String spot = getBoardSpot((a)); //uses getter from previous method to figure out what mark is in the spot
			if (" ".equals(spot)) { //checks if the spot is empty and equal to a space
				return true; //returns true
			}
			else {
				return false; //returns false, which means a mark is already in the spot
			}
		}
		else { //returns false if a is not within the range
			return false;
		}
	}

	//method that takes in the spot and the mark the user wants to place after checkMove returns true
	public void userMove(int a, String m) {
		if (a == 1) { //replaces the space with the mark in spot 1
			gameBoard[0][0] = m;
		}
		else if (a == 2) { //replaces the space with the mark in spot 2
			gameBoard[0][1] = m;
		}
		else if (a == 3) { //replaces the space with the mark in spot 3
			gameBoard[0][2] = m;
		}
		else if (a == 4) { //replaces the space with the mark in spot 4
			gameBoard[1][0] = m;
		}
		else if (a == 5) { //replaces the space with the mark in spot 5
			gameBoard[1][1] = m;
		}
		else if (a == 6) { //replaces the space with the mark in spot 6
			gameBoard[1][2] = m;
		}
		else if (a == 7) { //replaces the space with the mark in spot 7
			gameBoard[2][0] = m;
		}
		else if (a == 8) { //replaces the space with the mark in spot 8
			gameBoard[2][1] = m;
		}
		else if (a == 9) { //replaces the space with the mark in spot 9
			gameBoard[2][2] = m;
		}
	}

	//method that checks if there is a winner and returns gameOver boolean
	public boolean checkWinner() {
		//enhanced for loop that checks if someone has three in a row horizontally
		for (int row = 0; row < gameBoard.length; row++) {
			//checks if there are three of the same marks in a row and that all three aren't spaces
			if (gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2] && !gameBoard[row][0].equals(" ")) {
				//sets winner name to the mark that has won
				winnerName = gameBoard[row][0];
				//sets gameOver to be true since someone has won
				gameOver = true;
			}

		}
		
		//enhanced for loop that checks if someone has three in a row vertically
		for (int col = 0; col < gameBoard[0].length; col++) {
			//sees if there are three of the same marks in the same column and that the spots aren't empty
			if (gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col] && !gameBoard[0][col].equals(" ")) {
				//sets winner name to the mark that has won
				winnerName = gameBoard[0][col];
				//sets gameOver to be true since someone has won
				gameOver = true;
			}
		}
		
		//checks the diagonal going from top left corner to bottom right corner to see if someone has won
		//the marks in each spot has to be equal to each other and not a space
		if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && !gameBoard[0][0].equals(" ")) {
			//sets winner name to the mark that has won
			winnerName = gameBoard[0][0];
			//sets gameOver to be true since someone has won
			gameOver = true;

		}
		
		//checks the diagonal going from bottom left corner to top right corner to see if someone has won
		//the marks in each spot have to be equal to each other and not a space
		if (gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && !gameBoard[0][2].equals(" ")) {
			//sets the winner name to be the mark that has won
			winnerName = gameBoard[1][1];
			//sets gameOver to be true since someone has won
			gameOver = true;
		}
		
		//this means there's no winner and gameOver is still false as it was before
		return gameOver;
	}
}