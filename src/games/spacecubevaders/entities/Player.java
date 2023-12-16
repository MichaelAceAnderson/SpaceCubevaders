package games.spacecubevaders.entities;

import common.RGBColor;
import games.spacecubevaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;
import gl.objects.volumes.Cube;

public class Player extends Entity {
	// Missile du joueur
	private Cube missile;
	// Score du joueur
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
	 * Tirer un projectile
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
	 * Définir le missile du joueur
	 * 
	 * @param missile Missile du joueur
	 */
	public void setMissile(Cube missile) {
		this.missile = missile;
	}

	/**
	 * Récupérer le missile du joueur
	 * 
	 * @return Missile du joueur
	 */
	public Cube getMissile() {
		return this.missile;
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
}
