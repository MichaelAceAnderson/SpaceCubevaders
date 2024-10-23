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

	/* Implementation of GLEventListener */
	/**
	 * Display the content of the OpenGL canvas at each iteration of the rendering loop
	 * (this action is repeated automatically and infinitely based on the display frequency).
	 * 
	 * @param canvas The OpenGL canvas
	 * 
	 * @see GLEventListener#display(GLAutoDrawable)
	 */
	@Override
	public void display(GLAutoDrawable canvas) {
		super.display(canvas);

		GL2 gl2 = canvas.getGL().getGL2();
		gl2.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

		// Load the identity matrix to start from scratch each time
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
	 * Action to perform when the window is closed
	 * 
	 * @param canvas The OpenGL canvas
	 */
	@Override
	public void dispose(GLAutoDrawable canvas) {
		canvas.getAnimator().stop();
		// Release resources allocated by OpenGL
		canvas.getGL().getGL2().glFlush();

		System.exit(0);
	}

	/**
	 * Initialize the OpenGL context when the window is created
	 * 
	 * @param drawable The OpenGL drawable
	 * 
	 * @see GLEventListener#init(GLAutoDrawable)
	 */
	@Override
	public void init(GLAutoDrawable canvas) {
		super.init(canvas);

		GL2 gl2 = canvas.getGL().getGL2();
		// Set the background color
		gl2.glClearColor(RGBColor.BLACK[0], RGBColor.BLACK[1], RGBColor.BLACK[2], 0.0f);
		// Clear the depth buffer
		gl2.glClearDepth(1.0);
		// Enable depth testing
		gl2.glEnable(GL2.GL_DEPTH_TEST);
		// Accept the fragment if it is closer to the camera than the previous fragment
		gl2.glDepthFunc(GL2.GL_LEQUAL);
		// Correct the perspective based on distance
		gl2.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
	}

	/**
	 * Resize the OpenGL drawable when the window is resized
	 * 
	 * @param drawable The OpenGL drawable
	 * @param x        x position of the window
	 * @param y        y position of the window
	 * @param width    Width of the window
	 * 
	 * @see GLEventListener#reshape(GLAutoDrawable, int, int, int,
	 *      int)
	 */
	@Override
	public void reshape(GLAutoDrawable drawable,
			int x, int y, int width, int height) {
		GL2 gl2 = drawable.getGL().getGL2();
		// Set the display area
		gl2.glViewport(0, 0, width, height);
		// Set the projection mode to use
		gl2.glMatrixMode(GL2.GL_PROJECTION);
		// Load the identity matrix to clear previous transformations and start from scratch
		gl2.glLoadIdentity();
		// Create a GLU (OpenGL Utility Library) to define the perspective
		GLU glu = new GLU();
		this.setAspect((float) width / height);
		this.setFov(45.0f);
		this.setNearClip(0.1f);
		this.setDrawDistance(80.0f);
		glu.gluPerspective(this.getFov(), this.getAspect(),
				this.getNearClip(), this.getDrawDistance());
		// Set the projection mode to use
		gl2.glMatrixMode(GL2.GL_MODELVIEW);
	}
}
