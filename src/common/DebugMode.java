package common;

import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;

public final class DebugMode {
	// Variables booléennes définissant les modes de débogage
	public static enum Mode {
		DRAW_AXIS, DRAW_GRID, VERBOSE, LINE_MODE, RAINBOW
	}

	public static final boolean DRAW_AXIS = true;
	public static final boolean DRAW_GRID = true;
	public static final boolean VERBOSE = true;
	public static final boolean LINE_MODE = true;
	public static final boolean RAINBOW = false;
	public static final boolean[] MODES = { DRAW_AXIS, DRAW_GRID, VERBOSE, LINE_MODE, RAINBOW };

	public static enum OBJECT_TYPE {
		NONE, SHAPE, VOLUME, ITEM
	}

	public static final OBJECT_TYPE DRAW_COLLISIONS = OBJECT_TYPE.VOLUME;
	public static final OBJECT_TYPE DRAW_INFO = OBJECT_TYPE.VOLUME;

	public static void printInfo(Canvas canvas) {

		if (DebugMode.VERBOSE) {
			System.out.println("Mode d'information activé\nConstantes définies:\n");
			for (DebugMode.Mode mode : DebugMode.Mode.values()) {
				System.out.println(mode.toString() + " = " + DebugMode.MODES[mode.ordinal()]);
			}
			System.out.println("Mode d'affichage des collisions: " + DebugMode.DRAW_COLLISIONS.toString());
			System.out.println("Mode d'information 3D: " + DebugMode.DRAW_INFO.toString());

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
