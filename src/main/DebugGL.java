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

		Cube cube = new Cube(canvas, 0, 0, -10.0f,
				0, 45.0f, 0,
				1.0f, 1.0f, 1.0f,
				0, 0, 0,
				0, 0, 0,
				RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
		canvas.getObjects().add(cube);

		if (Debug.getMode(Debug.Mode.VERBOSE)) {
			Debug.printInfo(canvas);
		}
	}
}
