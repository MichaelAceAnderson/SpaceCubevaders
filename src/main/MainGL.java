package main;

import common.DebugMode;
import game.Game;
import gl.canvas.MainCanvas;

public class MainGL {
	public static void main(String[] args) {
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
		Game game = new Game(canvas);

		// Afficher les informations du jeu
		System.out.println(game);

		DebugMode.printInfo(canvas);
	}
}
