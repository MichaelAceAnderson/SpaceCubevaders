package games.spacecubevaders.entities;

import games.spacecubevaders.entities.rules.Entity;
import gl.objects.rules.GraphicalObject;

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
		this.setSpeed(0.01f);
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
}
