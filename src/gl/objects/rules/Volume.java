package gl.objects.rules;

import java.util.ArrayList;

import gl.canvas.rules.Canvas;

public abstract class Volume extends GraphicalObject {
	private ArrayList<Shape> shapes;

	/**
	 * Create a volume
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
	 * Set the shapes composing this volume
	 * 
	 * @param shapes The shapes composing this volume
	 */
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	/**
	 * Get the shapes composing this volume
	 * 
	 * @return The shapes composing this volume
	 */
	public ArrayList<Shape> getShapes() {
		return this.shapes;
	}

	/**
	 * Retrieve a shape composing this volume by its index
	 * 
	 * @param index The index of the shape to retrieve
	 */
	public Shape getShape(int index) {
		return this.getShapes().get(index);
	}

	/**
	 * Draw this volume
	 * 
	 * @see GraphicalObject#draw()
	 * @see Shape#display()
	 */
	@Override
	public void draw() {
		// Drawing this volume consists of displaying all the shapes that compose it
		for (Shape shape : this.getShapes()) {
			shape.display();
		}
	}
}
