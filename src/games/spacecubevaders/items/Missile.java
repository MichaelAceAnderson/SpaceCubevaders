package games.spacecubevaders.items;

import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.rules.Composite;
import gl.objects.volumes.Cube;
import gl.objects.volumes.Pyramid;

public class Missile extends Composite {
	/**
	 * Create a Missile
	 * 
	 * @see Composite#Composite(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Missile(Canvas canvas, float posX, float posY, float posZ,
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
		this.getComponents().add(new Cube(canvas, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX * 0.5f, scaleY * 2, scaleZ * 0.5f,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b));
		this.getComponents().add(new Pyramid(canvas, posX, posY + scaleY * 2, posZ,
				angleX, angleY, angleZ,
				scaleX * 0.5f, scaleY * 0.5f, scaleZ * 0.5f,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b));
	}

	/**
	 * Create a default Missile
	 * 
	 * @see Missile#Missile(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Missile(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}
}