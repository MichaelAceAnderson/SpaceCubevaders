package gl.objects.rules;

import com.jogamp.opengl.GL2;

import gl.common.RGBColor;
import gl.objects.shapes.Square;

public abstract class Shape extends GraphicalObject {
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
	 * @param speedX La vitesse sur l'axe X
	 * @param speedY La vitesse sur l'axe Y
	 * @param speedZ La vitesse sur l'axe Z
	 * @param rotationX La rotation sur l'axe X
	 * @param rotationY La rotation sur l'axe Y
	 * @param rotationZ La rotation sur l'axe Z
	 * @param r La couleur rouge
	 * @param g La couleur verte
	 * @param b La couleur bleue
	 * 
	 * @see GraphicalObject#GraphicalObject(GL2, float, float, float, float, float,
	 */
	public Shape(GL2 gl, float posX, float posY, float posZ,
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
	 * Créer un carré par défaut
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see Square#Square(GL2, float, float, float, float, float, float, float,
	 *      float)
	 */
	public Shape(GL2 gl) {
		this(gl, 0.0f, 0.0f, -10.0f,
				0.0f, 45.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
	}

	/**
	 * Dessiner cette forme.
	 * Cette méthode doit être implémentée dans les classes filles afin de
	 * déterminer comment la forme doit être dessinée et affichée dans la méthode
	 * display().
	 * 
	 * @see GraphicalObject#draw()
	 * @see GraphicalObject#display()
	 */
	public abstract void draw();
}