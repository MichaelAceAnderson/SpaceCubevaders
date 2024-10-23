package gl.frames.rules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.BorderLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import common.Debug;
import common.Debug.Mode;

public abstract class Frame extends JFrame {
	/**
	 * Create a window
	 */
	public Frame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Convert console text to HTML text to display in a window
	 * 
	 * @param text The text to convert
	 * 
	 * @return The text converted to HTML
	 */
	public String textToHTML(String text) {
		String convertedText = "<html>" + text.replace("\n", "<br>") + "</html>";
		return convertedText;
	}

	/**
	 * Show a dialog with a message and a button
	 * 
	 * @param title         Dialog title
	 * @param message       Message to display
	 * @param buttonMessage Button message
	 * @param closeAction   Close action (also triggered by button click)
	 */
	public void showMessageDialog(String title, String message, String buttonMessage, WindowAdapter closeAction) {

		JDialog dialog = new JDialog();
		dialog.setTitle(title);
		dialog.setLayout(new BorderLayout());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		dialog.addWindowListener(closeAction);
		dialog.setVisible(true);
		dialog.add(new JLabel(message, JLabel.CENTER), BorderLayout.CENTER);
		JButton button = new JButton(buttonMessage);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		dialog.add(button, BorderLayout.SOUTH);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
	}

	/**
	 * Show a dialog with a message and a button (closes the window on button click)
	 * 
	 * @param title         Dialog title
	 * @param message       Message to display
	 * @param buttonMessage Button message
	 */
	public void showMessageDialog(String title, String message, String buttonMessage) {
		this.showMessageDialog(title, message, buttonMessage, new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent windowEvent) {
				Frame.this.dispose();
			}
		});
	}

	/**
	 * Play a sound
	 * 
	 * @param soundPath Path to the sound
	 */
	public void playSound(final String soundPath) {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					File f = new File(soundPath);
					AudioInputStream audioInput = AudioSystem.getAudioInputStream(f.toURI().toURL());
					clip.open(audioInput);
					clip.start();
				} catch (Exception e) {
					if (Debug.getMode(Mode.VERBOSE)) {
						System.err.println("Error while playing sound " + soundPath + ":");
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
