
/**
 * @author Steven Chang
 * @date Nov 27, 2019
 * This extension of the generic IHangman interface adds functionalities more specific to this particular game - asking if the
 * player wants to fully immerse in the game or witness the computer cheating, as well as 
 *
 */
public interface IHangmanUser extends IHangman {
	
	/**
	 * This method asks the player if he/she wants to see a running tab of how many candidate words are possible.
	 * Select 1 to see the computer cheat, and 0 for an authentic experience of the game.
	 * @return reveal - whether the player wants to see the computer cheat, or just play the game as is
	 */
	public int show_words_remaining();
	
}
