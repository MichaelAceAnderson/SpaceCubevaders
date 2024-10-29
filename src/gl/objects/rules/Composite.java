package gl.objects.rules;

import java.util.ArrayList;

import gl.canvas.rules.Canvas;

public abstract class Composite extends GraphicalObject {
	public ArrayList<GraphicalObject> components;

	/**
	 * Create a composite object
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
	 * Set the objects composing this composite object
	 * 
	 * @param components The objects composing this composite object
	 */
	public void setComponents(ArrayList<GraphicalObject> components) {
		this.components = components;
	}

	/**
	 * Get the objects composing this composite object
	 * 
	 * @return The objects composing this composite object
	 */
	public ArrayList<GraphicalObject> getComponents() {
		return this.components;
	}

	/**
	 * Retrieve a shape composing this composite object by its index
	 * 
	 * @param index The index of the shape to retrieve
	 */
	public GraphicalObject getComponent(int index) {
		return this.getComponents().get(index);
	}

	/**
	 * Draw this composite object
	 * 
	 * @see GraphicalObject#draw()
	 * @see GraphicalObject#display()
	 */
	@Override
	public void draw() {
		// Drawing this composite object consists of displaying all the objects that compose it
		for (GraphicalObject component : this.getComponents()) {
			component.display();
		}
	}
}
