import java.util.ArrayList;

/**
 * @author Steven Chang
 * @date Nov 27, 2019
 * Extends the generic Hangman class and adds an abstract method that allows this particular hangman to "cheat"
 *
 */
public abstract class Evil_Hangman extends Hangman {
	
	/**
	 * Algorithm responsible for making the word as hard as possible for the player to guess. This method computes the number of
	 * words that belong in all possible word families. Each word family represents a possible pattern for the letter guessed 
	 * by the player for that round. For example, a three letter word can have the letter 'a' in the first slot, second slot, third
	 * slot, first and second slots, and so on. Each pattern is a word family that can have 0 up to n words, and the algorithm selects
	 * the word family with the largest n so the chances of the player guessing the correct word is minimized. 
	 * @param user_guess - letter that the player guessed
	 * @param word_length - word length the player specified
	 * @return max_list - ArrayList containing all the words from the largest word family
	 */
	public abstract ArrayList<String> largest_word_family (char user_guess, int word_length);

}
