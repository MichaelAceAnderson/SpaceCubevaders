package main;

import java.awt.event.KeyListener;

import common.Debug;
import common.RGBColor;
import gl.canvas.MainCanvas;
import gl.objects.items.*;
import gl.objects.shapes.*;
import gl.objects.volumes.*;

public class DebugGL {
	public static void main(String[] args) {

		for (Debug.Mode mode : Debug.Mode.values()) {
			Debug.setMode(mode, true);
		}

		MainCanvas canvas = new MainCanvas();

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				switch (e.getKeyCode()) {
					case java.awt.event.KeyEvent.VK_ESCAPE:
						canvas.togglePause();
						break;
				}
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
			}

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
			}
		};
		canvas.addKeyListener(keyListener);
		// Demander le focus pour pouvoir utiliser les touches
		canvas.requestFocus();

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

		Square square = new Square(canvas, 1.0f, 0.0f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
		canvas.getObjects().add(square);

		Triangle triangle = new Triangle(canvas, 2.0f, 0.0f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
		canvas.getObjects().add(triangle);

		Cube cube = new Cube(canvas, 0.0f, 1.0f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.PURPLE[0], RGBColor.PURPLE[1], RGBColor.PURPLE[2]);
		canvas.getObjects().add(cube);

		Pyramid pyramid = new Pyramid(canvas, 0.0f, 1.5f, -10.0f,
				30.0f, 25.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f,
				RGBColor.YELLOW[0], RGBColor.YELLOW[1], RGBColor.YELLOW[2]);
		canvas.getObjects().add(pyramid);

		Square zTest = new Square(canvas, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f,
				RGBColor.YELLOW[0], RGBColor.RED[1], RGBColor.RED[2]);
		canvas.getObjects().add(zTest);

		if (Debug.getMode(Debug.Mode.VERBOSE)) {
			Debug.printInfo(canvas);
		}
	}
}
