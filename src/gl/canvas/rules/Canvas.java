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
	 * Create a GLCanvas where OpenGL rendering is performed
	 * 
	 * @param parentFrame Parent window where the canvas will be displayed
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
	 * Get the number of display iterations
	 * 
	 * @return Number of display iterations
	 */
	public int getFrameCount() {
	return this.frameCount;
	}

	/**
	 * Store the number of display iterations
	 * 
	 * @param renderedFrame Number of the current iteration
	 */
	public void setFrameCount(int renderedFrame) {
	this.frameCount = renderedFrame;
	}

	/**
	 * Get the rendering window
	 * 
	 * @return Rendering window
	 */
	public Frame getParentFrame() {
	return this.parentFrame;
	}

	/**
	 * Set the rendering window
	 * 
	 * @param frame Rendering window
	 * 
	 * @see Frame
	 */
	public void setParentFrame(Frame frame) {
	this.parentFrame = frame;
	}

	/**
	 * Get the list of objects to display
	 * 
	 * @return List of objects to display
	 */
	public ArrayList<GraphicalObject> getObjects() {
	return this.objects;
	}

	public void setObjects(ArrayList<GraphicalObject> objects) {
	this.objects = objects;
	}

	/**
	 * Set the aspect ratio of the rendering window
	 * 
	 * @param aspect Aspect ratio of the rendering window
	 */
	public void setAspect(float aspect) {
	this.aspect = aspect;
	}

	/**
	 * Get the aspect ratio of the rendering window
	 * 
	 * @return Aspect ratio of the rendering window
	 */
	public float getAspect() {
	return this.aspect;
	}

	/**
	 * Set the nearest rendering distance
	 * 
	 * @param nearClip Nearest rendering distance
	 */
	public void setNearClip(float nearClip) {
	this.nearClip = nearClip;
	}

	/**
	 * Get the nearest rendering distance
	 * 
	 * @return Nearest rendering distance
	 */
	public float getNearClip() {
	return this.nearClip;
	}

	/**
	 * Set the farthest rendering distance
	 * 
	 * @param drawDistance Farthest rendering distance
	 */
	public void setDrawDistance(float drawDistance) {
	this.drawDistance = drawDistance;
	}

	/**
	 * Get the farthest rendering distance
	 * 
	 * @return Farthest rendering distance
	 */
	public float getDrawDistance() {
	return this.drawDistance;
	}

	/**
	 * Set the field of view
	 * 
	 * @param fov Field of view
	 */
	public void setFov(float fov) {
	this.fov = fov;
	}

	/**
	 * Get the field of view
	 * 
	 * @return Field of view
	 */
	public float getFov() {
	return this.fov;
	}

	/**
	 * Draw the reference axes.
	 * Note: For the axes to be visible, this method must be called
	 * after {@link Canvas#drawGrid()}.
	 * Furthermore, the axes are not drawn at the origin point (0, 0, 0) but
	 * inside the scene to be visible.
	 * 
	 * @param posX X position of the origin of the axes
	 * @param posY Y position of the origin of the axes
	 * @param posZ Z position of the origin of the axes
	 * 
	 */
	public void drawAxis(float posX, float posY, float posZ) {
	GL2 gl2 = this.getGL().getGL2();

	gl2.glBegin(GL2.GL_LINES);
	{
		// X-axis
		gl2.glColor3f(RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
		gl2.glVertex3f(-this.getDrawDistance(), posY, posZ);
		gl2.glVertex3f(this.getDrawDistance(), posY, posZ);
		// Y-axis
		gl2.glColor3f(RGBColor.BLUE[0], RGBColor.BLUE[1], RGBColor.BLUE[2]);
		gl2.glVertex3f(posX, -this.getDrawDistance(), posZ);
		gl2.glVertex3f(posX, this.getDrawDistance(), posZ);
		// Z-axis
		gl2.glColor3f(RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]);
		gl2.glVertex3f(posX, posY, -this.getDrawDistance());
		gl2.glVertex3f(posX, posY, this.getDrawDistance());
	}
	gl2.glEnd();

	}

	/**
	 * Draw the reference grid.
	 * Note: This method must be called before any other rendering. The grid has
	 * not been drawn at the origin point (0, 0, 0) but below to be visible.
	 */
	public void drawGrid() {
	float yOffset = -1;
	GL2 gl2 = this.getGL().getGL2();

	gl2.glColor3f(RGBColor.GRAY[0], RGBColor.GRAY[1], RGBColor.GRAY[2]);
	for (float lineIndex = -this.getDrawDistance(); lineIndex <= this.getDrawDistance(); lineIndex++) {
		// Draw lines parallel to the X-axis
		gl2.glBegin(GL2.GL_LINES);
		{
		gl2.glVertex3f(-this.getDrawDistance(), yOffset, lineIndex);
		gl2.glVertex3f(this.getDrawDistance(), yOffset, lineIndex);
		}
		gl2.glEnd();

		// Draw lines parallel to the Z-axis
		gl2.glBegin(GL2.GL_LINES);
		{
		gl2.glVertex3f(lineIndex, yOffset, -this.getDrawDistance());
		gl2.glVertex3f(lineIndex, yOffset, this.getDrawDistance());
		}
		gl2.glEnd();
	}
	}

	/**
	 * Store the current FPS
	 * 
	 * @param fps Number of FPS
	 */
	public void setFps(int fps) {
	this.fps = fps;
	}

	/**
	 * Get the current FPS
	 * 
	 * @return Number of FPS
	 */
	public int getFps() {
	return this.fps;
	}

	/**
	 * Store the time elapsed since the last render
	 * 
	 * @param lastRenderTime Time elapsed since the last render
	 */
	public void setLastRenderTime(long lastRenderTime) {
	this.lastRenderTime = lastRenderTime;
	}

	/**
	 * Get the time elapsed since the last render
	 * 
	 * @return Time elapsed since the last render
	 */
	public long getLastRenderTime() {
	return this.lastRenderTime;
	}

	/**
	 * Set the current game in this canvas
	 * 
	 * @param game Current game in this canvas
	 */
	public void setGame(Game game) {
	this.game = game;
	}

	/**
	 * Get the current game in this canvas
	 * 
	 * @return Current game in this canvas
	 */
	public Game getGame() {
	return this.game;
	}

	/**
	 * Set the default TextRenderer to display text
	 * 
	 * @param textRenderer TextRenderer to display text
	 */
	public void setDefaultTextRenderer(TextRenderer textRenderer) {
	this.defaultTextRenderer = textRenderer;
	}

	/**
	 * Get the default TextRenderer to display text
	 * 
	 * @return TextRenderer to display text
	 */
	public TextRenderer getDefaultTextRenderer() {
	return this.defaultTextRenderer;
	}

	/**
	 * Display text in the window
	 * 
	 * @param text Text to display
	 * @param x    X position of the text in the window
	 * @param y    Y position of the text in the window
	 * @param font Font to use
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
	 * Display text in the window
	 * 
	 * @param text Text to display
	 * @param x    X position of the text in the window
	 * @param y    Y position of the text in the window
	 */
	public void renderText(String text, float x, float y) {
	this.renderText(text, x, y, this.getDefaultTextRenderer().getFont());
	}

	/**
	 * Toggle the animator's pause
	 */
	public void togglePause() {
	if (!this.getAnimator().isPaused()) {
		this.getAnimator().pause();
	} else {
		this.getAnimator().resume();
	}
	}

	/**
	 * Get the preferred size of the rendering window
	 * 
	 * @return Preferred size of the rendering window
	 * 
	 * @see GLCanvas#getPreferredSize()
	 * 
	 */
	@Override
	public Dimension getPreferredSize() {
	return new Dimension(800, 600);
	}

	/* Implementation of GLEventListener */
	/**
	 * Initialize the OpenGL context
	 * 
	 * @param canvas The OpenGL canvas
	 */
	@Override
	public void init(GLAutoDrawable canvas) {
	this.setDefaultTextRenderer(new TextRenderer(new Font(Font.MONOSPACED, Font.PLAIN, 16)));
	}

	/**
	 * Display the content of the OpenGL canvas at each iteration of the rendering loop
	 * (this action is repeated automatically and infinitely according to the display frequency).
	 * 
	 * @param canvas The OpenGL canvas
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
