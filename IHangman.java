
/**
 * @author Steven Chang
 * @date Nov 27, 2019
 * This is the generic Hangman interface that handles the most basic player-facing functionalities - asking for the word length
 * and number of guesses allowed in the game.
 *
 */
public interface IHangman {
	
	/**
	 * This method is used to prompt the player to enter a word length for the hangman game. 
	 * The player is repeatedly re-prompted to enter an integer value if the input is any non-integer.
	 * @return word_length - the length of the word used to play the game
	 */
	public int get_word_length();
	
	
	
	/**
	 * This method is used to prompt the player to enter the number of guesses allowed in the game. 
	 * The player is repeatedly re-prompted to enter an integer value if the input is any non-integer.
	 * @return num_guesses - the number of guesses allowed in the game
	 */
	public int get_num_guesses();
	
	
	
	/**
	 * This method prompts the player to enter a letter guess and takes in a String input from the keyboard.
	 * @return player_guess - String input taken from the player from the keyboard
	 */
	public String get_user_guess();
	
	
	
	/**
	 * This method screens the player input for a letter guess, and re-prompts the player to enter a single character 
	 * if longer inputs are entered
	 * @param guess - the output from get_user_guess method, the player's input
	 * @return screened_input - a single character that represents the player's guess for that round
	 */
	public char screen_user_guess(String guess);
	
}
