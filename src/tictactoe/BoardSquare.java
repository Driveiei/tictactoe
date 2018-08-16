package tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * A single square on the game board.
 * 
 * @author James Brucker
 */
public class BoardSquare extends Rectangle {
	/** square knows its own row and column. */
	private int row;
	private int column;
	
	/**
	 * Initialize row, column and size.
	 * 
	 * @param row - row's number of box in the game.
	 * @param col - column's number of box in the game.
	 * @param size - size's number of box in the game.
	 * */
	public BoardSquare(int row, int col, int size){
		super(size, size);
        this.row = row;
        this.column = col;
	}
	
	/**
	 * Get row's number of box.
	 * @return number of row.
	 * */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Get column's number of box.
	 * @return number of column.
	 * */
	public int getColumn() {
		return this.column;
	}
	
}
