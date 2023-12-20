package gl.frames.rules;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Frame extends JFrame {
	/**
	 * Créer une fenêtre
	 */
	public Frame() {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		dialog.setSize(300, 200);
		dialog.setLayout(new BorderLayout());
		dialog.setLocationRelativeTo(null);
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
}
