package objects.volumes;

import com.jogamp.opengl.GL2;

import common.DebugMode;
import common.RGBColor;
import objects.rules.Shape;
import objects.rules.Volume;
import objects.shapes.Square;
import objects.shapes.Triangle;

public class Pyramid extends Volume {
	private enum Face {
		BASE, FRONT, BACK, LEFT, RIGHT
	}

	/**
	 * Créer une pyramide avec cinq instances de Triangle, une position, un angle,
	 * une
	 * taille et une couleur
	 * 
	 * @param gl        Le contexte OpenGL
	 * @param posX      La position en X
	 * @param posY      La position en Y
	 * @param posZ      La position en Z
	 * @param angleX    L'angle en X
	 * @param angleY    L'angle en Y
	 * @param angleZ    L'angle en Z
	 * @param scaleX    La taille sur l'axe X
	 * @param scaleY    La taille sur l'axe Y
	 * @param scaleZ    La taille sur l'axe Z
	 * @param speedX    La vitesse sur l'axe X
	 * @param speedY    La vitesse sur l'axe Y
	 * @param speedZ    La vitesse sur l'axe Z
	 * @param rotationX La rotation sur l'axe X
	 * @param rotationY La rotation sur l'axe Y
	 * @param rotationZ La rotation sur l'axe Z
	 * @param r         La couleur rouge
	 * @param g         La couleur verte
	 * @param b         La couleur bleue
	 * 
	 * @see GL2
	 */
	public Pyramid(GL2 gl, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {
		// Appeler le constructeur de la classe mère pour instancier l'objet graphique
		super(gl, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);

		// Ajouter la base de la pyramide
		if (DebugMode.RAINBOW) {
			r = RGBColor.WHITE[0];
			g = RGBColor.WHITE[1];
			b = RGBColor.WHITE[2];
		}
		this.getShapes().add(new Square(gl, 0, 0, 0,
				90, 0, 0,
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face avant
		if (DebugMode.RAINBOW) {
			r = RGBColor.RED[0];
			g = RGBColor.RED[1];
			b = RGBColor.RED[2];
		}
		this.getShapes().add(new Triangle(gl, 0, 0, 1,
				-45, 0, 0,
				1, 1.5f, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face arrière
		if (DebugMode.RAINBOW) {
			r = RGBColor.PURPLE[0];
			g = RGBColor.PURPLE[1];
			b = RGBColor.PURPLE[2];
		}
		this.getShapes().add(new Triangle(gl, 0, 0, -1,
				45, 0, 0,
				1, 1.5f, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face gauche
		if (DebugMode.RAINBOW) {
			r = RGBColor.YELLOW[0];
			g = RGBColor.YELLOW[1];
			b = RGBColor.YELLOW[2];
		}
		this.getShapes().add(new Triangle(gl, -1, 0, 0,
				0, -90, -45,
				1, 1.5f, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face droite
		if (DebugMode.RAINBOW) {
			r = RGBColor.BLUE[0];
			g = RGBColor.BLUE[1];
			b = RGBColor.BLUE[2];
		}
		this.getShapes().add(new Triangle(gl, 1, 0, 0,
				0, 90, 45,
				1, 1.5f, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));

	}

	/**
	 * Créer une pyramide par défaut
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see Pyramid#Pyramid(GL2, float, float, float, float, float, float, float,
	 *      float,
	 *      float, float)
	 */
	public Pyramid(GL2 gl) {
		this(gl, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}

	/**
	 * Récupérer une face de la pyramide
	 * 
	 * @param face La face de la pyramide selon l'énumération Face
	 * 
	 * @return La face de la pyramide
	 * 
	 * @see Face
	 */
	public Shape getFace(Face face) {
		return this.getShapes().get(face.ordinal());
	}
}