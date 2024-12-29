package gl.objects.volumes;

import common.Debug;
import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;
import gl.objects.rules.Shape;
import gl.objects.rules.Volume;
import gl.objects.shapes.Square;
import gl.objects.shapes.Triangle;

public class Pyramid extends Volume {
	private enum Face {
		BASE, BACK, FRONT, LEFT, RIGHT
	}

	/**
	 * Create a pyramid
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Pyramid(Canvas canvas, float posX, float posY, float posZ,
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

		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.WHITE[0];
			g = RGBColor.WHITE[1];
			b = RGBColor.WHITE[2];
		}
		this.getShapes().add(Face.BASE.ordinal(), new Square(canvas, 0, -0.5f, 0,
				90, 0, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.PURPLE[0];
			g = RGBColor.PURPLE[1];
			b = RGBColor.PURPLE[2];
		}
		this.getShapes().add(Face.BACK.ordinal(), new Triangle(canvas, 0, -0.5f, -0.5f,
				45, 0, 0,
				0.5f, 0.75f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.RED[0];
			g = RGBColor.RED[1];
			b = RGBColor.RED[2];
		}
		this.getShapes().add(Face.FRONT.ordinal(), new Triangle(canvas, 0, -0.5f, 0.5f,
				-45, 0, 0,
				0.5f, 0.75f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.YELLOW[0];
			g = RGBColor.YELLOW[1];
			b = RGBColor.YELLOW[2];
		}
		this.getShapes().add(Face.LEFT.ordinal(), new Triangle(canvas, -0.5f, -0.5f, 0,
				0, -90, -45,
				0.5f, 0.75f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.BLUE[0];
			g = RGBColor.BLUE[1];
			b = RGBColor.BLUE[2];
		}
		this.getShapes().add(Face.RIGHT.ordinal(), new Triangle(canvas, 0.5f, -0.5f, 0,
				0, 90, 45,
				0.5f, 0.75f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));

	}

	/**
	 * Create a default pyramid
	 * 
	 * @see Pyramid#Pyramid(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Pyramid(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}

	/**
	 * Get a face of the pyramid
	 * 
	 * @param face The face of the pyramid according to the Face enumeration
	 * 
	 * @return The face of the pyramid
	 * 
	 * @see Face
	 */
	public Shape getFace(Face face) {
		return this.getShapes().get(face.ordinal());
	}
}