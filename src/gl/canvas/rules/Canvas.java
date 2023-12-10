package gl.canvas.rules;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import gl.common.RGBColor;
import gl.objects.rules.GraphicalObject;

public abstract class Canvas extends GLCanvas
		implements GLEventListener {
	// Nombre d'itérations d'affichage
	private int frameCount;
	// Fenêtre de rendu
	private JFrame frame;
	// Objets à afficher
	private ArrayList<GraphicalObject> objects;
	// Limite de FPS
	private static final int FPS_LIMIT = 60;
	// Perspective
	private float aspect;
	private float fov;
	private float nearClip;
	private float maxDepth;

	/**
	 * Créer un GLCanvas où le rendu OpenGL est effectué
	 * 
	 * @see GLCanvas
	 * @see GLEventListener
	 */
	public Canvas() {
		// Ajouter le listener pour les événements OpenGL dans ce canvas
		this.addGLEventListener(this);
		this.setFrameCount(0);
		this.setObjects(new ArrayList<GraphicalObject>());
		this.setFrame(new JFrame());
		this.getFrame().getContentPane().add(this);
		this.getFrame().pack();
		this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getFrame().setVisible(true);
		this.setAnimator(new FPSAnimator(this, Canvas.FPS_LIMIT));
		this.getAnimator().start();
	}

	/**
	 * Récupérer le nombre d'itérations d'affichage
	 * 
	 * @return Nombre d'itérations d'affichage
	 */
	public int getFrameCount() {
		return this.frameCount;
	}

	/**
	 * Stocker le nombre d'itérations d'affichage
	 * 
	 * @param renderedFrame Nombre de l'itération en cours
	 */
	public void setFrameCount(int renderedFrame) {
		this.frameCount = renderedFrame;
	}

	/**
	 * Récupérer la fenêtre de rendu
	 * 
	 * @return Fenêtre de rendu
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Définir la fenêtre de rendu
	 * 
	 * @param frame Fenêtre de rendu
	 * 
	 * @see JFrame
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Récupérer la liste des objets à afficher
	 * 
	 * @return Liste des objets à afficher
	 */
	public ArrayList<GraphicalObject> getObjects() {
		return this.objects;
	}

	public void setObjects(ArrayList<GraphicalObject> objects) {
		this.objects = objects;
	}

	/**
	 * Définir le ratio de la fenêtre de rendu
	 * 
	 * @param aspect Ratio de la fenêtre de rendu
	 */
	public void setAspect(float aspect) {
		this.aspect = aspect;
	}

	/**
	 * Récupérer le ratio de la fenêtre de rendu
	 * 
	 * @return Ratio de la fenêtre de rendu
	 */
	public float getAspect() {
		return this.aspect;
	}

	/**
	 * Définir la distance de rendu la plus proche
	 * 
	 * @param nearClip Distance de rendu la plus proche
	 */
	public void setNearClip(float nearClip) {
		this.nearClip = nearClip;
	}

	/**
	 * Récupérer la distance de rendu la plus proche
	 * 
	 * @return Distance de rendu la plus proche
	 */
	public float getNearClip() {
		return this.nearClip;
	}

	/**
	 * Définir la distance de rendu la plus éloignée
	 * 
	 * @param maxDepth Distance de rendu la plus éloignée
	 */
	public void setMaxDepth(float maxDepth) {
		this.maxDepth = maxDepth;
	}

	/**
	 * Récupérer la distance de rendu la plus éloignée
	 * 
	 * @return Distance de rendu la plus éloignée
	 */
	public float getMaxDepth() {
		return this.maxDepth;
	}

	/**
	 * Définir le champ de vision
	 * 
	 * @param fov Champ de vision
	 */
	public void setFov(float fov) {
		this.fov = fov;
	}

	/**
	 * Récupérer le champ de vision
	 * 
	 * @return Champ de vision
	 */
	public float getFov() {
		return this.fov;
	}

	/**
	 * Dessiner les axes de repère.
	 * Note: Pour que les axes soient visibles, cette méthode doit être appelée
	 * après {@link Canvas#drawGrid()}.
	 * Par ailleurs, les axes ne sont pas dessinés au point d'origine (0, 0, 0) mais
	 * à l'intérieur de la scène pour être visibles.
	 */
	public void drawAxis() {
		// Récupérer le contexte OpenGL
		GL2 gl2 = this.getGL().getGL2();
		// Dessiner les axes de repère
		gl2.glBegin(GL2.GL_LINES);
		{
			// Axe des abscisses
			gl2.glColor3f(RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
			gl2.glVertex3f(-this.getMaxDepth(), -1.0f, -5.0f);
			gl2.glVertex3f(this.getMaxDepth(), -1.0f, -5.0f);
			// Axe des ordonnées
			gl2.glColor3f(RGBColor.BLUE[0], RGBColor.BLUE[1], RGBColor.BLUE[2]);
			gl2.glVertex3f(-2.0f, -this.getMaxDepth(), -5.0f);
			gl2.glVertex3f(-2.0f, this.getMaxDepth(), -5.0f);
			// Axe des profondeurs
			gl2.glColor3f(RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
			gl2.glVertex3f(-2.0f, -1.0f, -this.getMaxDepth());
			gl2.glVertex3f(-2.0f, -1.0f, this.getMaxDepth());
		}
		gl2.glEnd();

	}

	/**
	 * Dessiner la grille de repère.
	 * Note: Cette méthode doit être appelée avant tout autre rendu. La grille n'a
	 * pas été dessinée au point d'origine (0, 0, 0) mais en dessous pour être
	 * visible.
	 */
	public void drawGrid() {
		// Récupérer le contexte OpenGL
		GL2 gl2 = this.getGL().getGL2();
		// Dessiner la grille de repère
		gl2.glColor3f(RGBColor.GRAY[0], RGBColor.GRAY[1], RGBColor.GRAY[2]);
		for (float i = -this.getMaxDepth(); i <= this.getMaxDepth(); i++) {
			// Dessiner les lignes parallèles à l'axe des X des abscisses
			gl2.glBegin(GL2.GL_LINES);
			{
				gl2.glVertex3f(-this.getMaxDepth(), -1, i);
				gl2.glVertex3f(this.getMaxDepth(), -1, i);
			}
			gl2.glEnd();

			// Dessiner les lignes parralèles à l'axe Z de profondeur
			gl2.glBegin(GL2.GL_LINES);
			{
				gl2.glVertex3f(i, -1, -this.getMaxDepth());
				gl2.glVertex3f(i, -1, this.getMaxDepth());
			}
			gl2.glEnd();
		}
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
		// Incrémenter le nombre d'itérations d'affichage
		this.setFrameCount(this.getFrameCount() + 1);
	}
}
