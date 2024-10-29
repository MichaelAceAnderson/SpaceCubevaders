package main;

import java.awt.event.KeyListener;

import common.Debug;
import common.RGBColor;
import gl.canvas.GameCanvas;
import gl.frames.GLFrame;
import gl.objects.shapes.*;
import gl.objects.volumes.*;

public class DebugGL {
	public static void main(String[] args) {

		for (Debug.Mode mode : Debug.Mode.values()) {
			Debug.setMode(mode, true);
		}

		GLFrame debugFrame = new GLFrame();
		GameCanvas debugCanvas = new GameCanvas(debugFrame);
		debugFrame.playSound(System.getProperty("user.dir") + "/src/games/spacecubevaders/assets/sounds/tada.wav");

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				switch (e.getKeyCode()) {
					case java.awt.event.KeyEvent.VK_ESCAPE:
						debugCanvas.togglePause();
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
		debugCanvas.addKeyListener(keyListener);
		// Demander le focus pour pouvoir utiliser les touches
		debugCanvas.requestFocus();

		// Attendre que le contexte OpenGL soit initialisé
		boolean initialized = false;
		while (initialized == false) {
			try {
				debugCanvas.getGL().getGL2();
				initialized = true;
			} catch (Exception e) {
				initialized = false;
			}
		}

		Square square = new Square(debugCanvas, 3.0f, 0, -10.0f,
				0, 0, 0,
				1.0f, 1.0f, 1.0f,
				0, 0, 0,
				0, 0, 0,
				RGBColor.BLUE[0], RGBColor.BLUE[1], RGBColor.BLUE[2]);
		debugCanvas.getObjects().add(square);

		Cube cube = new Cube(debugCanvas, 0, 0, -10.0f,
				0, 20.0f, 0,
				1.0f, 1.0f, 1.0f,
				0, 0, 0,
				0, 0, 0,
				RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
		debugCanvas.getObjects().add(cube);

		Pyramid pyramid = new Pyramid(debugCanvas, 0, 0, -10.0f,
				0, -20.0f, 0,
				1.0f, 1.0f, 1.0f,
				0, 0, 0,
				0, 0, 0,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
		debugCanvas.getObjects().add(pyramid);

		if (Debug.getMode(Debug.Mode.VERBOSE)) {
			Debug.printInfo(debugCanvas);
		}
	}
}
