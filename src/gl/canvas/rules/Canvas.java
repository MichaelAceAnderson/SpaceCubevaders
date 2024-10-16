package gl.canvas.rules;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import java.awt.Font;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextRenderer;

import common.RGBColor;
import games.rules.Game;
import gl.frames.rules.Frame;
import gl.objects.rules.GraphicalObject;

public abstract class Canvas extends GLCanvas
		implements GLEventListener {
	private int frameCount;
	private Frame parentFrame;
	private ArrayList<GraphicalObject> objects;
	private int fps;
	private long lastRenderTime;
	public static final int FPS_LIMIT = 60;
	private float aspect;
	private float fov;
	private float nearClip;
	private float drawDistance;
	private Game game;
	private TextRenderer defaultTextRenderer;

	/**
	 * Créer un GLCanvas où le rendu OpenGL est effectué
	 * 
	 * @param parentFrame Fenêtre parente où sera affiché le canvas
	 * 
	 * @see GLCanvas
	 * @see GLEventListener
	 */
	public Canvas(Frame parentFrame) {
		this.setParentFrame(parentFrame);
		this.getParentFrame().getContentPane().add(this);
		this.getParentFrame().pack();
		this.getParentFrame().setVisible(true);

		this.setFrameCount(0);

		this.setObjects(new ArrayList<GraphicalObject>());

		this.setAnimator(new FPSAnimator(this, Canvas.FPS_LIMIT));
		this.addGLEventListener(this);
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
	public Frame getParentFrame() {
		return this.parentFrame;
	}

	/**
	 * Définir la fenêtre de rendu
	 * 
	 * @param frame Fenêtre de rendu
	 * 
	 * @see Frame
	 */
	public void setParentFrame(Frame frame) {
		this.parentFrame = frame;
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
	 * @param drawDistance Distance de rendu la plus éloignée
	 */
	public void setDrawDistance(float drawDistance) {
		this.drawDistance = drawDistance;
	}

	/**
	 * Récupérer la distance de rendu la plus éloignée
	 * 
	 * @return Distance de rendu la plus éloignée
	 */
	public float getDrawDistance() {
		return this.drawDistance;
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
	 * 
	 * @param posX Position x de l'origine des axes
	 * @param posY Position y de l'origine des axes
	 * @param posZ Position z de l'origine des axes
	 * 
	 */
	public void drawAxis(float posX, float posY, float posZ) {
		GL2 gl2 = this.getGL().getGL2();

		gl2.glBegin(GL2.GL_LINES);
		{
			// Axe des abscisses
			gl2.glColor3f(RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
			gl2.glVertex3f(-this.getDrawDistance(), posY, posZ);
			gl2.glVertex3f(this.getDrawDistance(), posY, posZ);
			// Axe des ordonnées
			gl2.glColor3f(RGBColor.BLUE[0], RGBColor.BLUE[1], RGBColor.BLUE[2]);
			gl2.glVertex3f(posX, -this.getDrawDistance(), posZ);
			gl2.glVertex3f(posX, this.getDrawDistance(), posZ);
			// Axe des profondeurs
			gl2.glColor3f(RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
			gl2.glVertex3f(posX, posY, -this.getDrawDistance());
			gl2.glVertex3f(posX, posY, this.getDrawDistance());
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
		float yOffset = -1;
		GL2 gl2 = this.getGL().getGL2();

		gl2.glColor3f(RGBColor.GRAY[0], RGBColor.GRAY[1], RGBColor.GRAY[2]);
		for (float lineIndex = -this.getDrawDistance(); lineIndex <= this.getDrawDistance(); lineIndex++) {
			// Dessiner les lignes parallèles à l'axe des X des abscisses
			gl2.glBegin(GL2.GL_LINES);
			{
				gl2.glVertex3f(-this.getDrawDistance(), yOffset, lineIndex);
				gl2.glVertex3f(this.getDrawDistance(), yOffset, lineIndex);
			}
			gl2.glEnd();

			// Dessiner les lignes parralèles à l'axe Z de profondeur
			gl2.glBegin(GL2.GL_LINES);
			{
				gl2.glVertex3f(lineIndex, yOffset, -this.getDrawDistance());
				gl2.glVertex3f(lineIndex, yOffset, this.getDrawDistance());
			}
			gl2.glEnd();
		}
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
	 * Récupérer le nombre de FPS actuel
	 * 
	 * @return Nombre de FPS
	 */
	public int getFps() {
		return this.fps;
	}

	/**
	 * Stocker le temps écoulé depuis le dernier rendu
	 * 
	 * @param lastRenderTime Temps écoulé depuis le dernier rendu
	 */
	public void setLastRenderTime(long lastRenderTime) {
		this.lastRenderTime = lastRenderTime;
	}

	/**
	 * Récupérer le temps écoulé depuis le dernier rendu
	 * 
	 * @return Temps écoulé depuis le dernier rendu
	 */
	public long getLastRenderTime() {
		return this.lastRenderTime;
	}

	/**
	 * Définir le jeu en cours dans ce canvas
	 * 
	 * @param game Jeu en cours dans ce canvas
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * Récupérer le jeu en cours dans ce canvas
	 * 
	 * @return Jeu en cours dans ce canvas
	 */
	public Game getGame() {
		return this.game;
	}

	/**
	 * Définir le TextRenderer par défaut pour afficher du texte
	 * 
	 * @param textRenderer TextRenderer pour afficher du texte
	 */
	public void setDefaultTextRenderer(TextRenderer textRenderer) {
		this.defaultTextRenderer = textRenderer;
	}

	/**
	 * Récupérer le TextRenderer par défaut pour afficher du texte
	 * 
	 * @return TextRenderer pour afficher du texte
	 */
	public TextRenderer getDefaultTextRenderer() {
		return this.defaultTextRenderer;
	}

	/**
	 * Afficher du texte dans la fenêtre
	 * 
	 * @param text Texte à afficher
	 * @param x    Position x du texte dans la fenêtre
	 * @param y    Position y du texte dans la fenêtre
	 * @param font La police d'écriture à utiliser
	 */
	public void renderText(String text, float x, float y, Font font) {
		TextRenderer textRenderer = new TextRenderer(font);
		text = text.replaceAll("\t", "  ");
		String[] lines = text.split("\n");
		textRenderer.beginRendering(this.getWidth(), this.getHeight());
		textRenderer.setColor(RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2], 1.0f);
		for (int i = 0; i < lines.length; i++) {
			textRenderer.draw(lines[i], (int) x, (int) y - i * textRenderer.getFont().getSize());
		}
		textRenderer.endRendering();
	}

	/**
	 * Afficher du texte dans la fenêtre
	 * 
	 * @param text Texte à afficher
	 * @param x    Position x du texte dans la fenêtre
	 * @param y    Position y du texte dans la fenêtre
	 */
	public void renderText(String text, float x, float y) {
		this.renderText(text, x, y, this.getDefaultTextRenderer().getFont());
	}

	/**
	 * Basculer la pause de l'animateur
	 */
	public void togglePause() {
		if (!this.getAnimator().isPaused()) {
			this.getAnimator().pause();
		} else {
			this.getAnimator().resume();
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
	 * Initialiser le contexte OpenGL
	 * 
	 * @param canvas Le canvas OpenGL
	 */
	@Override
	public void init(GLAutoDrawable canvas) {
		this.setDefaultTextRenderer(new TextRenderer(new Font(Font.MONOSPACED, Font.PLAIN, 16)));
	}

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
		this.setFrameCount(this.getFrameCount() + 1);

		long currentTime = System.nanoTime();
		long deltaTime = currentTime - this.getLastRenderTime();
		this.setFps((int) TimeUnit.SECONDS.toNanos(1) / (int) deltaTime);
		this.setLastRenderTime(currentTime);
	}
}
