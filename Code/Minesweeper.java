import java.util.Scanner;

public class Minesweeper {
	//objects rank and field of the classes ranking and minefield
	private static MineField field;
	private static Ranking rank;
	private static int result;
	
	/*First call to Main method */
	public static void main(String[] args) {
		rank=new Ranking();
		mainMessage();
		resetGame();
		while(gameCountinue());
		System.out.println("\nThank you for playing :) Have a nice day!");
	}
	
	//First part of the message displays
	private static void mainMessage(){
		System.out.println("Welcome to Minesweeper!\nTo play the game just input some coordinates for rows(0-4) and columns(0-9).\nTry not to step on the mine :)");
		System.out.println("Usefull commands:\nrestart- Starts a new game.\nexit- Quits the game.");
		System.out.println("top- Reveals the top scoreboard.\nHave Fun !");
	}
	
	//resets the game with new mine field
	private static void resetGame(){
		field = new MineField();
		result = 0;	
	}
	
	//method is used for the commands 
	private static void commandCondition(String input){
		if (input.equals("top")) {
			rank.show();// public method show is called with the help of object "rank"
		}else if (input.equals("restart")) {
			rank.recordName(result);// public method recordName is called with the help of object "rank" and the value of result is passed in parameter. 
			resetGame();
		}
	}
	
	//method plays the game
	private static boolean gameCountinue() {
			field.show();
			System.out.print("\nPlease enter your move(row col): ");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine(); // user inputs the string value
			commandCondition(input);
			// public method legalMoveString is called with the help of object "field" and in the parameter input value is passed.
			if (input.equals("exit")) {
				rank.recordName(result);
				return false;
			}
			if (field.legalMoveString(input)) {
				result++;
				if (result == 35) {
					System.out.println("Congratulations you WON the game!");
						rank.recordName(result);
						resetGame();
				}
			}else if (field.getBoom()) {
				System.out.println("\nBooooooooooooooooooooooooooooom!You stepped on a mine!You survived " + result + " turns");
				rank.recordName(result);
				resetGame();
			}
			return true;// used for looping
	}
}
