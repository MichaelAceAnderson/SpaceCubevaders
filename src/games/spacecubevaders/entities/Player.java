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
