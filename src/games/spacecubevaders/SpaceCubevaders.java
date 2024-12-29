package games.spacecubevaders;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import common.Debug;
import common.RGBColor;
import common.Debug.Mode;
import games.rules.Game;
import games.spacecubevaders.entities.Player;
import games.spacecubevaders.entities.rules.Entity;
import games.spacecubevaders.entities.rules.Entity.Direction;
import games.spacecubevaders.entities.Ennemy;
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
	 * Create a game
	 * 
	 * @param canvas Canvas on which to display the game
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
		// Request focus to use the keys
		this.getCanvas().requestFocus();
	}

	/**
	 * Get the canvas on which to display the game
	 * 
	 * @return Canvas on which to display the game
	 */
	public Canvas getCanvas() {
		return this.canvas;
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
	 * Get the player
	 * 
	 * @return Player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Set the player
	 * 
	 * @param player Player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Get the enemies
	 * 
	 * @return Array of enemies
	 */
	public ArrayList<Ennemy> getEnnemies() {
		return this.ennemies;
	}

	/**
	 * Set the shelters
	 * 
	 * @param shelters Array of shelters
	 */
	public void setShelters(ArrayList<Shelter> shelters) {
		this.shelters = shelters;
	}

	/**
	 * Get the shelters
	 * 
	 * @return Array of shelters
	 */
	public ArrayList<Shelter> getShelters() {
		return this.shelters;
	}

	/**
	 * Set the enemies
	 * 
	 * @param ennemies Array of enemies
	 */
	public void setEnnemies(ArrayList<Ennemy> ennemies) {
		this.ennemies = ennemies;
	}

	/**
	 * Spawn the player
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
	 * Spawn the shelters
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
	 * Initialize the game level
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
	 * Set the game level
	 * 
	 * @param level Game level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Get the game level
	 * 
	 * @return Game level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * Get the game name
	 * 
	 * @return Game name
	 */
	@Override
	public String getName() {
		return "Space Cubevaders";
	}

	/**
	 * Get the game icon
	 * 
	 * @return Game icon
	 */
	@Override
	public Image getIcon() {
		return new ImageIcon(System.getProperty("user.dir") + "/src/games/spacecubevaders/assets/images/icon.png")
				.getImage();
	}

	/*
	 * Update the game
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
	 * Update the shelters
	 */
	public void updateShelters() {
		for (Shelter shelter : this.getShelters()) {
			for (Cube shelterComponent : shelter.getBlocks()) {
				// To avoid a ConcurrentModificationException, we do not remove the shelter
				// components in case of destruction, we only remove them from the canvas
				// and only process those that are still there
				if (!this.getCanvas().getObjects().contains(shelterComponent)) {
					continue;
				}

				if (this.getPlayer().getMissile() != null) {
					if (this.getPlayer().getMissile().isColliding(shelterComponent)) {
						if (Debug.getMode(Mode.VERBOSE)) {
							System.out.println(
									"Collision between the missile and block "
											+ shelter.getBlocks().indexOf(shelterComponent)
											+ " of shelter " + this.getShelters().indexOf(shelter) + " !");
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
										"Collision between the missile and block "
												+ shelter.getBlocks().indexOf(shelterComponent)
												+ " of shelter " + this.getShelters().indexOf(shelter) + " !");
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
	 * Update the enemies
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
	 * Reverse the direction of the enemies
	 * 
	 * @param direction Direction to reverse
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
	 * Update the player
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
								"Collision between the player's missile and enemy " + this.getEnnemies().indexOf(ennemy)
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
									"Level " + this.getLevel() + " completed!",
									"You have completed level " + this.getLevel() + "!", "Next level",
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
			// Remove the missile if it exceeds the game limits
			float ennemyZoneHeight = ENNEMIES_INITIAL_Y_FROM_PLAYER + ENNEMIES_ROWS * ENNEMIES_SPACING;
			if (this.getPlayer().getMissile().getBoundary(Boundary.MIN_Y) > this.getPlayer()
					.getRepresentation().getPosY()
					+ ennemyZoneHeight) {
				this.removeMissile(this.getPlayer());
			}
		}
	}
	/**
	 * Remove the missile from a game entity
	 *
	 * @param entity Entity that owns the missile
	 */
	public void removeMissile(Entity entity) {
		this.getCanvas().getObjects().remove(entity.getMissile());
		entity.setMissile(null);
	}

	/**
	 * Get the game information
	 * 
	 * @return Game information
	 */
	@Override
	public String toString() {
		String infos = super.toString()
				+ "\n\tLevel: " + this.getLevel();
		if (this.getEnnemies() != null) {
			infos += "\n\tRemaining enemies: " + this.getEnnemies().size();
		}
		if (this.getPlayer() != null) {
			infos += "\n" + this.getPlayer().toString();
		}

		return infos;
	}
}
