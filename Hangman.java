import java.util.ArrayList;

/**
 * @author Steven Chang
 * @date Nov 27, 2019
 * This is a generic Hangman abstract class that contains the standard functions of a hangman game. The game will need
 * a dictionary of words to choose its words from, the ability to ask the player how long the word should be, and check
 * if the game has been won or lost.
 *
 */
public abstract class Hangman {
	
	/**
	 * Creates a String array that contains all the words in the English dictionary
	 * @return dictionaryArray - String array that contains all the words in the English dictionary
	 */
	public abstract String[] create_full_dictionary();
	
	
	
	/**
	 * Filters down the dictionary to only include words of the player-specified length
	 * @param full_dictionary - dictionaryArray created by the create_full_dictionary method
	 * @param word_length - player-specified word length
	 * @return result - String ArrayList that contains all words from the English dictionary of the player-specified length
	 */
	public abstract ArrayList<String> user_length_words(String[] full_dictionary, int word_length);
	
	
	
	/**
	 * Checks if the game is lost by evaluating if there are still guesses remaining for the player
	 * @param guesses_remaining - number of guesses left in the current game
	 * @return true if the player has no guesses remaining and the game is lost; false otherwise
	 */
	public abstract boolean check_lose(int guesses_remaining);
	
	
	
	/**
	 * Checks if the game is won by recursively evaluating if there are any blanks left in the word
	 * @param game_progress - String array representing the word being guessed
	 * @return true if there are no blanks left, the word is guessed, and the game is won; false otherwise
	 */
	public abstract boolean check_win(String[] game_progress);
	
}
