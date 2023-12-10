package gl.common;

import gl.frames.MainFrame;
import gl.objects.rules.GraphicalObject;

public final class DebugMode {
	public static enum Mode {
		VERBOSE, LINE_MODE, RAINBOW
	}

	public static final boolean VERBOSE = true;
	public static final boolean LINE_MODE = true;
	public static final boolean RAINBOW = false;

	public static final boolean[] MODES = { VERBOSE, LINE_MODE, RAINBOW };

	public static void printInfo(MainFrame glCanvas) {

		if (DebugMode.VERBOSE) {
			System.out.println("Mode d'information activé\nConstantes définies:\n");
			// Pour toute constante dans l'enum DebugMode.Mode
			for (DebugMode.Mode mode : DebugMode.Mode.values()) {
				// Afficher la constante et sa valeur
				System.out.println(mode.toString() + " = " + DebugMode.MODES[mode.ordinal()]);
			}

			for (GraphicalObject object : glCanvas.getObjects()) {
				// Afficher les propriétés de l'objet
				System.out.println(object.toString());
				// Vérifier les collisions avec les autres objets
				for (GraphicalObject otherObject : glCanvas.getObjects()) {
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
