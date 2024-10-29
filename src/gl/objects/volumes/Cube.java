package gl.objects.volumes;

import common.Debug;
import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;
import gl.objects.rules.Shape;
import gl.objects.rules.Volume;
import gl.objects.shapes.Square;

public class Cube extends Volume {
	private enum Face {
		TOP, BACK, FRONT, LEFT, RIGHT, BOTTOM
	}

	/**
	 * Create a cube
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Cube(Canvas canvas, float posX, float posY, float posZ,
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
			r = RGBColor.CYAN[0];
			g = RGBColor.CYAN[1];
			b = RGBColor.CYAN[2];
		}
		this.getShapes().add(Face.TOP.ordinal(), new Square(canvas, 0, 0.5f, 0,
				90, 0, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.RED[0];
			g = RGBColor.RED[1];
			b = RGBColor.RED[2];
		}
		this.getShapes().add(Face.BACK.ordinal(), new Square(canvas, 0, 0, -0.5f,
				0, 0, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.GREEN[0];
			g = RGBColor.GREEN[1];
			b = RGBColor.GREEN[2];
		}
		this.getShapes().add(Face.FRONT.ordinal(), new Square(canvas, 0, 0, 0.5f,
				0, 0, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.YELLOW[0];
			g = RGBColor.YELLOW[1];
			b = RGBColor.YELLOW[2];
		}
		this.getShapes().add(Face.LEFT.ordinal(), new Square(canvas, -0.5f, 0, 0,
				0, -90, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.BLUE[0];
			g = RGBColor.BLUE[1];
			b = RGBColor.BLUE[2];
		}
		this.getShapes().add(Face.RIGHT.ordinal(), new Square(canvas, 0.5f, 0, 0,
				0, 90, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		if (Debug.getMode(Debug.Mode.RAINBOW)) {
			r = RGBColor.MAGENTA[0];
			g = RGBColor.MAGENTA[1];
			b = RGBColor.MAGENTA[2];
		}
		this.getShapes().add(Face.BOTTOM.ordinal(), new Square(canvas, 0, -0.5f, 0,
				90, 0, 0,
				0.5f, 0.5f, 0.5f,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
	}

	/**
	 * Create a default cube
	 * 
	 * @see Cube#Cube(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Cube(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}

	/**
	 * Get a face of the cube
	 * 
	 * @param face The face of the cube according to the Face enumeration
	 * 
	 * @return The face of the cube
	 * 
	 * @see Face
	 */
	public Shape getFace(Face face) {
		return this.getShapes().get(face.ordinal());
	}
}