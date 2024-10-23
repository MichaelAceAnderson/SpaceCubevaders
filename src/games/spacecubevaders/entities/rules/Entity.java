package games.spacecubevaders.entities.rules;

import gl.objects.rules.GraphicalObject;

public abstract class Entity {
	private GraphicalObject representation;
	private float speed;
	private GraphicalObject missile;

	public static enum Direction {
		LEFT, RIGHT, UP, DOWN
	}

	/**
	 * Create an entity
	 * 
	 * @param representation Graphical object representing the entity
	 */
	public Entity(GraphicalObject representation) {
		this.setRepresentation(representation);
		representation.getCanvas().getObjects().add(representation);
	}

	/**
	 * Set the graphical object representing the entity
	 * 
	 * @param representation Graphical object representing the entity
	 */
	public void setRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	/**
	 * Get the graphical object representing the entity
	 * 
	 * @return Graphical object representing the entity
	 */
	public GraphicalObject getRepresentation() {
		return representation;
	}

	/**
	 * Set the speed of the entity
	 * 
	 * @param speed Speed of the entity
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Get the speed of the entity
	 * 
	 * @return Speed of the entity
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Move the entity in a direction
	 * 
	 * @param direction Direction in which to move the entity
	 */
	public void move(Direction direction) {
		float moveX = 0.0f;
		float moveY = 0.0f;
		float moveZ = 0.0f;
		switch (direction) {
			case LEFT:
				moveX = -this.getSpeed();
				break;
			case RIGHT:
				moveX = this.getSpeed();
				break;
			case UP:
				moveY = this.getSpeed();
				break;
			case DOWN:
				moveY = -this.getSpeed();
				break;
		}
		this.getRepresentation().move(moveX, moveY, moveZ);
	}

	/**
	 * Set the missile of the entity
	 * 
	 * @param missile Missile of the entity
	 */
	public void setMissile(GraphicalObject missile) {
		this.missile = missile;
	}

	/**
	 * Get the missile of the entity
	 * 
	 * @return Missile of the entity
	 */
	public GraphicalObject getMissile() {
		return this.missile;
	}

	/**
	 * Shoot a missile
	 */
	public abstract void shoot();

	/**
	 * Get the information of the entity
	 * 
	 * @return Information of the entity
	 */
	@Override
	public String toString() {
		return "Entity: " + this.getClass().getSimpleName()
				+ "\n\t\tRepresentative object: " + this.getRepresentation().getClass().getSimpleName();
	}

}
