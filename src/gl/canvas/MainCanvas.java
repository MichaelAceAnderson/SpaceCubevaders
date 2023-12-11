package gl.canvas;

import java.awt.Dimension;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;

import common.DebugMode;
import gl.canvas.rules.Canvas;
import gl.objects.rules.GraphicalObject;

public class MainCanvas extends Canvas {

	/**
	 * @see Canvas
	 */
	public MainCanvas() {
		super();
	}

	/**
	 * Récupérer la taille préférée de la fenêtre de rendu
	 * 
	 * @return Taille préférée de la fenêtre de rendu
	 * 
	 * @see GLCanvas#getPreferredSize()
	 * 
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	/* Implémentation de GLEventListener */
	/**
	 * Afficher le contenu du canvas OpenGL à chaque itération de la boucle de
	 * rendu
	 * (cette action est répétée en boucle de façon automatique et infinie en
	 * fonction de la fréquence d'affichage).
	 * 
	 * @param canvas Le canvas OpenGL
	 * 
	 * @see GLEventListener#display(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void display(GLAutoDrawable canvas) {
		super.display(canvas);

		// Récupérer le contexte OpenGL
		GL2 gl2 = canvas.getGL().getGL2();
		// Vide le buffer de couleur et de profondeur
		gl2.glClear(GL2.GL_COLOR_BUFFER_BIT);
		// Vider le buffer de profondeur
		gl2.glClear(GL2.GL_DEPTH_BUFFER_BIT);
		// gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Vider les
		// deux buffers d'un coup
		// Charger la matrice identité afin de repartir de zéro à chaque fois
		gl2.glLoadIdentity();

		// Afficher tous les objets
		gl2.glPushMatrix();
		{
			if (DebugMode.DRAW_GRID) {
				this.drawGrid();
			}
			if (DebugMode.DRAW_AXIS) {
				this.drawAxis(-2, -1, -5);
			}
			// Pour tous les objets
			for (GraphicalObject object : this.getObjects()) {
				if (object.isVisible()) {
					// Dessiner l'objet dans le contexte OpenGL courant
					object.display();
					// Déplacer l'objet en fonction de sa vitesse
					object.move(object.getSpeedX(), object.getSpeedY(), object.getSpeedZ());
					// Tourner l'objet en fonction de sa rotation
					object.rotate(object.getRotationX(), object.getRotationY(), object.getRotationZ());
				}
			}
		}
		gl2.glPopMatrix();

		this.getParentFrame()
				.setTitle("FPS: " + this.getFps() + "/" + ((FPSAnimator) this.getAnimator()).getFPS()
						+ " Frame: " + this.getFrameCount());

		if (this.getGame() != null) {
			this.getGame().update();
		}
	}

	/**
	 * Libérer les ressources allouées par OpenGL lors de la fermeture de la fenêtre
	 * 
	 * @param canvas Le canvas OpenGL
	 * 
	 * @see GLEventListener#dispose(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void dispose(GLAutoDrawable canvas) {
		// Libérer les ressources allouées par OpenGL
		canvas.getGL().getGL2().glFlush();
		// Terminer le processus
		System.exit(0);
	}

	/**
	 * Initialiser le contexte OpenGL lors de la création de la fenêtre
	 * 
	 * @param drawable Le drawable OpenGL
	 * 
	 * @see GLEventListener#init(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void init(GLAutoDrawable canvas) {
		GL2 gl2 = canvas.getGL().getGL2();
		// Définir la couleur de fond
		gl2.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// Vider le buffer de profondeur
		gl2.glClearDepth(1.0);
		// Mettre en place le test de profondeur
		gl2.glEnable(GL2.GL_DEPTH_TEST);
		// Accepter le fragment si il est plus proche de la caméra que le fragment
		// précédent
		gl2.glDepthFunc(GL2.GL_LEQUAL);
		// Corriger la perspective en fonction de la distance
		gl2.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	}

	/**
	 * Redimensionner le drawable OpenGL lors du redimensionnement de la fenêtre
	 * 
	 * @param drawable Le drawable OpenGL
	 * @param x        Position x de la fenêtre
	 * @param y        Position y de la fenêtre
	 * @param width    Largeur de la fenêtre
	 * 
	 * @see GLEventListener#reshape(com.jogamp.opengl.GLAutoDrawable, int, int, int,
	 *      int)
	 */
	@Override
	public void reshape(GLAutoDrawable drawable,
			int x, int y, int width, int height) {
		// Récupérer le contexte OpenGL
		GL2 gl2 = drawable.getGL().getGL2();
		// Définir la zone d'affichage
		gl2.glViewport(0, 0, width, height);
		// Définir le mode de projection à utiliser
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		// Charger la matrice identité pour effacer les transformations précédentes et
		// repartir de zéro
		gl2.glLoadIdentity();
		// Créer un GLU (OpenGL Utility Library) pour définir la perspective
		GLU glu = new GLU();
		this.setAspect((float) width / height);
		this.setFov(45.0f);
		this.setNearClip(0.1f);
		this.setMaxDepth(80.0f);
		glu.gluPerspective(this.getFov(), this.getAspect(),
				this.getNearClip(), this.getMaxDepth());
		// Définir le mode de projection à utiliser)
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
	}
}
