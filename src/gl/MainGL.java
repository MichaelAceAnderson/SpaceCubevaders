package gl;

import com.jogamp.opengl.GL2;

import common.DebugMode;
import common.RGBColor;
import gl.frames.MainFrame;
import objects.volumes.Cube;

public class MainGL
{
	public static void main(String[] args)
	{
		MainFrame glCanvas = new MainFrame();

		// Attendre que le contexte OpenGL soit initialisé
		boolean initialized = false;
		GL2 gl = null;
		while (initialized == false) {
			try {
				gl = glCanvas.getGL().getGL2();
				initialized = true;
			} catch (Exception e) {
				initialized = false;
			}
		}

		// Créer une grille de 11x5 cubes
		for (int row = 0; row < 5; row++) {
			for (int col = -5; col < 6; col++) {
				// Créer un cube
				Cube cube = new Cube(gl, col * 3, row * 3, -40.0f,
						0.0f, 0.0f, 0.0f,
						1.0f, 1.0f, 1.0f,
						0.0f, 0.0f, 0.0f,
						0.0f, 0.0f, 0.0f,
						RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
				glCanvas.getObjects().add(cube);
			}
		}

		// Créer le cube joueur
		Cube player = new Cube(gl, 0.0f, -10.0f, -39.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
		glCanvas.getObjects().add(player);

		DebugMode.printInfo(glCanvas);
	}
}
