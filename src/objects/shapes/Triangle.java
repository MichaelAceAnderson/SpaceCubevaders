package objects.shapes;

import com.jogamp.opengl.GL2;

import common.RGBColor;
import objects.rules.GraphicalObject;
import objects.rules.Shape;

public class Triangle extends Shape {
	/**
	 * Créer un triangle avec une position, un angle et une taille
	 * 
	 * @param gl        Le contexte OpenGL
	 * @param posX      La position en X
	 * @param posY      La position en Y
	 * @param posZ      La position en Z
	 * @param angleX    L'angle en X
	 * @param angleY    L'angle en Y
	 * @param angleZ    L'angle en Z
	 * @param speedX    La vitesse sur l'axe X
	 * @param speedY    La vitesse sur l'axe Y
	 * @param speedZ    La vitesse sur l'axe Z
	 * @param rotationX La rotation sur l'axe X
	 * @param rotationY La rotation sur l'axe Y
	 * @param rotationZ La rotation sur l'axe Z
	 * @param scale     La taille
	 * @param r         La couleur rouge
	 * @param g         La couleur verte
	 * @param b         La couleur bleue
	 * 
	 * @see GraphicalObject#GraphicalObject(GL2, float, float, float, float, float,
	 *      float, float, float)
	 */
	public Triangle(GL2 gl, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {
		super(gl, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
	}

	/**
	 * Créer un triangle par défaut
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see GraphicalObject#GraphicalObject(GL2, float, float, float, float, float,
	 *      float, float,
	 *      float)
	 */
	public Triangle(GL2 gl) {
		this(gl, 0.0f, 0.0f, -10.0f,
				0.0f, 45.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
	}

	/**
	 * Dessiner le triangle
	 * 
	 * @see GraphicalObject#draw()
	 */
	@Override
	public void draw() {
		// Commencer à dessiner le triangle (lecture bottom-up)
		this.getGl().glBegin(GL2.GL_TRIANGLES);
		{
			// Dessiner chaque point qui compose le triangle sur le plan XYZ
			// Point de gauche
			this.getGl().glVertex3f(-1.0f, 0.0f, 0.0f);
			// Point à droite
			this.getGl().glVertex3f(1.0f, 0.0f, 0.0f);
			// Point du haut
			this.getGl().glVertex3f(0.0f, 1.0f, 0.0f);

			// Définir la couleur pour toutes les opérations à venir
			this.getGl().glColor3f(this.getRed(), this.getGreen(), this.getBlue());
		}
		// Finir de dessiner les triangles
		this.getGl().glEnd();
	}
}