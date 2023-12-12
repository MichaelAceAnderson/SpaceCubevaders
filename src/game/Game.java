package game;

import java.util.ArrayList;

import common.DebugMode;
import common.RGBColor;

import java.awt.event.KeyListener;

import game.entities.Ennemy;
import game.entities.Player;
import game.entities.rules.Entity;
import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject.Boundary;
import gl.objects.volumes.Cube;
import gl.objects.volumes.Pyramid;

public class Game {
	// Canvas sur lequel afficher le jeu
	private Canvas canvas;
	// Limites du jeu
	public final static float MIN_X = -5.0f;
	public final static float MAX_X = 5.0f;
	public static final float MIN_Y = -10.0f;
	public static final float MAX_Y = 6.0f;
	public final static float GAME_DISTANCE = 40.0f;
	public final static float SPACING = 2;
	// Joueur
	private Player player;
	// Ennemis
	private ArrayList<Ennemy> ennemies;

	/**
	 * Créer un jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public Game(Canvas canvas) {
		this.setCanvas(canvas);

		// Créer un joueur au centre bas de l'écran
		this.setPlayer(new Player(new Pyramid(this.getCanvas(), MIN_X + MAX_X, MIN_Y, -GAME_DISTANCE,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2])));

		this.setEnnemies(new ArrayList<Ennemy>());
		for (int row = (int) MIN_Y + 10; row < MAX_Y; row++) {
			for (int col = (int) MIN_X; col < MAX_X; col++) {
				this.getEnnemies()
						.add(new Ennemy(new Cube(this.getCanvas(), col * SPACING, row * SPACING, -GAME_DISTANCE,
								0.0f, 0.0f, 0.0f,
								1.0f, 1.0f, 1.0f,
								0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f,
								RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2])));
			}
		}

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				switch (e.getKeyCode()) {
					case java.awt.event.KeyEvent.VK_Q:
					case java.awt.event.KeyEvent.VK_LEFT:
						if (Game.this.getPlayer().getRepresentation().getPosX() > Game.MIN_X * SPACING)
							Game.this.getPlayer().move(Entity.Direction.LEFT);
						break;
					case java.awt.event.KeyEvent.VK_D:
					case java.awt.event.KeyEvent.VK_RIGHT:
						if (Game.this.getPlayer().getRepresentation().getPosX() < Game.MAX_X * SPACING)
							Game.this.getPlayer().move(Entity.Direction.RIGHT);
						break;
					case java.awt.event.KeyEvent.VK_SPACE:
						Game.this.getPlayer().shoot();
						break;
					case java.awt.event.KeyEvent.VK_ESCAPE:
						Game.this.togglePause();
						break;
				}
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
			}

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
			}
		};
		this.getCanvas().addKeyListener(keyListener);
		// Demander le focus pour pouvoir utiliser les touches
		this.getCanvas().requestFocus();

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
	 * Récupérer le joueur
	 * 
	 * @return Joueur
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Définir le joueur
	 * 
	 * @param player Joueur
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Récupérer les ennemis
	 * 
	 * @return Tableau des ennemis
	 */
	public ArrayList<Ennemy> getEnnemies() {
		return ennemies;
	}

	/**
	 * Définir les ennemis
	 * 
	 * @param ennemies Tableau des ennemis
	 */
	public void setEnnemies(ArrayList<Ennemy> ennemies) {
		this.ennemies = ennemies;
	}

	/*
	 * Mettre à jour le jeu
	 */
	public void update() {
		if (this.getPlayer().getMissile() != null) {
			for (Ennemy ennemy : this.getEnnemies()) {
				// Si le missile est en collision avec un ennemi
				if (this.getPlayer().getMissile().isColliding(ennemy.getRepresentation())) {
					if (DebugMode.VERBOSE) {
						// Récupérer l'id de l'ennemi

						System.out.println(
								"Collision entre le missile et l'ennemi " + this.getEnnemies().indexOf(ennemy) + " !");
					}
					// Supprimer l'ennemi
					this.getCanvas().getObjects().remove(ennemy.getRepresentation());
					this.getEnnemies().remove(ennemy);
					// Supprimer le missile
					this.getCanvas().getObjects().remove(this.getPlayer().getMissile());
					this.getPlayer().setMissile(null);
					break;
				}
			}

			// Si le missile n'a pas été supprimé par une collision
			if (this.getPlayer().getMissile() != null) {
				// Supprimer le missile s'il dépasse les limites du jeu
				if (this.getPlayer().getMissile().getBoundingBox()[Boundary.MIN_Y.ordinal()] > MAX_Y * SPACING) {
					this.getCanvas().getObjects().remove(this.getPlayer().getMissile());
					this.getPlayer().setMissile(null);
				}
			}
		}
	}

	/**
	 * Basculer la pause
	 */
	public void togglePause() {
		if (this.getCanvas().getAnimator().isAnimating()) {
			this.getCanvas().getAnimator().stop();
		} else {
			this.getCanvas().getAnimator().start();
		}
	}

	/**
	 * Récupérer les informations du jeu
	 * 
	 * @return Informations du jeu
	 */
	@Override
	public String toString() {
		String gameInfo = "";
		gameInfo += "Jeu : "
				+ "\n\tJoueur : " + this.getPlayer()
				+ "\n\tEnnemis : " + this.getEnnemies().size();
		return gameInfo;
	}

}
