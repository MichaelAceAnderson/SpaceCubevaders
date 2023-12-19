package main;

import common.Debug;
import common.Debug.Mode;
import games.spacecubevaders.SpaceCubevaders;
import gl.canvas.MainCanvas;
import gl.frames.GLFrame;

public class MainGL {
	public static void main(String[] args) {

		for (Debug.Mode mode : Debug.Mode.values()) {
			Debug.setMode(mode, false);
		}
		Debug.setMode(Mode.LINE_MODE, true);

		GLFrame gameFrame = new GLFrame();
		MainCanvas gameCanvas = new MainCanvas(gameFrame);

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

		// Créer un jeu
		SpaceCubevaders game = new SpaceCubevaders(gameCanvas);

		if (Debug.getMode(Mode.VERBOSE)) {
			System.out.println(game);
			Debug.printInfo(gameCanvas);
		}
	}
}
