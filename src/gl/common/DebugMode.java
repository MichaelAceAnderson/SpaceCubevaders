package gl.common;

import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;

public final class DebugMode {
	// Variables booléennes définissant les modes de débogage
	public static enum Mode {
		VERBOSE, LINE_MODE, RAINBOW
	}
	public static final boolean VERBOSE = true;
	public static final boolean LINE_MODE = true;
	public static final boolean RAINBOW = false;
	public static final boolean[] MODES = { VERBOSE, LINE_MODE, RAINBOW };

	// Types de collisions à dessiner
	public static enum COLLISION_TYPE {
		NONE, VOLUME, SHAPE
	}

	public static final COLLISION_TYPE DRAW_COLLISIONS = COLLISION_TYPE.VOLUME;

	public static void printInfo(Canvas canvas) {

		if (DebugMode.VERBOSE) {
			System.out.println("Mode d'information activé\nConstantes définies:\n");
			// Pour toute constante dans l'enum DebugMode.Mode
			for (DebugMode.Mode mode : DebugMode.Mode.values()) {
				// Afficher la constante et sa valeur
				System.out.println(mode.toString() + " = " + DebugMode.MODES[mode.ordinal()]);
			}

			for (GraphicalObject object : canvas.getObjects()) {
				// Afficher les propriétés de l'objet
				System.out.println(object.toString());
				// Vérifier les collisions avec les autres objets
				for (GraphicalObject otherObject : canvas.getObjects()) {
					if (object != otherObject) {
						if (object.isColliding(otherObject)) {
							System.out.println(
									"\t" + "Cet objet est en collision avec un objet "
											+ otherObject.getClass().getSimpleName()
											+ " aux coordonnées " + otherObject.getPosX() + ", " + otherObject.getPosY()
											+ ", " + otherObject.getPosZ());
						}
					}
				}
			}
		}
	}
}
