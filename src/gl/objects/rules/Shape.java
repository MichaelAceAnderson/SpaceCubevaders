package gl.objects.rules;

import com.jogamp.opengl.awt.GLCanvas;

public abstract class Shape extends GraphicalObject {

	/**
	 * Créer une forme
	 * 
	 * @see GraphicalObject#GraphicalObject(GLCanvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Shape(GLCanvas glCanvas, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {
		super(glCanvas, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);
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