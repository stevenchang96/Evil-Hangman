import java.util.ArrayList;
import java.lang.Math;


/**
 * @author Steven Chang
 * @date Nov 27, 2019
 * This is the main class that runs the Evil Hangman game
 *
 */

public class PlayEvilHangman {
	
	//public ArrayList<String> = new ArrayList<String>();
	
	/**
	 * Main method used to run the game
	 */
	@SuppressWarnings("javadoc")
	public static void main (String args[]) {
		Evil_Hangman_User EH_user = new Evil_Hangman_User();
		
		//Prompt the user for word length. Repeatedly prompts until valid word length is entered
		int word_length = EH_user.get_word_length();
		while ( word_length != 2 && word_length != 3 && word_length != 4 && word_length != 5 && word_length != 6 && word_length != 7 && 
				word_length != 8 && word_length != 9 && word_length != 10 && word_length != 11 && word_length != 12 && word_length != 13 &&
				word_length != 14 && word_length != 15 && word_length != 16 && word_length != 17 && word_length != 18 && word_length != 19 &&
				word_length != 20 && word_length != 21 && word_length != 22 && word_length != 24 && word_length != 28 && word_length != 29) {
			if (word_length <= 0) {
				System.out.println(word_length + " is not a valid word length. Please enter a valid word length.");
				word_length = EH_user.get_word_length();
			} else if (word_length > 29) {
				System.out.println("There are no English words " + word_length + " letters long. Please enter a word length up to 29, inclusive.");
				word_length = EH_user.get_word_length();
			} else {
				System.out.println("There just so happened to be no English words " + word_length + " letters long. Please enter another word length"
						+ " up to 29, inclusive.");
				word_length = EH_user.get_word_length();
			}
		}
		
		
		//Prompt the user for number of guesses he/she wants in the game
		int num_guesses = EH_user.get_num_guesses();
		while (num_guesses <= 0 || num_guesses > 26) {
			if (num_guesses <= 0) {
				System.out.println("Please enter a valid number of guesses. This should be a positive integer, with a maximum of 26. ");
				num_guesses = EH_user.get_num_guesses();
			} else if (num_guesses > 26) {
				System.out.println("Please enter a valid number of guesses. This should be a positive integer, with a maximum of 26. ");
				num_guesses = EH_user.get_num_guesses();
			}
		}
		
		
		//Ask the user if he/she wants to see the number of candidate words remaining
		int reveal_status = EH_user.show_words_remaining();
		boolean show_words_remaining;
		while (reveal_status != 0 && reveal_status != 1) {
			System.out.println("Invalid response. Please enter 1 if you'd like to see the number of candidate words remaining, 0 otherwise. ");
			reveal_status = EH_user.show_words_remaining();
		}
		if (reveal_status == 0) {
			show_words_remaining = false;
		} else {
			show_words_remaining = true;
		}
		
		
		Evil_Hangman_Game EH_game = new Evil_Hangman_Game(word_length, num_guesses, show_words_remaining);
		ArrayList<String> remaining_words = new ArrayList<String>();
		String letters_guessed = "";
		String[] progress = new String[word_length];
		for (int i = 0; i < word_length; i++) {
			progress[i] = "_ ";
			System.out.print("_ ");
		}
		System.out.println(num_guesses + " guesses left.");
		System.out.println("Letters guessed thus far: None yet.");
		
		boolean game_won = false;
		
		while (!EH_game.check_lose(num_guesses)) {
			
			
			char user_guess = EH_user.screen_user_guess(EH_user.get_user_guess());
			//String user = Character.toString(user_guess);
			while (letters_guessed.indexOf(user_guess) != -1) {
				System.out.println(user_guess + " is already guessed.");
				System.out.println("==========================================================================================================");
				user_guess = EH_user.screen_user_guess(EH_user.get_user_guess());
			}
			remaining_words = EH_game.largest_word_family(user_guess, word_length);
			
			/*
			for (int i = 0; i < remaining_words.size(); i++) {
				System.out.println(remaining_words.get(i));
			}
			*/
			
			//Print updated progress pattern
			for (int i = 0; i < word_length; i++) {
				if (remaining_words.get(0).charAt(i) == user_guess) {
					progress[i] = Character.toString(user_guess);
				} 
			}
			for (int i = 0; i < progress.length; i++) {
				System.out.print(progress[i]);
			}
			
			num_guesses--;
			letters_guessed = letters_guessed + " " + user_guess;
			System.out.println();
			System.out.println(num_guesses + " guess(es) left.");
			System.out.println("Letters guessed thus far:" + letters_guessed);
			if (show_words_remaining = true) {
				System.out.println(remaining_words.size() + " possible words remaining. Good luck.");
				System.out.println("==========================================================================================================");
			}
			
			
			game_won = EH_game.check_win(progress);
			if(game_won == true) {
				System.out.println("==========================================================================================================");
				System.out.println();
				System.out.println("Congratulations. You beat the cheating evil hangman.");
				System.out.println();
				System.out.println("==========================================================================================================");
				num_guesses = 0;
			}
			EH_game.setWorking_dictionary(remaining_words);
			
		}
		
		if(game_won == false) {
			System.out.println("You have run out of guesses. You lost. Better luck next time.");
			int reveal_index = (int)(Math.random() * remaining_words.size());
			System.out.println("The correct word is " + remaining_words.get(reveal_index));
		}
		
		//System.exit(0);
	}
}
