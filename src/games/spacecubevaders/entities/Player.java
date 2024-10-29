package games.spacecubevaders.entities;

import common.RGBColor;
import games.spacecubevaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;
import gl.objects.volumes.Cube;

public class Player extends Entity {
	private int score = 0;

	/**
	 * Create a Player
	 * 
	 * @param representation Graphical object representing the player
	 */
	public Player(GraphicalObject representation) {
		super(representation);
		this.setSpeed(0.5f);
	}

	/**
	 * Set the player's score
	 * 
	 * @param score Player's score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Get the player's score
	 * 
	 * @return Player's score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Shoot a missile
	 */
	public void shoot() {
		if (this.getMissile() == null) {
			this.getRepresentation().getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
					+ "/src/games/spacecubevaders/assets/sounds/shoot.wav");
			float posX = this.getRepresentation().getPosX();
			float posY = this.getRepresentation().getPosY();
			float posZ = this.getRepresentation().getPosZ();
			this.setMissile(new Cube(this.getRepresentation().getCanvas(), posX, posY, posZ,
					0.0f, 0.0f, 0.0f,
					0.10f, 1.0f, 0.10f,
					0.0f, 0.5f, 0.0f,
					0.0f, 0.0f, 0.0f,
					RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]));
			this.getRepresentation().getCanvas().getObjects().add(this.getMissile());
		}
	}

	/**
	 * Lose the game
	 */
	public void lose() {
		this.getRepresentation().getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
				+ "/src/games/spacecubevaders/assets/sounds/explosion.wav");
		this.getRepresentation().getCanvas().getParentFrame().showMessageDialog("Defeat!",
				this.getRepresentation().getCanvas().getParentFrame()
						.textToHTML("You have lost!"
								+ "\nScore: " + this
										.getScore()
								+ "\n\"Sometimes the best way to win is not to play.\" - Wargames (1983)/Tron: Legacy (2010)"
								+ "\n<img src=\"file:" + System.getProperty("user.dir")
								+ "/src/games/spacecubevaders/assets/images/lose.gif\">"),
				"Quit the game");
	}

	/**
	 * Win the game
	 */
	public void win() {
		this.getRepresentation().getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
				+ "/src/games/spacecubevaders/assets/sounds/tada.wav");
		this.getRepresentation().getCanvas().getParentFrame().showMessageDialog("Victory!",
				this.getRepresentation().getCanvas().getParentFrame()
						.textToHTML("You have won!"
								+ "\nScore: " + this
										.getScore()
								+ "\"It's all in the wrist.\" - Tron (1982)"
								+ "\n<img src=\"file:" + System.getProperty(
										"user.dir")
								+ "/src/games/spacecubevaders/assets/images/win.gif\">"),
				"Quit the game");
	}

	/**
	 * Display player information
	 * 
	 * @return Player information
	 */
	@Override
	public String toString() {
		return "Player:"
				+ "\n\tScore: " + this.getScore() + "\n";
	}

}
