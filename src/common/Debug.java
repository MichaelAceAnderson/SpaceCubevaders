package common;

import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;

public final class Debug {
	// Boolean variables defining debug modes
	public static enum Mode {
		DRAW_AXIS, DRAW_GRID, DRAW_COLLISIONS, DRAW_INFO, VERBOSE, RAINBOW, LINE_MODE, CONTOURING
	}

	public static boolean DRAW_AXIS = false;
	public static boolean DRAW_GRID = false;
	public static boolean DRAW_COLLISIONS = false;
	public static boolean DRAW_INFO = false;
	public static boolean VERBOSE = false;
	public static boolean RAINBOW = false;
	public static boolean LINE_MODE = false;
	public static boolean CONTOURING = false;

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
	 * Set a debug mode
	 * 
	 * @param enabled True or false to enable or disable the mode
	 */
	public static void setMode(Mode mode, boolean enabled) {
		MODE[mode.ordinal()] = enabled;
	}

	/**
	 * Get the value of a mode
	 * 
	 * @param mode Mode to retrieve
	 */
	public static boolean getMode(Mode mode) {
		return MODE[mode.ordinal()];
	}

	/**
	 * Get the debug modes
	 */
	public static String getModes() {
		String modes = "Enabled debug modes: \n";
		for (Mode mode : Mode.values()) {
			modes += mode.toString() + "=" + MODE[mode.ordinal()] + "\n";
		}
		return modes;
	}

	/**
	 * Display debug information
	 * 
	 * @param canvas Concerned canvas
	 */
	public static void printInfo(Canvas canvas) {
		System.out.println(Debug.getModes());

		for (GraphicalObject object : canvas.getObjects()) {
			// Display the properties of the object
			System.out.println(object.toString());
			// Check for collisions with other objects
			for (GraphicalObject otherObject : canvas.getObjects()) {
				if (object != otherObject) {
					if (object.isColliding(otherObject)) {
						System.out.println(
								"\t" + "This object is colliding with an object "
										+ otherObject.getClass().getSimpleName()
										+ " at coordinates " + otherObject.getPosX() + ", " + otherObject.getPosY()
										+ ", " + otherObject.getPosZ());
					}
				}
			}
		}
	}
}
