package gl.objects.rules;

import java.util.ArrayList;

import gl.canvas.rules.Canvas;

public abstract class Composite extends GraphicalObject {
	public ArrayList<GraphicalObject> components;

	/**
	 * Créer un objet composite
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Composite(Canvas canvas, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {
		super(canvas, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.setComponents(new ArrayList<GraphicalObject>());
	}

	/**
	 * Définir les objets composant cet objet composite
	 * 
	 * @param components Les objets composant cet objet composite
	 */
	public void setComponents(ArrayList<GraphicalObject> components) {
		this.components = components;
	}

	/**
	 * Obtenir les objets composant cet objet composite
	 * 
	 * @return Les objets composant cet objet composite
	 */
	public ArrayList<GraphicalObject> getComponents() {
		return this.components;
	}

	/**
	 * Récupérer une forme composant cet objet composite à partir de son index
	 * 
	 * @param index L'index de la forme à récupérer
	 */
	public GraphicalObject getComponent(int index) {
		return this.getComponents().get(index);
	}

	/**
	 * Dessiner cet objet composite
	 * 
	 * @see GraphicalObject#draw()
	 * @see GraphicalObject#display()
	 */
	@Override
	public void draw() {
		// Dessiner cet objet composite consiste à afficher tous Les objets qui le
		// composent
		for (GraphicalObject component : this.getComponents()) {
			component.display();
		}
	}
}