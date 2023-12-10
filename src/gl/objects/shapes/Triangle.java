package gl.objects.shapes;

import com.jogamp.opengl.GL2;

import gl.canvas.rules.Canvas;
import gl.common.RGBColor;
import gl.objects.rules.GraphicalObject;
import gl.objects.rules.Shape;

public class Triangle extends Shape {

	/**
	 * Créer un triangle
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Triangle(Canvas canvas, float posX, float posY, float posZ,
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
	}

	/**
	 * Créer un triangle par défaut
	 * 
	 * @see Triangle#Triangle(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Triangle(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
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
		this.getGl2().glBegin(GL2.GL_TRIANGLES);
		{
			// Définir la couleur pour toutes les opérations à venir
			this.getGl2().glColor3f(this.getRed(), this.getGreen(), this.getBlue());
			// Dessiner chaque point qui compose le triangle sur le plan XYZ
			// Point de gauche
			this.getGl2().glVertex3f(-1.0f, 0.0f, 0.0f);
			// Point à droite
			this.getGl2().glVertex3f(1.0f, 0.0f, 0.0f);
			// Point du haut
			this.getGl2().glVertex3f(0.0f, 1.0f, 0.0f);
		}
		// Finir de dessiner les triangles
		this.getGl2().glEnd();
	}
}