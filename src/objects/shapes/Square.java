
package objects.shapes;

import com.jogamp.opengl.GL2;

import common.RGBColor;
import objects.GraphicalObject;

public class Square extends GraphicalObject {
	/* CONSTRUCTEURS */
	/**
	 * Créer un carré avec une position, un angle et une taille
	 * 
	 * @param gl Le contexte OpenGL
	 * @param posX La position en X
	 * @param posY La position en Y
	 * @param posZ La position en Z
	 * @param angleX L'angle en X
	 * @param angleY L'angle en Y
	 * @param angleZ L'angle en Z
	 * @param scaleX La taille sur l'axe X
	 * @param scaleY La taille sur l'axe Y
	 * @param scaleZ La taille sur l'axe Z
	 * @param r La couleur rouge
	 * @param g La couleur verte
	 * @param b La couleur bleue
	 * 
	 * @see GraphicalObject#GraphicalObject(GL2, float, float, float, float, float,
	 */
	public Square(GL2 gl, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float r, float g, float b) {
		super(gl, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				r, g, b);
	}

	/**
	 * Créer un carré par défaut
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see Square#Square(GL2, float, float, float, float, float, float, float,
	 *      float)
	 */
	public Square(GL2 gl) {
		this(gl, 0.0f, 0.0f, -10.0f,
				0.0f, 45.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
	}

	/**
	 * Dessiner un carré
	 * 
	 * @see GraphicalObject#draw()
	 */
	public void draw() {
		// Commencer à dessiner le carré (lecture bottom-up)
		this.getGl().glBegin(GL2.GL_QUADS);
		{
			// Dessiner chaque point qui compose le carré sur le plan XYZ
			// Point du haut à gauche
			this.getGl().glVertex3f(-1f, 1f, 0f);
			// Point du haut à droite
			this.getGl().glVertex3f(1f, 1f, 0f);
			// Point du bas à droite
			this.getGl().glVertex3f(1f, -1f, 0f);
			// Point du bas à gauche
			this.getGl().glVertex3f(-1f, -1f, 0f);

			// Définir la couleur pour toutes les opérations à venir
			this.getGl().glColor3f(this.red, this.green, this.blue);
		}
		// Finir de dessiner le carré
		this.getGl().glEnd();
	}
}