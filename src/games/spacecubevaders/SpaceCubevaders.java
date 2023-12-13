package games.spacecubevaders;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import common.Debug;
import common.RGBColor;
import common.Debug.Mode;
import games.rules.Game;
import games.spacecubevaders.entities.Ennemy;
import games.spacecubevaders.entities.Player;
import games.spacecubevaders.entities.rules.Entity;
import games.spacecubevaders.entities.rules.Entity.Direction;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject.Boundary;
import gl.objects.volumes.Cube;
import gl.objects.volumes.Pyramid;

public class SpaceCubevaders extends Game {
	// Canvas sur lequel afficher le jeu
	private Canvas canvas;
	// Limites du jeu
	public final static float MIN_X = -5.0f;
	public final static float MAX_X = 5.0f;
	public static final float MIN_Y = -10.0f;
	public static final float MAX_Y = 6.0f;
	public final static float GAME_DISTANCE = 40.0f;
	public final static float SPACING = 2;
	// Joueur
	private Player player;
	// Ennemis
	private ArrayList<Ennemy> ennemies;

	/**
	 * Créer un jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public SpaceCubevaders(Canvas canvas) {
		super(canvas);

		// Créer un joueur au centre bas de l'écran
		this.setPlayer(new Player(new Pyramid(this.getCanvas(), MIN_X + MAX_X, MIN_Y, -GAME_DISTANCE,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				0.0f, 0.0f, 0.0f,
				0.0f, 5.0f, 0.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2])));

		this.setEnnemies(new ArrayList<Ennemy>());
		for (int row = (int) MIN_Y + 10; row < MAX_Y; row++) {
			for (int col = (int) MIN_X; col < MAX_X; col++) {
				this.getEnnemies()
						.add(new Ennemy(new Cube(this.getCanvas(), col * SPACING, row * SPACING, -GAME_DISTANCE,
								0.0f, 0.0f, 0.0f,
								0.5f, 0.5f, 0.5f,
								0.0f, 0.0f, 0.0f,
								0.0f, 0.0f, 0.0f,
								RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2])));
			}
		}

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_Q:
					case KeyEvent.VK_LEFT:
						if (SpaceCubevaders.this.getPlayer().getRepresentation().getPosX() > SpaceCubevaders.MIN_X
								* SPACING)
							SpaceCubevaders.this.getPlayer().move(Entity.Direction.LEFT);
						break;
					case KeyEvent.VK_D:
					case KeyEvent.VK_RIGHT:
						if (SpaceCubevaders.this.getPlayer().getRepresentation().getPosX() < SpaceCubevaders.MAX_X
								* SPACING)
							SpaceCubevaders.this.getPlayer().move(Entity.Direction.RIGHT);
						break;
					case KeyEvent.VK_SPACE:
						SpaceCubevaders.this.getPlayer().shoot();
						break;
					case KeyEvent.VK_ESCAPE:
						SpaceCubevaders.this.getCanvas().togglePause();
						break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		};
		this.getCanvas().addKeyListener(keyListener);
		// Demander le focus pour pouvoir utiliser les touches
		this.getCanvas().requestFocus();
	}

	/**
	 * Récupérer le canvas sur lequel afficher le jeu
	 * 
	 * @return Canvas sur lequel afficher le jeu
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Définir le canvas sur lequel afficher le jeu
	 * 
	 * @param canvas Canvas sur lequel afficher le jeu
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Récupérer le joueur
	 * 
	 * @return Joueur
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Définir le joueur
	 * 
	 * @param player Joueur
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Récupérer les ennemis
	 * 
	 * @return Tableau des ennemis
	 */
	public ArrayList<Ennemy> getEnnemies() {
		return ennemies;
	}

	/**
	 * Définir les ennemis
	 * 
	 * @param ennemies Tableau des ennemis
	 */
	public void setEnnemies(ArrayList<Ennemy> ennemies) {
		this.ennemies = ennemies;
	}

	/**
	 * Récupérer le nom du jeu
	 * 
	 * @return Nom du jeu
	 */
	@Override
	public String getName() {
		return "Space Cubevaders";
	}

	/**
	 * Récupérer l'icône du jeu
	 * 
	 * @return Icône du jeu
	 */
	@Override
	public Image getIcon() {
		return new ImageIcon(System.getProperty("user.dir") + "/src/games/spacecubevaders/icon.png").getImage();
	}

	/*
	 * Mettre à jour le jeu
	 */
	@Override
	public void update() {
		for (Ennemy ennemy : this.getEnnemies()) {
			if (ennemy.getRepresentation().getPosY() <= this.getPlayer().getRepresentation().getPosY()) {
				this.getCanvas().getAnimator().stop();
				JDialog dialog = new JDialog();
				dialog.setTitle("Perdu !");
				dialog.setSize(300, 200);
				dialog.setLayout(new BorderLayout());
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				// Fermer la fenêtre du jeu quand le dialog de victoire est fermé
				dialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent windowEvent) {
						SpaceCubevaders.this.getCanvas().getParentFrame().dispose();
					}
				});
				dialog.setVisible(true);
				dialog.add(new JLabel("Vous avez perdu la partie !"), BorderLayout.CENTER);
				JButton button = new JButton("Quitter le jeu");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						SpaceCubevaders.this.getCanvas().getParentFrame().dispose();
					}
				});
				dialog.add(button, BorderLayout.SOUTH);
			}
			if (ennemy.getRepresentation().getPosX() > MAX_X * SPACING + 5) {
				for (Ennemy ennemyToMove : this.getEnnemies()) {
					ennemyToMove.move(Direction.DOWN);
					ennemyToMove.setDirection((Entity.Direction.LEFT));
					ennemyToMove.setSpeed(ennemy.getSpeed() + 0.005f);
				}
			}
			if (ennemy.getRepresentation().getPosX() < MIN_X * SPACING - 5) {
				for (Ennemy ennemyToMove : this.getEnnemies()) {
					ennemyToMove.move(Direction.DOWN);
					ennemyToMove.setDirection((Entity.Direction.RIGHT));
					ennemyToMove.setSpeed(ennemy.getSpeed() + 0.005f);
				}
			}
			ennemy.move(ennemy.getDirection());

			if (this.getPlayer().getMissile() != null) {
				if (this.getPlayer().getMissile().isColliding(ennemy.getRepresentation())) {
					if (Debug.getMode(Mode.VERBOSE)) {
						System.out.println(
								"Collision entre le missile et l'ennemi " + this.getEnnemies().indexOf(ennemy) + " !");
					}

					this.getCanvas().getObjects().remove(ennemy.getRepresentation());
					this.getEnnemies().remove(ennemy);

					this.getCanvas().getObjects().remove(this.getPlayer().getMissile());
					this.getPlayer().setMissile(null);

					this.getPlayer().setScore(this.getPlayer().getScore() + 1);

					if (this.getEnnemies().isEmpty()) {
						this.getCanvas().getAnimator().stop();
						JDialog dialog = new JDialog();
						dialog.setTitle("Victoire !");
						dialog.setSize(300, 200);
						dialog.setLayout(new BorderLayout());
						dialog.setLocationRelativeTo(null);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						// Fermer la fenêtre du jeu quand le dialog de victoire est fermé
						dialog.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent windowEvent) {
								SpaceCubevaders.this.getCanvas().getParentFrame().dispose();
							}
						});
						dialog.setVisible(true);
						dialog.add(new JLabel("Tous les ennemis ont été détruits !", JLabel.CENTER),
								BorderLayout.CENTER);
						JButton button = new JButton("Quitter le jeu");
						button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								SpaceCubevaders.this.getCanvas().getParentFrame().dispose();
							}
						});
						dialog.add(button, BorderLayout.SOUTH);
					}
					break;
				}
			}

			// Si le missile n'a pas été supprimé par une collision
			if (this.getPlayer().getMissile() != null) {
				// Supprimer le missile s'il dépasse les limites du jeu
				if (this.getPlayer().getMissile().getBoundingBox()[Boundary.MIN_Y.ordinal()] > MAX_Y * SPACING) {
					this.getCanvas().getObjects().remove(this.getPlayer().getMissile());
					this.getPlayer().setMissile(null);
				}
			}
		}
	}

	/**
	 * Récupérer les informations du jeu
	 * 
	 * @return Informations du jeu
	 */
	@Override
	public String toString() {
		return super.toString()
				+ "\n\tJoueur : " + this.getPlayer().getScore() + " points"
				+ "\n\tEnnemis : " + this.getEnnemies();
	}

}
