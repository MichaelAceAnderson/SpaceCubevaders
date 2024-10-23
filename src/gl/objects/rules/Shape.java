package gl.objects.rules;

import gl.canvas.rules.Canvas;

public abstract class Shape extends GraphicalObject {

	/**
	 * Create a shape
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Shape(Canvas canvas, float posX, float posY, float posZ,
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
	}

	/**
	 * Draw this shape
	 * 
	 * @see GraphicalObject#draw()
	 * @see GraphicalObject#display()
	 */
	@Override
	public abstract void draw();
}