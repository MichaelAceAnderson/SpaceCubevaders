package gl;

import com.jogamp.opengl.GL2;

import gl.frames.MainFrame;
import objects.volumes.*;

public class MainGL
{
	/* MÉTHODES */
	// Point d'entrée du programme
	public static void main(String[] args)
	{
		// Créer un canvas (zone de dessin) OpenGL
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

		// Lorsque le contexte OpenGL est initialisé, on peut commencer à dessiner

		// Créer un cube
		Pyramid pyramid = new Pyramid(gl);
		pyramid.setRotation(10.0f, 45.0f, 0.0f);
		// Ajouter le cube à la liste des objets à afficher
		glCanvas.getObjects().add(pyramid);
	}
}
