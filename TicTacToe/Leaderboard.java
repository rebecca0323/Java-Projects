//Made by Rebecca Zhu and Priya Shokeen on 6/11/19
//the purpose is to make the leaderboard object

//the package allows methods and constructors in the same package to be used in different files
package tictac;

//imported java functions
import java.io.File; //imports the class File for pathnames
import java.io.FileReader; //imports the class FileReader in order to read the text file
import java.io.FileWriter; //imports the class FileWriter  in order to add more text to a text file
import java.io.BufferedReader; //imports the class BufferedReader that allows text file to be read
import java.io.BufferedWriter; //imports the class BufferedWriter in order to add more text to a text file, works with FileWriter
import java.io.IOException; //imports the class IOException for try catch statements
import java.nio.file.Path; //imports the class Path to find the leaderboard.txt file
import java.nio.file.Paths; //imports the class Paths to find the leaderboard.txt file
import java.util.ArrayList; //imports the class ArrayList for the leaderboard
import java.util.Collections; //imports the class Collections to easily sort the players by win count

//leaderboard class
public class Leaderboard {

	//variable that contains the text of who's on the leaderboard
	private static String file = "leaderboard.txt";

	//method that prints the leaderboard and who's on it
	public static void show() {
		Path currentPath = Paths.get(System.getProperty("user.dir")); //creates a new Path instance
		Path filePath = Paths.get(currentPath.toString(), "saved", file); //file pathway to find the saved folder and leaderboard text in the saved folder
		File f = new File(filePath.toString()); //creates new file instance using the file path as a string
		BufferedReader br; //declares a new buffered reader instance
		
		try { //try catch statement to find errors
			br = new BufferedReader(new FileReader(f)); //creates a new file reader using the given file f, which is the text in the leaderboard.txt file

			//declares a variable lines that will represent each line of text in the leaderboard.txt
			String line;
			int totalLines = 0; //used to see if the leaderboard has 0 names
			System.out.println("*****"); //prints a divider
			while ((line = br.readLine()) != null) { //if the line is not blank and has a name and player then print the line with the names
				System.out.println(line);
				totalLines++; //increments the total lines to keep track of how many people on the leaderboard
			}

			if (totalLines == 0) { //if no players win, then there will be no lines in the leaderboard unless saved from previous games
				System.out.println("The leaderboard is empty"); //prints message saying leaderboard is empty
			}
			System.out.println("*****");
			br.close(); //closes the BufferedReader
		} catch (IOException e) { //catches to see if there's an error
			//auto-generated catch block
			e.printStackTrace();
		}
	}

	//method to update the leaderboard text with the new player or someone already on the leaderboard
	public static void updateLeaderboard(String playerName) { //takes in an argument with the player who wins
		Path currentPath = Paths.get(System.getProperty("user.dir")); //creates a new Path instance to ultimately get to the leaderboard.txt file
		Path filePath = Paths.get(currentPath.toString(), "saved", file); //turns the currentPath from previous Path instance to string and then uses file separators to find the leaderboard.txt
		File f = new File(filePath.toString()); //converts the file path to a string that is usable
		BufferedWriter writer; //declares a new buffered writer instance to write in the string
		BufferedReader reader; //declares a new buffered reader instance to read the string

		//declares an array list that uses the entry objects from the entry class
		//needs arrayList instead of array because arraylist is dynamic and expands to how big it needs to be and doesn't need to be definite in the beginning
		ArrayList<Entry> leaders = new ArrayList<Entry>();
		try { //try catch statement to find errors
			reader = new BufferedReader(new FileReader(f)); //creates new file reader using the leaderboard.txt as in previous method
			String line; //declares a variable called lines
			int totalEntries = 0; //starts with 0 entries

			boolean foundPlayer = false; //boolean that sees if a player is already on the leaderboard yet, i.e they've already won before
			//reads all the names in the file except the 10th one because leaderboard only shows top 10
			while (totalEntries < 10 && (line = reader.readLine()) != null) { //if there is a name on the line then it needs to print the name and number of wins
				//line is stored in the form NAME - COUNT
				//split it and create an entry object
				Entry e = new Entry(line.split(" - ")[0], Integer.parseInt(line.split(" - ")[1])); //0 denotes the part before the split so the name of the player, 1 is the part of the array after the split so the # of wins

				//if the player already exists by seeing if the name is already there, update their win count
				if (e.getName().equals(playerName)) { //uses name instance from entry class is the same name given as an argument into the method
					e.incrementCount(); //increments count instance from the entry class
					foundPlayer = true; //sets boolean to true
				}
				totalEntries++; //increments number of entries on the leaderboard
				leaders.add(e); //adds the entry object to the array list
			}
			reader.close(); //closes the buffered reader object

			if (foundPlayer == false) { //if the player is not on the leaderboard yet
				if (totalEntries == 10) {
					//if the leaderboard is full, then no need to add them to the board.
					return; //no need to update leaderboard so just break from the method
				}
				else {
					//the leaederboard has empty spaces, so create a new entry
					//win count is 1 because this is their first win.
					leaders.add(new Entry(playerName, 1)); //creates new entry object to the array list giving the player name and its win count as 1
					totalEntries++; //increments the number of entries on the leaderboard
				}
			}
			//sort the list of leaders by their win count in descending order
			if (totalEntries > 1) {
				//sort is method in the Collections class and sorts the players in the leaders array list by their # of wins from compare to method in Entry class
				Collections.sort(leaders, Collections.reverseOrder()); // player with the highest win count should be at the top, so reverseOrder method inherited with Collections
			}
			//creates new file writer using leaderboard.txt
			writer = new BufferedWriter(new FileWriter(f));
			int i = 0; //declares a variable that lets it loop through all the total entries

			//write the top 10 entries back to the leaderboard text by looping through all the entries, turning them into strings using toString method in entry class then writing it in leaderboard.txt
			while (i < totalEntries) { //while loop that runs through all the entries as long as i is less than the total entries
				writer.write(leaders.get(i).toString() + "\n"); //writes the player's name by converting it to string and adds a new line
				i++; //incrementing i to get another entry in the array list
			}
			writer.close(); //closes the buffered writer object
		} catch (IOException e) { //catches to see if there's an error
			//auto-generated catch block
			e.printStackTrace();
		}
	}
}