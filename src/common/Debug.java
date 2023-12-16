package common;

import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;

public final class Debug {
	// Variables booléennes définissant les modes de débogage
	public static enum Mode {
		DRAW_AXIS, DRAW_GRID, DRAW_COLLISIONS, DRAW_INFO, VERBOSE, RAINBOW, LINE_MODE, CONTOURING
	}

	public static boolean DRAW_AXIS;
	public static boolean DRAW_GRID;
	public static boolean DRAW_COLLISIONS;
	public static boolean DRAW_INFO;
	public static boolean VERBOSE;
	public static boolean RAINBOW;
	public static boolean LINE_MODE;
	public static boolean CONTOURING;

	public static boolean[] MODE = {
			Debug.DRAW_AXIS,
			Debug.DRAW_GRID,
			Debug.DRAW_COLLISIONS,
			Debug.DRAW_INFO,
			Debug.VERBOSE,
			Debug.RAINBOW,
			Debug.LINE_MODE,
			Debug.CONTOURING
	};

	/**
	 * Définir un mode de débogage
	 * 
	 * @param enabled Vrai ou faux pour activer ou désactiver le mode
	 */
	public static void setMode(Mode mode, boolean enabled) {
		MODE[mode.ordinal()] = enabled;
	}

	/**
	 * Récupérer la valeur d'un mode
	 * 
	 * @param mode Mode à récupérer
	 */
	public static boolean getMode(Mode mode) {
		return MODE[mode.ordinal()];
	}

	/**
	 * Récupérer les modes de débogage
	 */
	public static String getModes() {
		String modes = "Modes de débogage activés : \n";
		for (Mode mode : Mode.values()) {
			modes += mode.toString() + "=" + MODE[mode.ordinal()] + "\n";
		}
		return modes;
	}

	/**
	 * Afficher les informations de débogage
	 * 
	 * @param canvas Canvas concerné
	 */
	public static void printInfo(Canvas canvas) {
		System.out.println(Debug.getModes());

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
