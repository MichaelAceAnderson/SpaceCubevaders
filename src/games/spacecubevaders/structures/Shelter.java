package games.spacecubevaders.structures;

import java.util.ArrayList;

import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.volumes.Cube;

public class Shelter {
	public enum Component {
		LEFT, RIGHT, TOP, TOP_LEFT, TOP_RIGHT
	}

	private ArrayList<Cube> blocks;

	/**
	 * Create a shelter
	 * 
	 * @param canvas    Canvas in which the shelter is displayed
	 * @param posX      X position of the shelter
	 * @param posY      Y position of the shelter
	 * @param posZ      Z position of the shelter
	 * @param angleX    X angle of the shelter
	 * @param angleY    Y angle of the shelter
	 * @param angleZ    Z angle of the shelter
	 * @param scaleX    X scale of the shelter
	 * @param scaleY    Y scale of the shelter
	 * @param scaleZ    Z scale of the shelter
	 * @param speedX    X speed of the shelter
	 * @param speedY    Y speed of the shelter
	 * @param speedZ    Z speed of the shelter
	 * @param rotationX X rotation of the shelter
	 * @param rotationY Y rotation of the shelter
	 * @param rotationZ Z rotation of the shelter
	 * @param r         Red color of the shelter
	 * @param g         Green color of the shelter
	 * @param b         Blue color of the shelter
	 * 
	 */
	public Shelter(Canvas canvas, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {

		this.setBlocks(new ArrayList<Cube>());

		Cube left = new Cube(canvas, posX - 1, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Component.LEFT.ordinal(), left);
		canvas.getObjects().add(left);

		Cube right = new Cube(canvas, posX + 1, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Component.RIGHT.ordinal(), right);
		canvas.getObjects().add(right);

		Cube top = new Cube(canvas, posX, posY + 1, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Component.TOP.ordinal(), top);
		canvas.getObjects().add(top);

		Cube topLeft = new Cube(canvas, posX - 1, posY + 1, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Component.TOP_LEFT.ordinal(), topLeft);
		canvas.getObjects().add(topLeft);

		Cube topRight = new Cube(canvas, posX + 1, posY + 1, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Component.TOP_RIGHT.ordinal(), topRight);
		canvas.getObjects().add(topRight);

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
	public Shelter(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}

	/**
	 * Set the shelter blocks
	 * 
	 * @param blocks Array of shelter blocks
	 */
	public void setBlocks(ArrayList<Cube> blocks) {
		this.blocks = blocks;
	}

	/**
	 * List of shelter blocks
	 */
	public ArrayList<Cube> getBlocks() {
		return this.blocks;
	}
}