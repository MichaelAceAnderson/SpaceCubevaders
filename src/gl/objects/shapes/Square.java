package gl.objects.shapes;

import com.jogamp.opengl.GL2;

import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;
import gl.objects.rules.Shape;

public class Square extends Shape {

	/**
	 * Create a square
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Square(Canvas canvas, float posX, float posY, float posZ,
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
	 * Create a default square
	 * 
	 * @see Square#Square(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Square(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 45.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
	}

	/**
	 * Draw a square
	 * 
	 * @see GraphicalObject#draw()
	 */
	@Override
	public void draw() {
		this.getGl2().glBegin(GL2.GL_QUADS);
		{
			this.getGl2().glColor3f(this.getRed(), this.getGreen(), this.getBlue());

			// Top left point
			this.getGl2().glVertex3f(-1f, 1f, 0f);
			// Top right point
			this.getGl2().glVertex3f(1f, 1f, 0f);
			// Bottom right point
			this.getGl2().glVertex3f(1f, -1f, 0f);
			// Bottom left point
			this.getGl2().glVertex3f(-1f, -1f, 0f);
		}
		this.getGl2().glEnd();
	}
}