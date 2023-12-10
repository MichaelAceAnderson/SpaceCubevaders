package main;

import com.jogamp.opengl.GL2;

import gl.common.DebugMode;
import gl.common.RGBColor;
import gl.frames.MainFrame;
import gl.objects.shapes.*;
import gl.objects.volumes.*;

public class DebugGL {
	public static void main(String[] args) {
		MainFrame glCanvas = new MainFrame();

		// Attendre que le contexte OpenGL soit initialisé
		boolean initialized = false;
		while (initialized == false) {
			try {
				glCanvas.getGL().getGL2();
				initialized = true;
			} catch (Exception e) {
				initialized = false;
			}
		}

		Square square = new Square(glCanvas, 1.0f, 0.0f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
		glCanvas.getObjects().add(square);

		Triangle triangle = new Triangle(glCanvas, 2.0f, 0.0f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
		glCanvas.getObjects().add(triangle);

		Cube cube = new Cube(glCanvas, 0.0f, 1.0f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.PURPLE[0], RGBColor.PURPLE[1], RGBColor.PURPLE[2]);
		glCanvas.getObjects().add(cube);

		Pyramid pyramid = new Pyramid(glCanvas, 0.0f, 1.5f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
				RGBColor.YELLOW[0], RGBColor.YELLOW[1], RGBColor.YELLOW[2]);
		glCanvas.getObjects().add(pyramid);

		DebugMode.printInfo(glCanvas);
	}
}
