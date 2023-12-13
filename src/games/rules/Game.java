package games.rules;

import java.awt.Image;

import games.rules.Game;

import gl.canvas.rules.Canvas;

public abstract class Game {
	// Canvas sur lequel afficher le jeu
	private Canvas canvas;

	/**
	 * Créer un jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public Game(Canvas canvas) {
		this.setCanvas(canvas);
		this.getCanvas().getParentFrame().setIconImage(this.getIcon());
		this.getCanvas().setGame(this);
	}

	/**
	 * Récupérer le canvas sur lequel afficher le jeu
	 * 
	 * @return Canvas sur lequel afficher le jeu
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Définir le canvas sur lequel afficher le jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Récupérer le nom du jeu
	 * 
	 * @return Nom du jeu
	 */
	public abstract String getName();

	/**
	 * Récupérer l'icône du jeu
	 * 
	 * @return Icône du jeu
	 */
	public abstract Image getIcon();

	/**
	 * Mettre à jour le jeu
	 */
	public abstract void update();

	/**
	 * Récupérer les informations du jeu
	 * 
	 * @return Informations du jeu
	 */
	@Override
	public String toString() {
		return "Jeu : " + this.getName() + "\n";
	}

}
