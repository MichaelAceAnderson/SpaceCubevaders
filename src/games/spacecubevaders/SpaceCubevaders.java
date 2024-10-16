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
import games.spacecubevaders.structures.Shelter;

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
	private int level;
	private Player player;
	private ArrayList<Shelter> shelters;
	private ArrayList<Ennemy> ennemies;
	public final static float ENNEMIES_SPACING = 2.0f;
	public final static float ENNEMIES_INITIAL_Y_FROM_PLAYER = 15.0f;
	public final static int ENNEMIES_ROWS = 5;
	public final static int ENNEMIES_PER_ROW = 11;
	public final static int MAX_SHELTERS = 3;
	public final static float MIN_X = -16.0f;
	public final static float MAX_X = 16.0f;
	public final static float ENDING_Y_FROM_PLAYER = 3.0f;
	public final static float GAME_DISTANCE = 30.0f;

	/**
	 * Créer un jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public SpaceCubevaders(Canvas canvas) {
		super(canvas);
		this.getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
				+ "/src/games/spacecubevaders/assets/sounds/flynnsarcade.wav");

		this.setLevel(1);

		this.spawnPlayer();
		this.spawnShelters();
		this.initLevel();

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_Q:
					case KeyEvent.VK_LEFT:
						if (SpaceCubevaders.this.getPlayer().getRepresentation().getPosX() > SpaceCubevaders.MIN_X)
							SpaceCubevaders.this.getPlayer().move(Entity.Direction.LEFT);
						break;
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if (SpaceCubevaders.this.getPlayer().getRepresentation().getPosX() < SpaceCubevaders.MAX_X)
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
		return this.canvas;
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
		return this.player;
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
		return this.ennemies;
	}

	/**
	 * Définir les abris
	 * 
	 * @param shelters Tableau des abris
	 */
	public void setShelters(ArrayList<Shelter> shelters) {
		this.shelters = shelters;
	}

	/**
	 * Récupérer les abris
	 * 
	 * @return Tableau des abris
	 */
	public ArrayList<Shelter> getShelters() {
		return this.shelters;
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
	 * Faire apparaître le joueur
	 */
	private void spawnPlayer() {
		this.setPlayer(new Player(new Pyramid(this.getCanvas(), MIN_X + MAX_X, -10, -GAME_DISTANCE,
				0.0f, 0.0f, 0.0f,
				2.0f, 2.0f, 2.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2])));
	}

	/**
	 * Faire apparaître les abris
	 */
	private void spawnShelters() {
		this.setShelters(new ArrayList<Shelter>());
		float shelterScale = 1.0f;
		float minShelterX = MIN_X + shelterScale;
		float maxShelterX = MAX_X - shelterScale;
		for (float i = minShelterX; i <= maxShelterX; i += (maxShelterX - minShelterX) / (MAX_SHELTERS - 1.0f)) {
			this.getShelters()
					.add(new Shelter(this.getCanvas(), i, this.getPlayer().getRepresentation()
							.getPosY() + ENDING_Y_FROM_PLAYER - shelterScale,
							-GAME_DISTANCE,
							0.0f, 0.0f, 0.0f,
							shelterScale, shelterScale, shelterScale,
							0.0f, 0.0f, 0.0f,
							0.0f, 0.0f, 0.0f,
							RGBColor.DARK_GRAY[0], RGBColor.DARK_GRAY[1], RGBColor.DARK_GRAY[2]));
		}
	}

	/**
	 * Initialiser le niveau du jeu
	 */
	private void initLevel() {
		this.getCanvas().getAnimator().pause();

		this.setEnnemies(new ArrayList<Ennemy>());
		float startingX = MIN_X;
		float startingY = this.getPlayer().getRepresentation().getPosY() + ENNEMIES_INITIAL_Y_FROM_PLAYER
				- this.getLevel();
		for (float row = 0; row < ENNEMIES_ROWS; row++) {
			for (float col = 0; col < ENNEMIES_PER_ROW; col++) {
				this.getEnnemies()
						.add(new Ennemy(
								new Cube(this.getCanvas(), startingX + col * ENNEMIES_SPACING,
										startingY + row * ENNEMIES_SPACING,
										-GAME_DISTANCE,
								0.0f, 0.0f, 0.0f,
								1, 1, 1,
								0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f,
								RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2])));
			}
		}

		this.getCanvas().getAnimator().resume();
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
		return new ImageIcon(System.getProperty("user.dir") + "/src/games/spacecubevaders/assets/images/icon.png")
				.getImage();
	}

	/*
	 * Mettre à jour le jeu
	 */
	@Override
	public void update() {
		if (this.getShelters() != null) {
			if (this.getShelters().size() > 0) {
				this.updateShelters();
			}
		}
		if (this.getEnnemies() != null) {
			if (this.getEnnemies().size() > 0) {
				this.updateEnnemies();
			}
		}
		if (this.getPlayer() != null) {
			this.updatePlayer();
		}
	}

	/**
	 * Mettre à jour les abris
	 */
	public void updateShelters() {
		for (Shelter shelter : this.getShelters()) {
			for (Cube shelterComponent : shelter.getBlocks()) {
				// Pour éviter une ConcurrentModificationException, on ne retire pas les
				// composants de l'abri en cas de destruction, on les retire seulement du canvas
				// et on ne traite que ceux qui y sont toujours
				if (!this.getCanvas().getObjects().contains(shelterComponent)) {
					continue;
				}

				if (this.getPlayer().getMissile() != null) {
					if (this.getPlayer().getMissile().isColliding(shelterComponent)) {
						if (Debug.getMode(Mode.VERBOSE)) {
							System.out.println(
									"Collision entre le missile et le block "
											+ shelter.getBlocks().indexOf(shelterComponent)
											+ " de l'abri " + this.getShelters().indexOf(shelter) + " !");
						}
						this.getCanvas().getObjects().remove(shelterComponent);

						this.removeMissile(this.getPlayer());
					}
				}

				for (Ennemy ennemy : this.getEnnemies()) {
					if (ennemy.getMissile() != null) {
						if (ennemy.getMissile().isColliding(shelterComponent)) {
							if (Debug.getMode(Mode.VERBOSE)) {
								System.out.println(
										"Collision entre le missile et le block "
												+ shelter.getBlocks().indexOf(shelterComponent)
												+ " de l'abri " + this.getShelters().indexOf(shelter) + " !");
							}
							this.getCanvas().getObjects().remove(shelterComponent);

							this.removeMissile(ennemy);
						}
					}
				}
			}
		}
	}

	/**
	 * Mettre à jour les ennemis
	 */
	public void updateEnnemies() {
		for (Ennemy ennemy : this.getEnnemies()) {
			float shootProbability = (this.getLevel() * 0.0001f);
			if (Math.random() < shootProbability) {
				ennemy.shoot();
			}
			ennemy.move(ennemy.getDirection());

			if (ennemy.getRepresentation().getPosX() > MAX_X) {
				reverseEnnemies(Direction.LEFT);
				break;
			} else if (ennemy.getRepresentation().getPosX() < MIN_X) {
				reverseEnnemies(Direction.RIGHT);
				break;
			}
		}
	}

	/**
	 * Inverser la direction des ennemies
	 * 
	 * @param direction Direction à inverser
	 */
	public void reverseEnnemies(Direction direction) {
		for (Ennemy ennemyToReverse : this.getEnnemies()) {
			float currentSpeed = ennemyToReverse.getSpeed();
			ennemyToReverse.setSpeed(0.5f);
			ennemyToReverse.move(Direction.DOWN);
			ennemyToReverse.setSpeed(currentSpeed);
			ennemyToReverse.setDirection(direction);
			ennemyToReverse.setSpeed(currentSpeed + 0.005f);
		}
	}

	/**
	 * Mettre à jour le joueur
	 */
	public void updatePlayer() {
		for (Ennemy ennemy : this.getEnnemies()) {
			if (ennemy.getRepresentation().getPosY() <= this.getPlayer().getRepresentation().getPosY()
					+ ENDING_Y_FROM_PLAYER) {

				this.getCanvas().getAnimator().stop();
				this.getPlayer().lose();
				break;
			}
			if (ennemy.getMissile() != null) {
				if (ennemy.getMissile().isColliding(this.getPlayer().getRepresentation())) {
					this.getCanvas().getAnimator().stop();
					this.getPlayer().lose();
					break;
				}
			}

			if (this.getPlayer().getMissile() != null) {
				if (this.getPlayer().getMissile().isColliding(ennemy.getRepresentation())) {
					if (Debug.getMode(Mode.VERBOSE)) {
						System.out.println(
								"Collision entre le missile du joueur et l'ennemi " + this.getEnnemies().indexOf(ennemy)
										+ " !");
					}
					this.getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
							+ "/src/games/spacecubevaders/assets/sounds/kill.wav");
					this.getCanvas().getObjects().remove(ennemy.getRepresentation());
					this.getEnnemies().remove(ennemy);

					this.removeMissile(this.getPlayer());
					this.getPlayer().setScore(this.getPlayer().getScore() + 1);

					if (this.getEnnemies().isEmpty()) {
						float nextLevelEnnemiesHeight = (this.getPlayer().getRepresentation().getPosY()
								+ ENNEMIES_INITIAL_Y_FROM_PLAYER
								- (this.getLevel() + 1));
						float endingHeight = this.getPlayer().getRepresentation().getPosY()
								+ ENDING_Y_FROM_PLAYER;
						if (nextLevelEnnemiesHeight <= endingHeight) {
							this.getCanvas().getAnimator().stop();
							this.getPlayer().win();
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
		}

		if (this.getPlayer().getMissile() != null) {
			// Supprimer le missile s'il dépasse les limites du jeu
			float ennemyZoneHeight = ENNEMIES_INITIAL_Y_FROM_PLAYER + ENNEMIES_ROWS * ENNEMIES_SPACING;
			if (this.getPlayer().getMissile().getBoundary(Boundary.MIN_Y) > this.getPlayer()
					.getRepresentation().getPosY()
					+ ennemyZoneHeight) {
				this.removeMissile(this.getPlayer());
			}
		}
	}

	/**
	 * Retirer le missile d'une entité du jeu
	 *
	 * @param entity Entité à qui appartient le missile
	 */
	public void removeMissile(Entity entity) {
		this.getCanvas().getObjects().remove(entity.getMissile());
		entity.setMissile(null);
	}


	/**
	 * Récupérer les informations du jeu
	 * 
	 * @return Informations du jeu
	 */
	@Override
	public String toString() {
		String infos = super.toString()
				+ "\n\tNiveau : " + this.getLevel();
		if (this.getEnnemies() != null) {
			infos += "\n\tEnnemis restants: " + this.getEnnemies().size();
		}
		if (this.getPlayer() != null) {
			infos += "\n" + this.getPlayer().toString();
		}

		return infos;
	}
}
