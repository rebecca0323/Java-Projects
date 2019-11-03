//Made by Rebecca Zhu and Priya Shokeen on 6/11/19
//the purpose is to make the objects to use in the array list of winners in the leaderboard class

//the package allows methods and constructors in the same package to be used in different files
package tictac;

//Entry class
public class Entry implements Comparable<Entry> { //file implements the comparable interface that allows us to use the compareTo method to sort the array list
	
	//instance variables used in the constructor to describe the entry object
	private String name;
	private int count = 0;

	//constructor for the entry object that requires a name and an integer as a parameter
	public Entry(String n, int c) {
		name = n; //sets the name as the first parameter given
		count = c; //sets the count as the integer parameter
	}

	//returns a string that will be used to update the leaderboard.txt file
	public String toString() {
		return name + " - " + count; //returns a string that has the player on the leaderboard and a hyphen and how many wins the user has
	}

	//getter that returns the name instance variable in the object
	public String getName() {
		return name;
	}
	
	//setter that increments count, which is the number of wins a player has
	public void incrementCount() {
		count++;
	}

	//compare one entry to another based on count (the number of wins)
	@Override //overrides the compareTo method in Comparable 
	public int compareTo(Entry o) { //takes in an Entry object parameter and sorts all the entry objects in the leader array list
		//returns 1 if this.count > count of parameter, 0 if they're equal, and -1 if count of parameter > this.count
		return Integer.compare(this.count, o.count); //compares current entry object count instance variable to other count instance variables in other entry objects
	}
}