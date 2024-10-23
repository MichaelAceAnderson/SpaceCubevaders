package games.spacecubevaders.entities;

import common.RGBColor;
import games.spacecubevaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;
import gl.objects.volumes.Cube;

public class Ennemy extends Entity {
	private Direction direction;

	/**
	 * Create an enemy
	 * 
	 * @param representation Graphical object representing the enemy
	 */
	public Ennemy(GraphicalObject representation) {
		super(representation);
		this.setDirection(Direction.RIGHT);
		this.setSpeed(0.02f);
	}

	/**
	 * Set the direction of the enemy
	 * 
	 * @param direction Direction of the enemy
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Get the direction of the enemy
	 * 
	 * @return Direction of the enemy
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * Shoot a missile
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
