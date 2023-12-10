package main;

import gl.canvas.MainCanvas;
import gl.common.DebugMode;
import gl.common.RGBColor;
import gl.objects.volumes.Cube;

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

		// Créer une grille de 11x5 cubes
		for (int row = 0; row < 5; row++) {
			for (int col = -5; col < 6; col++) {
				// Créer un cube
				Cube cube = new Cube(canvas, col * 3, row * 3, -40.0f,
						0.0f, 0.0f, 0.0f,
						1.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 0.0f,
						0.0f, 0.0f, 0.0f,
						RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
				canvas.getObjects().add(cube);
			}
		}

		// Créer le cube joueur
		Cube player = new Cube(canvas, 0.0f, -10.0f, -39.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
		canvas.getObjects().add(player);

		DebugMode.printInfo(canvas);
	}
}
