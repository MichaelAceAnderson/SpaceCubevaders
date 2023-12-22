package gl.canvas;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import common.Debug;
import common.RGBColor;
import gl.canvas.rules.Canvas;
import gl.frames.rules.Frame;
import gl.objects.rules.GraphicalObject;

public class GameCanvas extends Canvas {
	/**
	 * @see Canvas
	 */
	public GameCanvas(Frame parentFrame) {
		super(parentFrame);
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
	 * @see GLEventListener#display(GLAutoDrawable)
	 */
	@Override
	public void display(GLAutoDrawable canvas) {
		super.display(canvas);

		GL2 gl2 = canvas.getGL().getGL2();
		gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		// Charger la matrice identité afin de repartir de zéro à chaque fois
		gl2.glLoadIdentity();

		gl2.glPushMatrix();
		{
			if (Debug.getMode(Debug.Mode.DRAW_AXIS)) {
				this.drawGrid();
			}
			if (Debug.getMode(Debug.Mode.DRAW_GRID)) {
				this.drawAxis(-2, -1, -5);
			}
			for (GraphicalObject object : this.getObjects()) {
				if (object.isVisible()) {
					object.display();
					object.move(object.getSpeedX(), object.getSpeedY(), object.getSpeedZ());
					object.rotate(object.getRotationX(), object.getRotationY(), object.getRotationZ());
				}
			}
		}
		gl2.glPopMatrix();

		String frameCounterString = "FPS: " + this.getFps() + "/" + Canvas.FPS_LIMIT
				+ " \nFrame: " + this.getFrameCount();

		if (this.getGame() != null) {
			this.getGame().update();
			this.renderText(this.getGame().toString(), 0,
					this.getHeight() - this.getDefaultTextRenderer().getFont().getSize());
			this.getParentFrame()
					.setTitle(this.getGame().getName() + " - " + frameCounterString);
			if (Debug.getMode(Debug.Mode.DRAW_INFO)) {
				this.renderText(frameCounterString,
						this.getWidth() - 150, this.getHeight() - this.getDefaultTextRenderer().getFont().getSize());
			}
		} else {
			this.getParentFrame().setTitle(frameCounterString);
			if (Debug.getMode(Debug.Mode.DRAW_INFO)) {
				this.renderText("FPS: " + this.getFps() + "/" + Canvas.FPS_LIMIT,
						this.getWidth() - 150, this.getHeight() - this.getDefaultTextRenderer().getFont().getSize());
			}
		}
	}

	/**
	 * Action à effectuer lors de la fermeture de la fenêtre
	 * 
	 * @param canvas Le canvas OpenGL
	 */
	@Override
	public void dispose(GLAutoDrawable canvas) {
		canvas.getAnimator().stop();
		// Libérer les ressources allouées par OpenGL
		canvas.getGL().getGL2().glFlush();

		System.exit(0);
	}

	/**
	 * Initialiser le contexte OpenGL lors de la création de la fenêtre
	 * 
	 * @param drawable Le drawable OpenGL
	 * 
	 * @see GLEventListener#init(GLAutoDrawable)
	 */
	@Override
	public void init(GLAutoDrawable canvas) {
		super.init(canvas);

		GL2 gl2 = canvas.getGL().getGL2();
		// Définir la couleur de fond
		gl2.glClearColor(RGBColor.BLACK[0], RGBColor.BLACK[1], RGBColor.BLACK[2], 0.0f);
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
	 * @see GLEventListener#reshape(GLAutoDrawable, int, int, int,
	 *      int)
	 */
	@Override
	public void reshape(GLAutoDrawable drawable,
			int x, int y, int width, int height) {
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
		this.setDrawDistance(80.0f);
		glu.gluPerspective(this.getFov(), this.getAspect(),
				this.getNearClip(), this.getDrawDistance());
		// Définir le mode de projection à utiliser
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
	}
}
