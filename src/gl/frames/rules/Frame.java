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
	 * Créer une fenêtre
	 */
	public Frame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Convertir un texte console en texte HTML pour l'afficher dans une fenêtre
	 * 
	 * @param text Le texte à convertir
	 * 
	 * @return Le texte converti en HTML
	 */
	public String textToHTML(String text) {
		String convertedText = "<html>" + text.replace("\n", "<br>") + "</html>";
		return convertedText;
	}

	/**
	 * Montrer un dialog avec un message et un bouton
	 * 
	 * @param title         Titre du dialog
	 * @param message       Message à afficher
	 * @param buttonMessage Message du bouton
	 * @param closeAction   Action de la fermeture (également déclenchée au clic du
	 *                      bouton)
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
	 * Montrer un dialog avec un message et un bouton (ferme la fenêtre au clic du
	 * bouton)
	 * 
	 * @param title         Titre du dialog
	 * @param message       Message à afficher
	 * @param buttonMessage Message du bouton
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
	 * Jouer un son
	 * 
	 * @param soundPath Chemin du son
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
						System.err.println("Erreur lors de la lecture du son" + soundPath + ":");
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
