package gl.objects.rules;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import common.DebugMode;
import gl.canvas.rules.Canvas;

public abstract class Shape extends GraphicalObject {

	/**
	 * Créer une forme
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Shape(Canvas canvas, float posX, float posY, float posZ,
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
	 * Dessiner cette forme.
	 * Cette méthode doit être implémentée dans les classes filles afin de
	 * déterminer comment la forme doit être dessinée et affichée dans la méthode
	 * display().
	 * 
	 * @see GraphicalObject#draw()
	 * @see GraphicalObject#display()
	 */
	@Override
	public abstract void draw();

	/**
	 * Dessiner les collisions de cette forme
	 * 
	 * @see GraphicalObject#drawCollisions()
	 */
	@Override
	public void drawCollisions() {
		// Dessiner la boîte de collision à partir d'un cube
		if (DebugMode.DRAW_COLLISIONS == DebugMode.COLLISION_TYPE.SHAPE) {
			this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			this.getGl2().glColor3f(1.0f, 1.0f, 1.0f);
			GLUT glut = new GLUT();
			glut.glutWireCube(2.0f);
			this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		}
	}
}