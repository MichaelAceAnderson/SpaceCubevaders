package gl.objects.volumes;


import common.DebugMode;
import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;
import gl.objects.rules.Shape;
import gl.objects.rules.Volume;
import gl.objects.shapes.Square;

public class Cube extends Volume {
	private enum Face {
		MAX_Y, MAX_Z, MIN_Z, MIN_X, MAX_X, MIN_Y
	}

	/**
	 * Créer un cube
	 * 
	 * @see GraphicalObject#GraphicalObject(Canvas, float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float,
	 *      float, float, float)
	 * 
	 */
	public Cube(Canvas canvas, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {
		// Appeler le constructeur de la classe mère pour instancier l'objet graphique
		super(canvas, posX, posY, posZ,
				angleX, angleY, angleZ,
				scaleX, scaleY, scaleZ,
				speedX, speedY, speedZ,
				rotationX, rotationY, rotationZ,
				r, g, b);

		// Ajouter la face supérieure
		if (DebugMode.RAINBOW) {
			r = RGBColor.CYAN[0];
			g = RGBColor.CYAN[1];
			b = RGBColor.CYAN[2];
		}
		this.getShapes().add(new Square(canvas, 0, 1, 0,
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
		this.getShapes().add(new Square(canvas, 0, 0, 1,
				0, 0, 0,
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face arrière
		if (DebugMode.RAINBOW) {
			r = RGBColor.GREEN[0];
			g = RGBColor.GREEN[1];
			b = RGBColor.GREEN[2];
		}
		this.getShapes().add(new Square(canvas, 0, 0, -1,
				0, 0, 0,
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face gauche
		if (DebugMode.RAINBOW) {
			r = RGBColor.YELLOW[0];
			g = RGBColor.YELLOW[1];
			b = RGBColor.YELLOW[2];
		}
		this.getShapes().add(new Square(canvas, -1, 0, 0,
				0, -90, 0,
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face droite
		if (DebugMode.RAINBOW) {
			r = RGBColor.BLUE[0];
			g = RGBColor.BLUE[1];
			b = RGBColor.BLUE[2];
		}
		this.getShapes().add(new Square(canvas, 1, 0, 0,
				0, 90, 0,
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
		// Ajouter la face inférieure
		if (DebugMode.RAINBOW) {
			r = RGBColor.MAGENTA[0];
			g = RGBColor.MAGENTA[1];
			b = RGBColor.MAGENTA[2];
		}
		this.getShapes().add(new Square(canvas, 0, -1, 0,
				90, 0, 0,
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
				r, g, b));
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
	public Cube(Canvas canvas) {
		this(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}

	/**
	 * Récupérer une face du cube
	 * 
	 * @param face La face du cube selon l'énumération Face
	 * 
	 * @return La face du cube
	 * 
	 * @see Face
	 */
	public Shape getFace(Face face) {
		return this.getShapes().get(face.ordinal());
	}
}