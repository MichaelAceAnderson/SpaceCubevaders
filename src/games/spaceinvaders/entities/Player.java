package games.spaceinvaders.entities;

import common.RGBColor;
import games.spaceinvaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;
import gl.objects.volumes.Cube;

public class Player extends Entity {
	// Missile du joueur
	private Cube missile;

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
					0.25f, 2.0f, 0.25f,
					0.0f, 0.5f, 0.0f,
					0.0f, 0.0f, 0.0f,
					RGBColor.GRAY[0], RGBColor.GRAY[1], RGBColor.GRAY[2]));
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

}
