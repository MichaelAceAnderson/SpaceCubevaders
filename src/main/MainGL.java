package main;

import common.Debug;
import common.Debug.Mode;
import games.spacecubevaders.SpaceCubevaders;
import gl.canvas.GameCanvas;
import gl.frames.GLFrame;

public class MainGL {
	public static void main(String[] args) {

		// Feel free to play with these debug modes!

		// Debug.setMode(Mode.DRAW_AXIS, true);
		// Debug.setMode(Mode.DRAW_GRID, true);
		// Debug.setMode(Mode.DRAW_COLLISIONS, true);
		// Debug.setMode(Mode.DRAW_INFO, true);
		// Debug.setMode(Mode.VERBOSE, true);
		// Debug.setMode(Mode.RAINBOW, true);

		// Note: LINE_MODE and CONTOURING are mutually exclusive as they
		// render objects in two different ways.
		// LINE_MODE takes precedence over CONTOURING.

		Debug.setMode(Mode.LINE_MODE, true);
		// Debug.setMode(Mode.CONTOURING, true);

		GLFrame gameFrame = new GLFrame();
		GameCanvas gameCanvas = new GameCanvas(gameFrame);

		// Wait for the OpenGL context to be initialized
		boolean initialized = false;
		while (initialized == false) {
			try {
				gameCanvas.getGL().getGL2();
				initialized = true;
			} catch (Exception e) {
				initialized = false;
			}
		}

		SpaceCubevaders game = new SpaceCubevaders(gameCanvas);

		if (Debug.getMode(Mode.VERBOSE)) {
			System.out.println(game);
			Debug.printInfo(gameCanvas);
		}
	}
}
