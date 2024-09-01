package games.spacecubevaders.structures;

import java.util.ArrayList;

import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.volumes.Cube;

public class Shelter {
	public enum Composant {
		LEFT, RIGHT, TOP, TOP_LEFT, TOP_RIGHT
	}

	private ArrayList<Cube> blocks;

	/**
	 * Créer un abri
	 * 
	 * @param canvas    Canvas dans lequel l'abri est affiché
	 * @param posX      Position X de l'abri
	 * @param posY      Position Y de l'abri
	 * @param posZ      Position Z de l'abri
	 * @param angleX    Angle X de l'abri
	 * @param angleY    Angle Y de l'abri
	 * @param angleZ    Angle Z de l'abri
	 * @param scaleX    Echelle X de l'abri
	 * @param scaleY    Echelle Y de l'abri
	 * @param scaleZ    Echelle Z de l'abri
	 * @param speedX    Vitesse X de l'abri
	 * @param speedY    Vitesse Y de l'abri
	 * @param speedZ    Vitesse Z de l'abri
	 * @param rotationX Rotation X de l'abri
	 * @param rotationY Rotation Y de l'abri
	 * @param rotationZ Rotation Z de l'abri
	 * @param r         Couleur rouge de l'abri
	 * @param g         Couleur verte de l'abri
	 * @param b         Couleur bleue de l'abri
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
		this.getBlocks().add(Composant.LEFT.ordinal(), left);
		canvas.getObjects().add(left);

		Cube right = new Cube(canvas, posX + 1, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Composant.RIGHT.ordinal(), right);
		canvas.getObjects().add(right);

		Cube top = new Cube(canvas, posX, posY + 1, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Composant.TOP.ordinal(), top);
		canvas.getObjects().add(top);

		Cube topLeft = new Cube(canvas, posX - 1, posY + 1, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Composant.TOP_LEFT.ordinal(), topLeft);
		canvas.getObjects().add(topLeft);

		Cube topRight = new Cube(canvas, posX + 1, posY + 1, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
		this.getBlocks().add(Composant.TOP_RIGHT.ordinal(), topRight);
		canvas.getObjects().add(topRight);

	}

	/**
	 * Créer un cube par défaut
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
	 * Définir les blocs de l'abri
	 * 
	 * @param blocks Tableau des blocs de l'abri
	 */
	public void setBlocks(ArrayList<Cube> blocks) {
		this.blocks = blocks;
	}

	/**
	 * Liste des blocs de l'abri
	 */
	public ArrayList<Cube> getBlocks() {
		return this.blocks;
	}
}