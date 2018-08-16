package tictactoe;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * UI controller for the JavaFX interface to tic-tac-toe game. This class
 * handles user input events.
 * 
 * @author James Brucker
 */
public class GameController {
	@FXML
	private Label topLabel;
	@FXML
	private Pane centerPane;
	@FXML
	private Button newGameButton;

	private TicTacToeGame game;

	/**
	 * Initialize something.
	 */
	public GameController() {
		// nothing to iniitialize yet.
	}

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set.
	 *
	 * This is a hook to initialize anything your controller or UI needs.
	 */
	@FXML
	public void initialize() {
		game = new TicTacToeGame(4);
		Board board = game.getBoard();
		// make the board size match the size of pane where it is shown
		centerPane.getChildren().add(board);
		centerPane.prefWidthProperty().bind(board.prefWidthProperty());
		centerPane.prefHeightProperty().bind(board.prefHeightProperty());

		// listen to each square for mouse click
		EventHandler<MouseEvent> mouseClicked = this::handleCellClicked;
		board.getChildren().forEach(child -> child.setOnMouseClicked(mouseClicked));
		// The "New Game" button action
		newGameButton.setOnAction(this::handleNewGameEvent);

		// Listen to TicTacToeGame for changes in status.
		game.gameOver().addListener((observable, oldValue, newValue) -> updateGameStatus());

		updateGameStatus();
	}

	/*
	 * Update message above UI that who would win in this game.
	 */
	private void updateGameStatus() {
		Player winner = game.winner();
		if (winner != Player.NONE)
			topLabel.setText("Player " + winner + " wins!");
		else if (game.isGameOver())
			topLabel.setText("Draw. No winner.");
		else
			topLabel.setText("Next Player: " + game.getNextPlayer());
	}

	/**
	 * Event handler for mouse clicks on game board.
	 * 
	 * @param event - The mouse (pointer's) location is available relative to
	 *            severalcoordinate systems: x,y - relative to the origin of
	 *            theMouseEvent's node.
	 */
	public void handleCellClicked(MouseEvent event) {
		Object source = event.getSource();
		if (source instanceof BoardSquare) {
			BoardSquare cell = (BoardSquare) source;
			int row = cell.getRow();
			int col = cell.getColumn();
			double size = cell.getHeight();
			System.out.printf("Clicked on [%d,%d]\n", row, col);
			Player player = game.getNextPlayer();
			if (game.canMoveTo(player, col, row)) {
				game.moveTo(new Piece(player, size), col, row);
				// The game will add piece to the board
			}
			updateGameStatus();
		}
	}

	/**
	 * Handler for button click to start a new game.
	 * 
	 * @param event- an Event representing some type of action. This event type is
	 *            widelyused to represent a variety of things, such as when a
	 *            Buttonhas been fired, when a KeyFrame has finished, and othersuch
	 *            usages.
	 */
	public void handleNewGameEvent(ActionEvent event) {
		game.startNewGame();
	}
}
