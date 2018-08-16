package tictactoe;

/**
 * Types of pieces in the game.
 * 
 * @author James Brucker
 */
public enum Player {
	X("X", 1),
	O("O", -1),
	NONE("", 0);
	
	public final String text;
	public final int value;
	
	/**
	 * Initialize the player status
	 * 
	 * @param text - display text of players.
	 * @param value - number of players.
	 * */
	private Player(String text, int value) {
		this.text = text;
		this.value = value;
	}
}
