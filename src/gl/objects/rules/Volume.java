package gl.objects.rules;

import java.util.ArrayList;

import gl.canvas.rules.Canvas;

public abstract class Volume extends GraphicalObject {
	private ArrayList<Shape> shapes;


	/**
	 * Créer un volume
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Volume(Canvas canvas, float posX, float posY, float posZ,
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
		this.setShapes(new ArrayList<Shape>());
	}

	/**
	 * Définir les formes composant ce volume
	 * 
	 * @param shapes Les formes composant ce volume
	 */
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	/**
	 * Obtenir les formes composant ce volume
	 * 
	 * @return Les formes composant ce volume
	 */
	public ArrayList<Shape> getShapes() {
		return this.shapes;
	}

	/**
	 * Récupérer une forme composant ce volume à partir de son index
	 * 
	 * @param index L'index de la forme à récupérer
	 */
	public Shape getShape(int index) {
		return this.getShapes().get(index);
	}

	/**
	 * Dessiner ce volume
	 * 
	 * @see GraphicalObject#draw()
	 * @see Shape#display()
	 */
	@Override
	public void draw() {
		// Dessiner ce volume consiste à afficher toutes les formes qui le composent
		for (Shape shape : this.getShapes()) {
			shape.display();
		}
	}
}