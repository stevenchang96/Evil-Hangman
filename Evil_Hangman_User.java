import java.util.Scanner;

/**
 * @author Steven Chang
 * @date Nov 27., 2019
 * This is the player-facing class that's responsible for gathering all player inputs for the game
 *
 */

public class Evil_Hangman_User implements IHangmanUser {
	Scanner scan = new Scanner(System.in);
	
	public int get_word_length() {
		int word_length = 0;
		System.out.println("Please enter a word length you'd like to play with. This should be an integer between 2 and 29, inclusive. ");
		
		while(!scan.hasNextInt()) {
			scan.next();
			System.out.println("Please enter a valid word length. This should be a number between 2 and 29, inclusive.");
		}
		
		word_length = scan.nextInt();
		//System.out.println(word_length);
		
		return word_length;
	}
	
	public int get_num_guesses() {
		int num_guesses = 0;
		System.out.println("Please enter how many guesses (lives) you'd like to have in the game. This should be a positive integer, with a maximum of 26. ");
		
		while(!scan.hasNextInt()) {
			scan.next();
			System.out.println("Please enter a valid number of guesses. This should be a positive integer, with a maximum of 26. ");
		}
		
		num_guesses = scan.nextInt();
		//System.out.println(word_length);
		
		return num_guesses;
	}
	
	public int show_words_remaining() {
		int reveal = -1;
		System.out.println("Please enter whether you want to see the number of candidate words remaining. Enter 1 for yes and 0 for no. ");
		
		while(!scan.hasNextInt()) {
			scan.next();
			System.out.println("Please enter a valid response - 1 if you'd like to see the number of candidate words remaining, 0 otherwise. ");
		}
		
		reveal = scan.nextInt();
		return reveal;
		
	}
	
	public String get_user_guess() {
		System.out.println("Please enter your guess of letter. Any non-letter guesses will cost you a life! ");
		
		String user_guess = scan.next();
		return user_guess;	
	}
	
	public char screen_user_guess(String guess) {
		while (guess.length() != 1) {
			System.out.println("Please enter one character at a time. ");
			System.out.println("==========================================================================================================");
			guess = get_user_guess();
		}
		char screened_input = Character.toLowerCase(guess.charAt(0));
		return screened_input;
	}
	
}
