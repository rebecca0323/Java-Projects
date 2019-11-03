//Made by Rebecca Zhu and Priya Shokeen on 6/11/19
//the purpose is the run the code

//the package allows methods and constructors in the same package to be used in different files
package tictac;


//imported scanner allows users to input their options
import java.util.Scanner;

//class name is Driver that runs the program
public class Driver {

	//declared a new object using the constructor from TicTacToe class
	static TicTacToe board;
	private static Scanner scan; //declares a Scanner object using java import
	
	//this is the main method
	public static void main(String[] args) {

		//instantiates the new scanner object
		scan = new Scanner(System.in);
		boolean running = true; //boolean variable means the program is running, when user exits it sets to false
		int input; //variable that allows user to input and choose what they want, given the options
		
		//outside the do while loop so it only prints once when the user first runs the program
		System.out.println("Welcome to TicTacToe! Please look at the instructions before playing by entering 1");
		
		//do while loop that executes and shows the menu while running is true
		do {
			//prints the menu options
			System.out.print("\nWhat would you like to do? \n1. View instructions \n2. Play with two players \n3. View leaderboard \n4. Exit > ");
			//sets the variable declared as what the user inputs
			input = scan.nextInt();
			
			//this parses the integer the user inputs
			if (input == 1) { //if the user chooses 1, then run the help method
				help();
			} else if (input == 2) { //if the user chooses 2, then run the two player game
				twoPlayer();
			} else if (input == 3) { //if the user chooses 3, then show the leaderboard
				showLeaderboard();
			} else if (input == 4) { //if the user chooses 4, then exit the program
				running = false;
			} else if (input > 4) { //if the user chooses something else, then tell user input is invalid and have them choose again
				System.out.println("You've entered an invalid input.");
				continue; //continues the do while loop
			}
		} while (running == true); //condition that runs the do while loop
		scan.close(); //closes the scanner
	}
	
	//method that runs the tic tac toe game
	public static void twoPlayer() { 
		//instantiates the new scanner object
		scan = new Scanner(System.in);
		//declares a variable input that will equal what the user inputs as the spot they want to put the mark
		int input;
		//declares variables equal to what the users put as their name
		String player1;
		String player2;
		//declares a variable that means that there is no winner in the beginning
		String winner = "";
		//player one's mark is an X so the mark is set as X first
		String mark = "X";
		//declares a variable that counts how many turns have gone by in order to figure out if there's a tie or if the board is full
		int turns = 1;

		//instantiates an object using the constructor from the tictactoe class
		board = new TicTacToe();
		
		//prints a message that shows which player is X and O
		System.out.println("=================");
		System.out.println("You have chosen to play the game!\nThe player that goes first is X.\nThe player that goes second is O");
		System.out.println("=================");
		System.out.print("Player 1 please enter your name > ");
		player1 = scan.nextLine(); //records what user 1 inputs as their name
		System.out.print("Player 2 please enter your name > ");
		player2 = scan.nextLine(); //records what user 2 inputs as their name

		//less than 10 turns because a board only has 9 spots and if winner is "" then there is no winner and game continues
		while (winner.equals("") && turns < 10) {
			board.printBoard(); //prints board object using the printBoard method in the tictactoe class

			if (turns % 2 == 1) { //if turns is odd, using the modulo function, the it's player1's turn
				System.out.print(player1 + " type the number of the slot you would like to move > ");
			}
			else { //if turns is even, then its the player2's turn
				System.out.print(player2 + " type the number of the slot you would like to move > ");
			}
			
			input = scan.nextInt(); //spot either player1 or player 2 inputs to place a mark
			if (board.checkMove(input) == false) { //checks if the checkMove method from the tictactoe class returns false, which means the move is invalid
				System.out.println("This spot is invalid! Please enter a new spot"); //tells user to input a new spot and reprints the board
			}
			else { //the move is valid
				board.userMove(input, mark); //places a mark where the user inputs
				if (board.checkWinner() == true) { //checks if there is a winner by seeing if gameOver boolean from tictactoe class is true using the method in the tictactoe class
					board.printBoard(); //prints the board that shows the winning combination
					if(board.getWinner().equals("X")) { //checks which player wins using the getter from the tictactoe class
						winner = player1; //since x is the mark of the first player, it sets the winner to the first player's name
					}
					else {
						winner = player2; //this means the second player - O - won
					}
					break; //exits the while loop so it doesn't ask the next player to make a move after there is already a winner
				}
				
			mark = user2Move(mark); //uses method to change the marks
			turns++; //increments turns to keep track of how many 
			}
		}

		if (winner.equals("")) { //once turns is 10, the while loop doesn't run and if there still isn't a winner, then there is a tie
			System.out.println("=================");
			board.printBoard(); //prints the board object using the method in the tictactoe class
			System.out.println("You guys have tied! Better luck next time."); //prints out a tie message
		} else { //after the break from the while loop, will run this code because winner is not "" and will be the player that win's name
			System.out.println("Congratualations " + winner + ". You win!"); //prints out a message saying who wins
			Leaderboard.updateLeaderboard(winner); //updates the leaderboard text file using method from leaderboard class
		}
	}

	//method that changes the users' marks back and forth once they've made their move
	private static String user2Move(String mark) { //takes in an argument that is the previous mark and returns a string that is set to be the new mark
		if (mark.equals("X")) {
			return "O"; //changes mark X into mark O
		} else {
			return "X"; //else changes mark O into mark X
		}
	}

	//method that prints the instructions to the game
	public static void help() {
		scan = new Scanner(System.in); //instantiates new scanner object
		String input; //declares a variable called input
		System.out.println("================="); //prints how to play and how to win
		System.out.println("\nHOW TO PLAY: \n\nThe game board will look like this. \nTo place a mark in sqaure, simply enter the number corresponding to the spot, shown on the right.\n");
		System.out.println("_|_|_     1|2|3"); //easy way to print board and the numbers associated with the spots
		System.out.println("_|_|_     4|5|6");
		System.out.println("_|_|_     7|8|9");
		System.out.println("\n\nHOW TO WIN: get three marks in a row either horizontally, vertically, or diagonally before your opponent does. \nIf all 9 spots are full and no one has 3 in a row, a tie has occurred.");
		System.out.println("\nPress enter to continue back to the menu"); //makes users enter something before going back to the menu
		input = scan.nextLine(); //sets the declared variable to what user enters
		if (input.equals("")) { //checks if the user entered enter
			System.out.println("================="); //prints a divider
		}
	}

	//method that is called when user inputs 3 from the menu and prints the leaderboard using the leaderboard text in the saved folder, like the MadLibs
	public static void showLeaderboard() {
		System.out.println("Here is the leaderboard:");
		Leaderboard.show(); //reads the text and converts it into a string to be printed through the show method in the leaderboard class
	}
}