package gl;

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
import objects.volumes.Cube;

public class MainGL extends GLCanvas
					implements GLEventListener
{
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Nombre d'itérations d'affichage
	private int renderedFrame;
	// Temps écoulé depuis le début de la boucle de rendu
	private long elapsedTime;
	// Nombre de FPS
	private int fps;
	// Fenêtre de rendu
	private static JFrame frame;
	// Objets à afficher
	private static ArrayList<GraphicalObject> objects;

	/* CONSTRUCTEURS */
	/**
	 * Créer un objet MainGL où le rendu OpenGL est effectué
	 * 
	 * @see GLEventListener
	 */	
	public MainGL() {
		this.addGLEventListener(this);
		this.renderedFrame = 0;
	}

	/* MÉTHODES */
	// Point d'entrée du programme
	public static void main(String[] args)
	{
		// Créer un canvas (zone de dessin) OpenGL
		GLCanvas canvas = new MainGL();
		// Définir la taille préférentielle du canvas
		canvas.setPreferredSize(new Dimension(800, 600));
		// Créer une fenêtre
		MainGL.frame = new JFrame();
		// Ajouter le canvas dans la fenêtre
		frame.getContentPane().add(canvas);
		// Empaque les composants dans la fenêtre 
		frame.pack();
		frame.setVisible(true);
		
		// Créer un animator pour appeler la méthode display() de MainGL à chaque itération
		Animator animator = new Animator(canvas);
		// Appelle implicitement init et reshape une fois
		animator.start();

	}


	/* Implémentation de GLEventListener */
	/**
	 * Afficher le contenu du drawable OpenGL à chaque itération de la boucle de rendu
	 * (cette action est répétée en boucle de façon automatique et infinie en fonction de la fréquence d'affichage).
	 * 
	 * NOTE: Cette méthode est lue à l'envers (bottom-up) en raison du fonctionnement des matrices
	 * 
	 * @param drawable Le drawable OpenGL
	 * 
	 * @see GLEventListener#display(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		// Incrémenter le nombre d'itérations d'affichage
		this.renderedFrame += 1;

		// Récupérer le contexte OpenGL
		GL2 gl = drawable.getGL().getGL2();
		// Vide le buffer de couleur et de profondeur
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		// Vider le buffer de profondeur
		gl.glClear(GL2.GL_DEPTH_BUFFER_BIT);
		// gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // Vider les deux buffers d'un coup
		// Charger la matrice identité afin de repartir de zéro à chaque fois
		gl.glLoadIdentity();

		gl.glPushMatrix();
		{
			// Pour tous les objets
			for (GraphicalObject object : MainGL.objects) {
				// Faire une rotation
				object.rotate(0.0f, 1.0f, 0.0f);
				// Dessiner l'objet dans le contexte OpenGL courant
				object.display();
			}
		}
		gl.glPopMatrix();

		// Récupérer le temps actuel
		long currentTime = System.nanoTime();
		// Calculer le temps écoulé depuis le dernier calcul du temps (itération précédentes)
		long deltaTime = currentTime - this.elapsedTime;
		this.elapsedTime = currentTime;
		// Calculer le nombre de FPS
		this.fps = (int) TimeUnit.SECONDS.toNanos(1) / (int) deltaTime;
		// Toutes les 60 itérations, changer le titre de la fenêtre pour afficher le nombre de FPS
		if (this.renderedFrame % 60 == 0)
		MainGL.frame.setTitle("FPS: " + this.fps + ", Frame: " + this.renderedFrame);
	}
	

	/**
	 * Libérer les ressources allouées par OpenGL lors de la fermeture de la fenêtre
	 * 
	 * @param drawable Le drawable OpenGL
	 * 
	 * @see GLEventListener#dispose(com.jogamp.opengl.GLAutoDrawable)
	 */
	@Override
	public void dispose(GLAutoDrawable drawable) {
		// Libérer les ressources allouées par OpenGL
		drawable.getGL().getGL2().glFlush();
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
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// Vider le buffer de profondeur
		gl.glClearDepth(1.0);
		// Mettre en place le test de profondeur
		gl.glEnable(GL2.GL_DEPTH_TEST);
		// Accepter le fragment si il est plus proche de la caméra que le fragment précédent
		gl.glDepthFunc(GL2.GL_LEQUAL);
		// Corriger la perspective en fonction de la distance
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

		// Initialiser la liste des objets à afficher
		MainGL.objects = new ArrayList<GraphicalObject>();
		// Créer un cube
		Cube cube = new Cube(gl);
		cube.setRotation(10.0f, 45.0f, 0.0f);
		// Ajouter le cube à la liste des objets à afficher
		MainGL.objects.add(cube);
	}

	/**
	 * Redimensionner le drawable OpenGL lors du redimensionnement de la fenêtre
	 * 
	 * @param drawable Le drawable OpenGL
	 * @param x Position x de la fenêtre
	 * @param y Position y de la fenêtre
	 * @param width Largeur de la fenêtre
	 * 
	 * @see GLEventListener#reshape(com.jogamp.opengl.GLAutoDrawable, int, int, int, int)
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
		// Charger la matrice identité afin de repartir de zéro à chaque redimensionnement
		gl.glLoadIdentity();
		// Créer un GLU (OpenGL Utility Library) pour définir la perspective
		GLU glu = new GLU();
		glu.gluPerspective(45.0, (float)width/height, 
				0.1, 100.0);
		// Définir le mode de projection à utiliser)
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		// Charger la matrice identité encore une fois pour 
		gl.glLoadIdentity();
	}
}
