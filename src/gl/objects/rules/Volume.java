package gl.objects.rules;

import java.util.ArrayList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import common.DebugMode;
import common.RGBColor;
import gl.canvas.rules.Canvas;

public abstract class Volume extends GraphicalObject {
	// Formes composant le volume
	private ArrayList<Shape> shapes;


	/**
	 * Créer un volume
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Volume(Canvas canvas, float posX, float posY, float posZ,
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
		this.setShapes(new ArrayList<Shape>());
	}

	/**
	 * Définir les formes composant ce volume
	 * 
	 * @param shapes Les formes composant ce volume
	 */
	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	/**
	 * Obtenir les formes composant ce volume
	 * 
	 * @return Les formes composant ce volume
	 */
	public ArrayList<Shape> getShapes() {
		return this.shapes;
	}

	/**
	 * Récupérer une forme composant ce volume à partir de son index
	 * 
	 * @param index L'index de la forme à récupérer
	 */
	public Shape getShape(int index) {
		return this.getShapes().get(index);
	}

	/**
	 * Dessiner ce volume
	 * 
	 * @see GraphicalObject#draw()
	 * @see Shape#display()
	 */
	@Override
	public void draw() {
		// Dessiner ce volume consiste à afficher toutes les formes qui le composent
		for (Shape shape : this.getShapes()) {
			shape.display();
		}
	}

	/**
	 * Dessiner les collisions de cette forme
	 * 
	 * @see GraphicalObject#drawCollisions()
	 */
	@Override
	public void drawCollisions() {
		// Dessiner la boîte de collision à partir d'un cube
		if (DebugMode.DRAW_COLLISIONS == DebugMode.OBJECT_TYPE.VOLUME) {
			this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			this.getGl2().glColor3f(1.0f, 1.0f, 1.0f);
			GLUT glut = new GLUT();
			glut.glutWireCube(2.0f);
			this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		}
	}

	/**
	 * Attacher des informations à ce volume
	 * 
	 * @see GraphicalObject#drawInfos()
	 */
	@Override
	public void drawInfos() {
		if (DebugMode.DRAW_INFO == DebugMode.OBJECT_TYPE.VOLUME) {
			this.getGl2().glColor3f(RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
			this.getGl2().glRasterPos3f(this.getPosX(), this.getPosY(), this.getPosZ());
			GLUT glut = new GLUT();
			String[] lines = this.get3DInfo().split("\n");
			for (int line = 0; line < lines.length; line++) {
				this.getGl2().glRasterPos3f(this.getPosX(), this.getPosY() - line, this.getPosZ());
				glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, lines[line]);
			}
		}
	}
}