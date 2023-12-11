package game.entities.rules;

import gl.objects.rules.GraphicalObject;

public abstract class Entity {
	// Objet graphique représentant l'entité
	private GraphicalObject representation;

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
				moveX = -1.0f;
				break;
			case RIGHT:
				moveX = 1.0f;
				break;
			case UP:
				moveY = 1.0f;
				break;
			case DOWN:
				moveY = -1.0f;
				break;
		}
		this.getRepresentation().move(moveX, moveY, moveZ);
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
	 * Récupérer les informations du joueur
	 * 
	 * @return Informations du joueur
	 */
	@Override
	public String toString() {
		return "Entité: " + this.getClass().getSimpleName()
				+ "\n\t\tObjet représentatif : " + this.getRepresentation().getClass().getSimpleName();
	}

}
