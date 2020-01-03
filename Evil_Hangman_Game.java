import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Steven Chang
 * @date Nov 27, 2019
 * This is the game class that handles all the game logic and dictates how the game behaves
 *
 */

public class Evil_Hangman_Game extends Evil_Hangman {
	
	@SuppressWarnings("unused")
	private int word_length;
	@SuppressWarnings("unused")
	private int guesses_allowed;
	@SuppressWarnings("unused")
	private boolean words_remaining;
	//private ArrayList<String> starting_dictionary = new ArrayList<String>();
	@SuppressWarnings("javadoc")
	public ArrayList<String> working_dictionary = new ArrayList<String>();
	
	//Constructor of Evil_Hangman object
	/**
	 * @param length - length of the word to be guessed
	 * @param guesses - number of guesses allowed in the game
	 * @param wordsRemaining - number of candidate words remaining in the English dictionary
	 */
	public Evil_Hangman_Game(int length, int guesses, boolean wordsRemaining) {
		this.word_length = length;
		this.guesses_allowed = guesses;
		this.words_remaining = wordsRemaining;
		this.working_dictionary = user_length_words(create_full_dictionary(), length);
		
	}
	
	
	
	/**
	 * Getter method to fetch the working dictionary of the game, which contains all candidate words left in the current game
	 * @return working_dictionary - all candidate words left in the current game
	 */
	/*
	public ArrayList<String> getWorking_dictionary() {
		return working_dictionary;
	}
	*/

	
	
	/**
	 * Setter method that updates the working dictionary of the game
	 * @param working_dictionary - updated list of the working dictionary from the previous guess, with one or more words removed 
	 * based on player guess
	 */
	public void setWorking_dictionary(ArrayList<String> working_dictionary) {
		this.working_dictionary = working_dictionary;
	}
	
	
	
	/**
	 * Creates a String array that contains all the words in the English dictionary
	 * @return dictionaryArray - String array that contains all the words in the English dictionary
	 */
	@Override
	public String[] create_full_dictionary() {
		
		Words dictionary = new Words();
		String[] dictionaryArray = dictionary.getWords();
		return dictionaryArray;
		
	}
	
	
	/**
	 * Filters down the dictionary to only include words of the player-specified length
	 * @param full_dictionary - dictionaryArray created by the create_full_dictionary method
	 * @param word_length - player-specified word length
	 * @return result - String ArrayList that contains all words from the English dictionary of the player-specified length
	 */
	@Override
	public ArrayList<String> user_length_words(String[] full_dictionary, int word_length) {
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < full_dictionary.length; i++) {
			if (full_dictionary[i].length() == word_length) {
				result.add(full_dictionary[i]);
			}
		}
		
		return result;
	}
	
	
	
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
	@Override
	public ArrayList<String> largest_word_family(char user_guess, int word_length) {
		ArrayList<String> max_list = new ArrayList<String>();
		ArrayList<String> current_list = new ArrayList<String>();
		char[] current_family = new char[word_length];
		int max_count = 0;
		int current_count = 0;
		
		//Identify all word families in list; keep looping until list becomes empty
		while(!this.working_dictionary.isEmpty()) {
			char[] temp_family = new char[word_length];
			current_list.clear();
			
			//Finds word family for first word, use that as standard to collect all words that fall in the same word family
			for (int i = 0; i < word_length; i++) {
				if (this.working_dictionary.get(0).charAt(i) == user_guess) {
					current_family[i] = user_guess;
				} else {
					current_family[i] = '_';
				}
			}
			//System.out.println(Arrays.toString(current_family));
			
			//Add the first word to the current list of current word family
			current_list.add(this.working_dictionary.get(0));
			
			//Loop through every word of the user specified length
			for (int i = 1; i < this.working_dictionary.size(); i++) {
				//Loop through every letter of each word of user specified length
				for (int j = 0; j < word_length; j++) {
					//Create word pattern for every word of user specified length, and add to temporary word family for comparison
					if (this.working_dictionary.get(i).charAt(j) == user_guess) {
						temp_family[j] = user_guess;
					} else {
						temp_family[j] = '_';
					}
				}
				//System.out.println(Arrays.toString(temp_family));
				
				//If temporary word family is the same as current word family, add that word to the current list
				if (Arrays.equals(temp_family, current_family)) {
					current_list.add(this.working_dictionary.get(i));
				}
			}
			
			//Set max count to the largest current list encountered; this is equivalent to finding the largest word family
			current_count = current_list.size();
			//System.out.println(current_count);
			if (current_count > max_count) {
				max_count = current_count;
				
				//Copy all words from the newly discovered largest word family into max_list
//				for (int i = 0; i < current_list.size(); i++) {
//					
//					max_list.add(current_list.get(i));
//				}
				max_list.clear();
				max_list.addAll(current_list);
			}
			
			//Reduces the size of the master set of words with user-specified length
			this.working_dictionary.removeAll(current_list);
			
		}
		
		
		return max_list;
		

	}
	
	
	/**
	 * Checks if the game is lost by evaluating if there are still guesses remaining for the player
	 * @param guesses_left - number of guesses left in the current game
	 * @return true if the player has no guesses remaining and the game is lost; false otherwise
	 */
	@Override
	public boolean check_lose(int guesses_left) {
		if (guesses_left <= 0) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * Checks if the game is won by recursively evaluating if there are any blanks left in the word
	 * @param progress - String array representing the word being guessed
	 * @return true if there are no blanks left, the word is guessed, and the game is won; false otherwise
	 */
	@Override
	public boolean check_win(String[] progress) {
		if (progress.length == 0) {
			return true;
		}
		
		return !progress[progress.length - 1].equals("_ ") && check_win(Arrays.copyOfRange(progress, 0, progress.length - 1));
	}
	
	
	
	/**
	 * Checks if the game is won by iteratively evaluating if there are any blanks left in the word
	 * @param progress - String array representing the word being guessed
	 * @return true if there are no blanks left, the word is guessed, and the game is won; false otherwise
	 */
	/*
	public boolean check_win2(String[] progress) {
		for (int i = 0; i < progress.length; i++) {
			if (progress[i].equals("_ ")) {
				return false;
			}
		}
		return true;
	}
	*/
	
	

	
	
}
