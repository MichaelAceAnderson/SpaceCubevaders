package gl.frames;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

import objects.GraphicalObject;

public class MainFrame extends GLCanvas
		implements GLEventListener {
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Nombre d'itérations d'affichage
	private int frameCount;
	// Temps écoulé depuis le début de la boucle de rendu
	private long elapsedTime;
	// Nombre de FPS
	private int fps;
	// Fenêtre de rendu
	private JFrame frame;
	// Objets à afficher
	private ArrayList<GraphicalObject> objects;

	/* CONSTRUCTEURS */
	/**
	 * Créer un objet MainFrame où le rendu OpenGL est effectué
	 * 
	 * @see GLEventListener
	 */
	public MainFrame() {
		// Ajouter un listener à ce canvas
		this.addGLEventListener(this);
		this.setFrameCount(0);
		this.setObjects(new ArrayList<GraphicalObject>());
		this.setFrame(new JFrame());
		this.getFrame().getContentPane().add(this);
		this.getFrame().pack();
		this.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getFrame().setVisible(true);
		Animator animator = new Animator(this);
		// Appelle implicitement init et reshape une fois
		animator.start();
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
	 * Récupérer le temps écoulé depuis le début de la boucle de rendu
	 * 
	 * @return Temps écoulé depuis le début de la boucle de rendu (en nanosecondes)
	 */
	public long getElapsedTime() {
		return this.elapsedTime;
	}

	/**
	 * Stocker le temps écoulé depuis le début de la boucle de rendu (en
	 * nanosecondes)
	 * 
	 * @param elapsedTime
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * Récupérer le nombre de FPS actuel
	 * 
	 * @return Nombre de FPS
	 */
	public int getFps() {
		return this.fps;
	}

	/**
	 * Stocker le nombre de FPS actuel
	 * 
	 * @param fps Nombre de FPS
	 */
	public void setFps(int fps) {
		this.fps = fps;
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
	 * Récupérer la taille préférée du canvas
	 * 
	 * @return Taille préférée du canvas
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
	 * NOTE: Cette méthode est lue à l'envers (bottom-up) en raison du
	 * fonctionnement des matrices
	 * 
	 * @param canvas Le canvas OpenGL
	 * 
	 * @see GLEventListener#display(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void display(GLAutoDrawable canvas) {
		// Incrémenter le nombre d'itérations d'affichage
		this.setFrameCount(this.getFrameCount() + 1);

		// Récupérer le contexte OpenGL
		GL2 gl = canvas.getGL().getGL2();
		// Vide le buffer de couleur et de profondeur
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		// Vider le buffer de profondeur
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);
		// gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Vider les
		// deux buffers d'un coup
		// Charger la matrice identité afin de repartir de zéro à chaque fois
		gl.glLoadIdentity();

		gl.glPushMatrix();
		{
			// Pour tous les objets
			for (GraphicalObject object : this.objects) {
				// Faire une rotation
				object.rotate(0.0f, 0.0f, 0.0f);
				// Dessiner l'objet dans le contexte OpenGL courant
				object.display();
			}
		}
		gl.glPopMatrix();

		// Récupérer le temps actuel
		long currentTime = System.nanoTime();
		// Calculer le temps écoulé depuis le dernier calcul du temps (itération
		// précédentes)
		long deltaTime = currentTime - this.elapsedTime;
		this.setElapsedTime(currentTime);
		// Calculer le nombre de FPS
		this.setFps((int) TimeUnit.SECONDS.toNanos(1) / (int) deltaTime);
		// Toutes les 60 itérations, changer le titre de la fenêtre pour afficher le
		// nombre de FPS
		if (this.getFrameCount() % 60 == 0)
			this.getFrame().setTitle("FPS: " + this.fps + ", Frame: " + this.getFrameCount());
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
		GL2 gl = canvas.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// Vider le buffer de profondeur
		gl.glClearDepth(1.0);
		// Mettre en place le test de profondeur
		gl.glEnable(GL2.GL_DEPTH_TEST);
		// Accepter le fragment si il est plus proche de la caméra que le fragment
		// précédent
		gl.glDepthFunc(GL2.GL_LEQUAL);
		// Corriger la perspective en fonction de la distance
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
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
		GL2 gl = drawable.getGL().getGL2();
		// Définir la zone d'affichage
		gl.glViewport(0, 0, width, height);
		// Définir le mode de projection à utiliser
		gl.glMatrixMode(GL2.GL_PROJECTION);
		// Charger la matrice identité afin de repartir de zéro à chaque
		// redimensionnement
		gl.glLoadIdentity();
		// Créer un GLU (OpenGL Utility Library) pour définir la perspective
		GLU glu = new GLU();
		glu.gluPerspective(45.0, (float) width / height,
				0.1, 100.0);
		// Définir le mode de projection à utiliser)
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		// Charger la matrice identité encore une fois pour
		gl.glLoadIdentity();
	}
}
