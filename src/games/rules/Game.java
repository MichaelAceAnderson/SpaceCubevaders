package games.rules;

import java.awt.Image;

import games.rules.Game;

import gl.canvas.rules.Canvas;

public abstract class Game {
	/** Canvas on which to display the game */
	private Canvas canvas;

	/**
	 * Create a game
	 * 
	 * @param canvas Canvas on which to display the game
	 */
	public Game(Canvas canvas) {
		this.setCanvas(canvas);
		this.getCanvas().getParentFrame().setIconImage(this.getIcon());
		this.getCanvas().setGame(this);
	}

	/**
	 * Get the canvas on which to display the game
	 * 
	 * @return Canvas on which to display the game
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Set the canvas on which to display the game
	 * 
	 * @param canvas Canvas on which to display the game
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Get the name of the game
	 * 
	 * @return Name of the game
	 */
	public abstract String getName();

	/**
	 * Get the icon of the game
	 * 
	 * @return Icon of the game
	 */
	public abstract Image getIcon();

	/**
	 * Update the game
	 */
	public abstract void update();

	/**
	 * Get the game information
	 * 
	 * @return Game information
	 */
	@Override
	public String toString() {
		return "Game: " + this.getName();
	}

}
