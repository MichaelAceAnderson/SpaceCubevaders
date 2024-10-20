package games.spacecubevaders.entities;

import common.RGBColor;
import games.spacecubevaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;
import gl.objects.volumes.Cube;

public class Player extends Entity {
	private int score = 0;

	/**
	 * Créer un Joueur
	 * 
	 * @param representation Objet graphique représentant le joueur
	 */
	public Player(GraphicalObject representation) {
		super(representation);
		this.setSpeed(0.5f);
	}

	/**
	 * Définir le score du joueur
	 * 
	 * @param score Score du joueur
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Récupérer le score du joueur
	 * 
	 * @return Score du joueur
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * Tirer un missile
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
	 * Perdre le jeu
	 */
	public void lose() {
		this.getRepresentation().getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
				+ "/src/games/spacecubevaders/assets/sounds/explosion.wav");
		this.getRepresentation().getCanvas().getParentFrame().showMessageDialog("Défaite !",
				this.getRepresentation().getCanvas().getParentFrame()
						.textToHTML("Vous avez perdu !"
								+ "\nScore: " + this
										.getScore()
								+ "\n\"Parfois, la meilleure façon de gagner, c'est de ne pas jouer.\"\n - Wargames (1983)/Tron: L'Héritage (2010)"
								+ "\n<img src=\"file:" + System.getProperty("user.dir")
								+ "/src/games/spacecubevaders/assets/images/lose.gif\">"),
				"Quitter le jeu");
	}

	/**
	 * Gagner le jeu
	 */
	public void win() {
		this.getRepresentation().getCanvas().getParentFrame().playSound(System.getProperty("user.dir")
				+ "/src/games/spacecubevaders/assets/sounds/tada.wav");
		this.getRepresentation().getCanvas().getParentFrame().showMessageDialog("Victoire !",
				this.getRepresentation().getCanvas().getParentFrame()
						.textToHTML("Vous avez gagné !"
								+ "\nScore: " + this
										.getScore()
								+ "\"Tout est dans le jeu des poignets.\" - Tron (1982)"
								+ "\n<img src=\"file:" + System.getProperty(
										"user.dir")
								+ "/src/games/spacecubevaders/assets/images/win.gif\">"),
				"Quitter le jeu");
	}

	/**
	 * Afficher les informations du joueur
	 * 
	 * @return Informations du joueur
	 */
	@Override
	public String toString() {
		return "Joueur:"
				+ "\n\tScore: " + this.getScore() + "\n";
	}

}
