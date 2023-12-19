package gl.frames.rules;

import javax.swing.JFrame;

public abstract class Frame extends JFrame {

	/**
	 * Créer une fenêtre
	 */
	public Frame() {
		super();
		this.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
	}
}
