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
	 * Créer une entité
	 * 
	 * @param representation Objet graphique représentant l'entité
	 */
	public Entity(GraphicalObject representation) {
		this.setRepresentation(representation);
		representation.getCanvas().getObjects().add(representation);
	}

	/**
	 * Définir l'objet graphique représentant l'entité
	 * 
	 * @param representation Objet graphique représentant l'entité
	 */
	public void setRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	/**
	 * Récupérer l'objet graphique représentant l'entité
	 * 
	 * @return Objet graphique représentant l'entité
	 */
	public GraphicalObject getRepresentation() {
		return representation;
	}

	/**
	 * Définir la vitesse de l'entité
	 * 
	 * @param speed Vitesse de l'entité
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Récupérer la vitesse de l'entité
	 * 
	 * @return Vitesse de l'entité
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * Déplacer l'entité dans une direction
	 * 
	 * @param direction Direction dans laquelle déplacer l'entité
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
	 * Définir le missile de l'entité
	 * 
	 * @param missile Missile de l'entité
	 */
	public void setMissile(GraphicalObject missile) {
		this.missile = missile;
	}

	/**
	 * Récupérer le missile de l'entité
	 * 
	 * @return Missile de l'entité
	 */
	public GraphicalObject getMissile() {
		return this.missile;
	}

	/**
	 * Tirer un missile
	 */
	public abstract void shoot();

	/**
	 * Récupérer les informations de l'entité
	 * 
	 * @return Informations de l'entité
	 */
	@Override
	public String toString() {
		return "Entité: " + this.getClass().getSimpleName()
				+ "\n\t\tObjet représentatif : " + this.getRepresentation().getClass().getSimpleName();
	}

}
