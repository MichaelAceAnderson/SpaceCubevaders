package main;

import common.Debug;
import common.Debug.Mode;
import games.spacecubevaders.SpaceCubevaders;
import gl.canvas.GameCanvas;
import gl.frames.GLFrame;

public class MainGL {
	public static void main(String[] args) {

		// N'hésitez pas à jouer avec ces modes de debug !

		// Debug.setMode(Mode.DRAW_AXIS, true);
		// Debug.setMode(Mode.DRAW_GRID, true);
		// Debug.setMode(Mode.DRAW_COLLISIONS, true);
		// Debug.setMode(Mode.DRAW_INFO, true);
		// Debug.setMode(Mode.VERBOSE, true);
		// Debug.setMode(Mode.RAINBOW, true);

		// Note: Le LINE_MODE et le CONTOURING sont mutuellement exclusifs puisqu'ils
		// effectuent le rendu des objets de deux façons différentes
		// Le LINE_MODE prévaut sur le CONTOURING

		Debug.setMode(Mode.LINE_MODE, true);
		// Debug.setMode(Mode.CONTOURING, true);

		GLFrame gameFrame = new GLFrame();
		GameCanvas gameCanvas = new GameCanvas(gameFrame);

		// Attendre que le contexte OpenGL soit initialisé
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
