package games.spacecubevaders;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import common.Debug;
import common.RGBColor;
import common.Debug.Mode;
import games.rules.Game;
import games.spacecubevaders.entities.Ennemy;
import games.spacecubevaders.entities.Player;
import games.spacecubevaders.entities.rules.Entity;
import games.spacecubevaders.entities.rules.Entity.Direction;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject.Boundary;
import gl.objects.volumes.Cube;
import gl.objects.volumes.Pyramid;

public class SpaceCubevaders extends Game {
	private Canvas canvas;
	private Player player;
	private int level;
	// Limites du jeu
	public final static float MIN_X = -8.0f;
	public final static float MAX_X = 8.0f;
	public final static float GAME_DISTANCE = 30.0f;
	// Caractéristiques des ennemis
	private ArrayList<Ennemy> ennemies;
	public final static float SPACING = 2;
	// Doit être supérieur à 6 pour éviter que le niveau ne démarre trop proche du
	// joueur
	public final static float ENNEMIES_PLAYER_GAP = 12.0f;
	public final static int ENNEMIES_ROWS = 5;
	public final static int ENNEMIES_PER_ROW = 11;

	/**
	 * Créer un jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public SpaceCubevaders(Canvas canvas) {
		super(canvas);

		this.setLevel(1);

		// Créer un joueur au centre bas de l'écran
		this.setPlayer(new Player(new Pyramid(this.getCanvas(), MIN_X + MAX_X, -10, -GAME_DISTANCE,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				RGBColor.DARK_GRAY[0], RGBColor.DARK_GRAY[1], RGBColor.DARK_GRAY[2])));

		this.setEnnemies(new ArrayList<Ennemy>());
		this.initLevel();

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_Q:
					case KeyEvent.VK_LEFT:
						if (SpaceCubevaders.this.getPlayer().getRepresentation().getPosX() > SpaceCubevaders.MIN_X
								* SPACING)
							SpaceCubevaders.this.getPlayer().move(Entity.Direction.LEFT);
						break;
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if (SpaceCubevaders.this.getPlayer().getRepresentation().getPosX() < SpaceCubevaders.MAX_X
								* SPACING)
							SpaceCubevaders.this.getPlayer().move(Entity.Direction.RIGHT);
						break;
					case KeyEvent.VK_SPACE:
						SpaceCubevaders.this.getPlayer().shoot();
						break;
					case KeyEvent.VK_ESCAPE:
						SpaceCubevaders.this.getCanvas().togglePause();
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		};
		this.getCanvas().addKeyListener(keyListener);
		// Demander le focus pour pouvoir utiliser les touches
		this.getCanvas().requestFocus();
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

	/**
	 * Initialiser le niveau du jeu
	 */
	public void initLevel() {
		this.setEnnemies(new ArrayList<Ennemy>());
		float startingRow = this.getPlayer().getRepresentation().getPosY() + ENNEMIES_PLAYER_GAP - this.getLevel();
		for (float row = startingRow; row < startingRow + ENNEMIES_ROWS; row++) {
			for (int col = -(ENNEMIES_PER_ROW / 2); col < +(ENNEMIES_PER_ROW / 2); col++) {
				this.getEnnemies()
						.add(new Ennemy(new Cube(this.getCanvas(), col * SPACING, row * SPACING, -GAME_DISTANCE,
								0.0f, 0.0f, 0.0f,
								1, 1, 1,
								0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f,
								RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2])));
			}
		}
	}

	/**
	 * Définir le niveau du jeu
	 * 
	 * @param level Niveau du jeu
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Récupérer le niveau du jeu
	 * 
	 * @return Niveau du jeu
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Récupérer le nom du jeu
	 * 
	 * @return Nom du jeu
	 */
	@Override
	public String getName() {
		return "Space Cubevaders";
	}

	/**
	 * Récupérer l'icône du jeu
	 * 
	 * @return Icône du jeu
	 */
	@Override
	public Image getIcon() {
		return new ImageIcon(System.getProperty("user.dir") + "/src/games/spacecubevaders/icon.png").getImage();
	}

	/*
	 * Mettre à jour le jeu
	 */
	@Override
	public void update() {
		for (Ennemy ennemy : this.getEnnemies()) {
			if (ennemy.getRepresentation()
					.getBoundingBox()[Boundary.MIN_Y.ordinal()] <= this.getPlayer().getRepresentation()
							.getBoundingBox()[Boundary.MAX_Y.ordinal()]) {

				this.getCanvas().getAnimator().stop();
				this.getCanvas().getParentFrame().showMessageDialog("Défaite !", "Vous avez perdu !", "Quitter le jeu",
						new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent windowEvent) {
								SpaceCubevaders.this.getCanvas().getParentFrame().dispose();
							}
						});
				break;
			}
			if (ennemy.getRepresentation().getPosX() > MAX_X * SPACING) {
				for (Ennemy ennemyToMove : this.getEnnemies()) {
					ennemyToMove.move(Direction.DOWN);
					ennemyToMove.setDirection((Entity.Direction.LEFT));
					ennemyToMove.setSpeed(ennemy.getSpeed() + 0.005f);
				}
			}
			if (ennemy.getRepresentation().getPosX() < MIN_X * SPACING) {
				for (Ennemy ennemyToMove : this.getEnnemies()) {
					ennemyToMove.move(Direction.DOWN);
					ennemyToMove.setDirection((Entity.Direction.RIGHT));
					ennemyToMove.setSpeed(ennemy.getSpeed() + 0.005f);
				}
			}
			ennemy.move(ennemy.getDirection());

			if (this.getPlayer().getMissile() != null) {
				if (this.getPlayer().getMissile().isColliding(ennemy.getRepresentation())) {
					if (Debug.getMode(Mode.VERBOSE)) {
						System.out.println(
								"Collision entre le missile et l'ennemi " + this.getEnnemies().indexOf(ennemy) + " !");
					}

					this.getCanvas().getObjects().remove(ennemy.getRepresentation());
					this.getEnnemies().remove(ennemy);

					this.getCanvas().getObjects().remove(this.getPlayer().getMissile());
					this.getPlayer().setMissile(null);

					this.getPlayer().setScore(this.getPlayer().getScore() + 1);

					if (this.getEnnemies().isEmpty()) {
						if (this.getLevel() >= (int) ENNEMIES_PLAYER_GAP - 6) {
							this.getCanvas().getAnimator().stop();
							this.getCanvas().getParentFrame().showMessageDialog("Victoire !", "Vous avez gagné !",
									"Quitter le jeu");
						} else {
							this.getCanvas().getAnimator().pause();
							this.getCanvas().getParentFrame().showMessageDialog(
									"Niveau " + this.getLevel() + " terminé !",
									"Vous avez terminé le niveau " + this.getLevel() + " !", "Niveau suivant",
									new WindowAdapter() {
										@Override
										public void windowClosed(WindowEvent windowEvent) {
											SpaceCubevaders.this.setLevel(SpaceCubevaders.this.getLevel() + 1);
											SpaceCubevaders.this.initLevel();
											SpaceCubevaders.this.getCanvas().getAnimator().resume();
										}
									});

						}
					}
					break;
				}
			}

			// Si le missile n'a pas été supprimé par une collision
			if (this.getPlayer().getMissile() != null) {
				// Supprimer le missile s'il dépasse les limites du jeu
				if (this.getPlayer().getMissile().getBoundingBox()[Boundary.MIN_Y.ordinal()] > ENNEMIES_ROWS
						* SPACING + ENNEMIES_PLAYER_GAP) {
					this.getCanvas().getObjects().remove(this.getPlayer().getMissile());
					this.getPlayer().setMissile(null);
				}
			}
		}
	}

	/**
	 * Récupérer les informations du jeu
	 * 
	 * @return Informations du jeu
	 */
	@Override
	public String toString() {
		return super.toString()
				+ "\n\tNiveau : " + this.getLevel()
				+ "\n\tEnnemis restants: " + this.getEnnemies().size()
				+ "\n" + this.getPlayer().toString();
	}

}
