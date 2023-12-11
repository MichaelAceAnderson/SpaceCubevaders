package gl.objects.rules;

import java.util.ArrayList;

import com.jogamp.opengl.util.gl2.GLUT;

import common.DebugMode;
import common.RGBColor;
import gl.canvas.rules.Canvas;

public abstract class Item extends GraphicalObject {
	// Objets composants l'item
	public ArrayList<GraphicalObject> components;

	/**
	 * Créer un item
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 */
	public Item(Canvas canvas, float posX, float posY, float posZ,
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
		this.setComponents(new ArrayList<GraphicalObject>());
	}

	/**
	 * Définir les objets composant cet item
	 * 
	 * @param components Les objets composant cet item
	 */
	public void setComponents(ArrayList<GraphicalObject> components) {
		this.components = components;
	}

	/**
	 * Obtenir les objets composant cet item
	 * 
	 * @return Les objets composant cet item
	 */
	public ArrayList<GraphicalObject> getComponents() {
		return this.components;
	}

	/**
	 * Récupérer une forme composant cet item à partir de son index
	 * 
	 * @param index L'index de la forme à récupérer
	 */
	public GraphicalObject getComponent(int index) {
		return this.getComponents().get(index);
	}

	/**
	 * Dessiner cet item
	 * 
	 * @see GraphicalObject#draw()
	 * @see GraphicalObject#display()
	 */
	@Override
	public void draw() {
		// Dessiner cet item consiste à afficher tous Les objets qui le composent
		for (GraphicalObject component : this.getComponents()) {
			component.display();
		}
	}

	/**
	 * Dessiner les collisions de cette forme
	 * 
	 * @see GraphicalObject#drawCollisions()
	 */
	@Override
	public void drawCollisions() {
		// Ne pas dessiner les collisions puisque ce sont les objets qui le composent
		// qui le font
	}

	/**
	 * Attacher des informations à cet item
	 * 
	 * @see GraphicalObject#drawInfos()
	 */
	@Override
	public void drawInfos() {
		if (DebugMode.DRAW_INFO == DebugMode.OBJECT_TYPE.ITEM) {
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