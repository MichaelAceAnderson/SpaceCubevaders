package main;

import common.Debug;
import common.Debug.Mode;
import games.spaceinvaders.SpaceInvaders;
import gl.canvas.MainCanvas;

public class MainGL {
	public static void main(String[] args) {

		for (Debug.Mode mode : Debug.Mode.values()) {
			Debug.setMode(mode, true);
		}
		Debug.setMode(Debug.Mode.RAINBOW, false);

		MainCanvas canvas = new MainCanvas();

		// Attendre que le contexte OpenGL soit initialisé
		boolean initialized = false;
		while (initialized == false) {
			try {
				canvas.getGL().getGL2();
				initialized = true;
			} catch (Exception e) {
				initialized = false;
			}
		}

		// Créer un jeu
		SpaceInvaders game = new SpaceInvaders(canvas);

		if (Debug.getMode(Mode.VERBOSE)) {
			System.out.println(game);
			Debug.printInfo(canvas);
		}
	}
}
