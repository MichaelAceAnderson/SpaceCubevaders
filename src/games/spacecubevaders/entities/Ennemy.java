package games.spacecubevaders.entities;

import common.RGBColor;
import games.spacecubevaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;
import gl.objects.volumes.Cube;

public class Ennemy extends Entity {
	// Direction de l'ennemi
	private Direction direction;

	/**
	 * Créer un ennemi
	 * 
	 * @param representation Objet graphique représentant l'ennemi
	 */
	public Ennemy(GraphicalObject representation) {
		super(representation);
		this.setDirection(Direction.RIGHT);
		this.setSpeed(0.02f);
	}

	/**
	 * Définir la direction de l'ennemi
	 * 
	 * @param direction Direction de l'ennemi
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Récupérer la direction de l'ennemi
	 * 
	 * @return Direction de l'ennemi
	 */
	public Direction getDirection() {
		return this.direction;
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
					0.0f, -0.5f, 0.0f,
					0.0f, 0.0f, 0.0f,
					RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]));
			this.getRepresentation().getCanvas().getObjects().add(this.getMissile());
		}
	}
}
