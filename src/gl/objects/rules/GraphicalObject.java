package gl.objects.rules;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import common.Debug;
import common.RGBColor;
import gl.canvas.rules.Canvas;

public abstract class GraphicalObject {
	protected Canvas canvas;
	protected float posX, posY, posZ;
	protected float angleX, angleY, angleZ;
	protected float scaleX, scaleY, scaleZ;
	protected float red, green, blue;
	protected float speedX, speedY, speedZ;
	protected float rotationX, rotationY, rotationZ;
	// Collision box boundaries
	public static enum Boundary {
		MIN_X, MAX_X, MIN_Y, MAX_Y, MIN_Z, MAX_Z
	}

	/**
	 * Create a graphical object
	 * 
	 * @param canvas    The OpenGL canvas
	 * @param posX      The X position
	 * @param posY      The Y position
	 * @param posZ      The Z position
	 * @param angleX    The X angle
	 * @param angleY    The Y angle
	 * @param angleZ    The Z angle
	 * @param scaleX    The size on the X axis
	 * @param scaleY    The size on the Y axis
	 * @param scaleZ    The size on the Z axis
	 * @param speedX    The speed on the X axis
	 * @param speedY    The speed on the Y axis
	 * @param speedZ    The speed on the Z axis
	 * @param rotationX The rotation on the X axis
	 * @param rotationY The rotation on the Y axis
	 * @param rotationZ The rotation on the Z axis
	 * @param r         The red color
	 * @param g         The green color
	 * @param b         The blue color
	 * 
	 */
	public GraphicalObject(Canvas canvas, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
			float r, float g, float b) {
		this.setCanvas(canvas);
		this.setPosX(posX);
		this.setPosY(posY);
		this.setPosZ(posZ);
		this.setAngleX(angleX);
		this.setAngleY(angleY);
		this.setAngleZ(angleZ);
		this.setScaleX(scaleX);
		this.setScaleY(scaleY);
		this.setScaleZ(scaleZ);
		this.setSpeedX(speedX);
		this.setSpeedY(speedY);
		this.setSpeedZ(speedZ);
		this.setRotationX(rotationX);
		this.setRotationY(rotationY);
		this.setRotationZ(rotationZ);
		this.setRed(r);
		this.setGreen(g);
		this.setBlue(b);
	}

	/**
	 * Get the X position of this graphical object
	 * 
	 * @return The X position
	 */
	public float getPosX() {
		return this.posX;
	}

	/**
	 * Set the X position of this graphical object
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * Get the Y position of this graphical object
	 * 
	 * @return The Y position
	 */
	public float getPosY() {
		return this.posY;
	}

	/**
	 * Set the Y position of this graphical object
	 * 
	 * @param posY The Y position
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}

	/**
	 * Get the Z position of this graphical object
	 * 
	 * @return The Z position
	 */
	public float getPosZ() {
		return this.posZ;
	}

	/**
	 * Set the Z position of this graphical object
	 * 
	 * @param posZ The Z position
	 */
	public void setPosZ(float posZ) {
		this.posZ = posZ;
	}

	/**
	 * Get the X angle of this graphical object
	 * 
	 * @return The X angle
	 */
	public float getAngleX() {
		return this.angleX;
	}

	/**
	 * Set the X angle of this graphical object
	 * 
	 * @param angleX The X angle
	 */
	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	/**
	 * Get the Y angle of this graphical object
	 * 
	 * @return The Y angle
	 */
	public float getAngleY() {
		return this.angleY;
	}

	/**
	 * Set the Y angle of this graphical object
	 */
	public void setAngleY(float angleY) {
		this.angleY = angleY;
	}

	/**
	 * Get the Z angle of this graphical object
	 */
	public float getAngleZ() {
		return this.angleZ;
	}

	/**
	 * Set the Z angle of this graphical object
	 * 
	 * @param angleZ The Z angle
	 */
	public void setAngleZ(float angleZ) {
		this.angleZ = angleZ;
	}

	/**
	 * Set the size of this graphical object on the X axis
	 * 
	 * @param scaleX The size of the object on the X axis
	 */
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * Get the size of this graphical object on the X axis
	 * 
	 * @return The size of the object on the X axis
	 */
	public float getScaleX() {
		return this.scaleX;
	}

	/**
	 * Set the size of this graphical object on the Y axis
	 * 
	 * @param scaleY The size of the object on the Y axis
	 */
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * Get the size of this graphical object on the Y axis
	 * 
	 * @return The size of the object on the Y axis
	 */
	public float getScaleY() {
		return this.scaleY;
	}

	/**
	 * Get the speed of this graphical object on the X axis
	 * 
	 * @return The speed of the object on the X axis
	 */
	public float getSpeedX() {
		return this.speedX;
	}

	/**
	 * Set the speed of this graphical object on the X axis
	 * 
	 * @param speedX The speed of the object on the X axis
	 */
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	/**
	 * Get the speed of this graphical object on the Y axis
	 * 
	 * @return The speed of the object on the Y axis
	 */
	public float getSpeedY() {
		return this.speedY;
	}

	/**
	 * Set the speed of this graphical object on the Y axis
	 * 
	 * @param speedY The speed of the object on the Y axis
	 */
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	/**
	 * Get the speed of this graphical object on the Z axis
	 * 
	 * @return The speed of the object on the Z axis
	 */
	public float getSpeedZ() {
		return this.speedZ;
	}

	/**
	 * Set the speed of this graphical object on the Z axis
	 * 
	 * @param speedZ The speed of the object on the Z axis
	 */
	public void setSpeedZ(float speedZ) {
		this.speedZ = speedZ;
	}
	/**
	 * Get the rotation of this graphical object on the X axis
	 * 
	 * @return The rotation of the object on the X axis
	 */
	public float getRotationX() {
		return this.rotationX;
	}

	/**
	 * Set the rotation of this graphical object on the X axis
	 * 
	 * @param rotationX The rotation of the object on the X axis
	 */
	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
	}

	/**
	 * Get the rotation of this graphical object on the Y axis
	 * 
	 * @return The rotation of the object on the Y axis
	 */
	public float getRotationY() {
		return this.rotationY;
	}

	/**
	 * Set the rotation of this graphical object on the Y axis
	 * 
	 * @param rotationY The rotation of the object on the Y axis
	 */
	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
	}

	/**
	 * Get the rotation of this graphical object on the Z axis
	 * 
	 * @return The rotation of the object on the Z axis
	 */
	public float getRotationZ() {
		return this.rotationZ;
	}

	/**
	 * Set the rotation of this graphical object on the Z axis
	 * 
	 * @param rotationZ The rotation of the object on the Z axis
	 */
	public void setRotationZ(float rotationZ) {
		this.rotationZ = rotationZ;
	}

	/**
	 * Get the size of this graphical object on the Z axis
	 * 
	 * @return The size of the object on the Z axis
	 */
	public float getScaleZ() {
		return this.scaleZ;
	}

	/**
	 * Set the size of this graphical object on the Z axis
	 * 
	 * @param scaleZ The size of the object on the Z axis
	 */
	public void setScaleZ(float scaleZ) {
		this.scaleZ = scaleZ;
	}

	/**
	 * Set the red color of this graphical object
	 * 
	 * @param red The red color value (between 0.0 and 1.0)
	 */
	public void setRed(float red) {
		this.red = red;
	}

	/**
	 * Get the red color of this graphical object
	 * 
	 * @return The red color value
	 */
	public float getRed() {
		return this.red;
	}

	/**
	 * Set the green color of this graphical object
	 * 
	 * @param green The green color value (between 0.0 and 1.0)
	 */
	public void setGreen(float green) {
		this.green = green;
	}

	/**
	 * Get the green color of this graphical object
	 * 
	 * @return The green color value
	 */
	public float getGreen() {
		return this.green;
	}

	/**
	 * Set the blue color of this graphical object
	 * 
	 * @param blue The blue color value (between 0.0 and 1.0)
	 */
	public void setBlue(float blue) {
		this.blue = blue;
	}

	/**
	 * Get the blue color of this graphical object
	 * 
	 * @return The blue color value
	 */
	public float getBlue() {
		return this.blue;
	}

	/**
	 * Get the OpenGL context
	 * 
	 * @return The OpenGL context
	 * 
	 * @see GL2
	 */
	public GL2 getGl2() {
		return this.getCanvas().getGL().getGL2();
	}

	/**
	 * Set the OpenGL context
	 * 
	 * @param canvas The OpenGL canvas
	 * 
	 * @see Canvas
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Get the OpenGL canvas
	 * 
	 * @return The OpenGL canvas
	 * 
	 * @see Canvas
	 */
	public Canvas getCanvas() {
		return this.canvas;
	}

	/**
	 * Get the boundaries of this graphical object
	 * 
	 * @return The boundary coordinates
	 */
	public float[] getBoundaries() {
		float[] centerPosition = { this.getPosX(), this.getPosY(), this.getPosZ() };
		float[] size = { this.getScaleX(), this.getScaleY(), this.getScaleZ() };

		float[] boundaryBox = new float[Boundary.values().length];

		boundaryBox[Boundary.MAX_Y.ordinal()] = centerPosition[1] + size[1] / 2;
		boundaryBox[Boundary.MIN_Y.ordinal()] = centerPosition[1] - size[1] / 2;
		boundaryBox[Boundary.MAX_Z.ordinal()] = centerPosition[2] + size[2] / 2;
		boundaryBox[Boundary.MIN_Z.ordinal()] = centerPosition[2] - size[2] / 2;
		boundaryBox[Boundary.MIN_X.ordinal()] = centerPosition[0] - size[0] / 2;
		boundaryBox[Boundary.MAX_X.ordinal()] = centerPosition[0] + size[0] / 2;

		return boundaryBox;
	}
	/**
	 * Get the coordinate of a boundary of this graphical object
	 * 
	 * @param boundary The boundary to get
	 */
	public float getBoundary(Boundary boundary) {
		return this.getBoundaries()[boundary.ordinal()];
	}

	/**
	 * Check if this graphical object is colliding with another graphical object
	 * 
	 * @param object The graphical object to check collision with
	 * 
	 * @return true if the objects are colliding, false otherwise
	 */
	public boolean isColliding(GraphicalObject object) {
		float[] thisBoundingBox = this.getBoundaries();
		float[] objectBoundingBox = object.getBoundaries();

		// If the top of this object is higher than the bottom of the other object
		boolean isColliding = (thisBoundingBox[Boundary.MAX_Y.ordinal()] >= objectBoundingBox[Boundary.MIN_Y.ordinal()]
				// If the bottom of this object is lower than the top of the other object
				&& thisBoundingBox[Boundary.MIN_Y.ordinal()] <= objectBoundingBox[Boundary.MAX_Y.ordinal()]
				// If the back of this object is further than the front of the other object
				&& thisBoundingBox[Boundary.MAX_Z.ordinal()] >= objectBoundingBox[Boundary.MIN_Z.ordinal()]
				// If the front of this object is closer than the back of the other object
				&& thisBoundingBox[Boundary.MIN_Z.ordinal()] <= objectBoundingBox[Boundary.MAX_Z.ordinal()]
				// If the left of this object is more to the left than the right of the other object
				&& thisBoundingBox[Boundary.MIN_X.ordinal()] <= objectBoundingBox[Boundary.MAX_X.ordinal()]
				// If the right of this object is more to the right than the left of the other object
				&& thisBoundingBox[Boundary.MAX_X.ordinal()] >= objectBoundingBox[Boundary.MIN_X.ordinal()]);

		return isColliding;
	}

	/**
	 * Check if this graphical object is visible by the canvas camera from the
	 * position and size of its collision box. NOTE: The camera must be positioned
	 * at (0, 0, 0) and oriented towards the negative Z axis.
	 * 
	 * @return true if the object is visible, false otherwise
	 */
	public boolean isVisible() {
		// If the object is behind the camera's field of view
		if (this.getBoundary(Boundary.MAX_Z) > 0) {
			return false;
		}

		// If the object is further than the maximum depth
		if (this.getBoundary(Boundary.MIN_Z) < -this.getCanvas().getDrawDistance()) {
			return false;
		}

		// If the object is more to the left than the left limit
		if (this.getBoundary(Boundary.MIN_X) < -this.getCanvas().getDrawDistance()) {
			return false;
		}

		// If the object is more to the right than the right limit
		if (this.getBoundary(Boundary.MAX_X) > this.getCanvas().getDrawDistance()) {
			return false;
		}

		// If the object is higher than the upper limit
		if (this.getBoundary(Boundary.MAX_Y) > this.getCanvas().getDrawDistance()) {
			return false;
		}

		// If the object is lower than the lower limit
		if (this.getBoundary(Boundary.MAX_X) < -this.getCanvas().getDrawDistance()) {
			return false;
		}

		return true;
	}

	/**
	 * Draw this graphical object.
	 * This method must be implemented in child classes to determine how the object
	 * should be drawn and displayed in the display() method.
	 * 
	 * @see GraphicalObject#display()
	 */
	public abstract void draw();

	/**
	 * Draw the collisions of this graphical object
	 * 
	 * @return true if the collisions were drawn, false otherwise
	 */
	public boolean drawCollisions() {
		if (!this.getCanvas().getObjects().contains(this)) {
			return false;
		}

		// Draw the lines of the collision box
		this.getGl2().glBegin(GL2.GL_LINES);
		{
			this.getGl2().glColor3f(RGBColor.YELLOW[0], RGBColor.YELLOW[1], RGBColor.YELLOW[2]);
			this.getGl2().glVertex3f(this.getBoundary(Boundary.MIN_X),
					this.getPosY(),
					this.getPosZ());
			this.getGl2().glVertex3f(
					this.getBoundary(Boundary.MAX_X),
					this.getPosY(),
					this.getPosZ());
			this.getGl2().glVertex3f(this.getPosX(),
					this.getBoundary(Boundary.MIN_Y),
					this.getPosZ());
			this.getGl2().glVertex3f(this.getPosX(),
					this.getBoundary(Boundary.MAX_Y),
					this.getPosZ());
			this.getGl2().glVertex3f(this.getPosX(),
					this.getPosY(),
					this.getBoundary(Boundary.MIN_Z));
			this.getGl2().glVertex3f(this.getPosX(),
					this.getPosY(),
					this.getBoundary(Boundary.MAX_Z));
		}
		this.getGl2().glEnd();
		this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);

		return true;
	}

	/**
	 * Attach information to this graphical object
	 * 
	 * @return true if the information was drawn, false otherwise
	 */
	public boolean drawInfos() {
		if (!this.getCanvas().getObjects().contains(this)) {
			return false;
		}
		this.getGl2().glColor3f(RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]);
		this.getGl2().glRasterPos3f(this.getPosX(), this.getPosY(), this.getPosZ());
		GLUT glut = new GLUT();
		String[] lines = this.get3DInfo().split("\n");
		for (int line = 0; line < lines.length; line++) {
			this.getGl2().glRasterPos3f(this.getPosX(), this.getPosY() - line, this.getPosZ());
			glut.glutBitmapString(GLUT.BITMAP_HELVETICA_10, lines[line]);
		}
		return true;
	}

	/**
	 * Display this graphical object in its current context.
	 * Note: Transformations are applied in the reverse order of their appearance
	 * in the code due to the way matrices work
	 * 
	 * @see GL2
	 */
	public void display() {
		// Save the current rendering state
		this.getGl2().glPushMatrix();
		{
			// Apply transformations to the object to be drawn
			this.getGl2().glTranslatef(this.getPosX(), this.getPosY(), this.getPosZ());
			this.getGl2().glRotatef(this.getAngleX(), 1.0f, 0.0f, 0.0f);
			this.getGl2().glRotatef(this.getAngleZ(), 0.0f, 0.0f, 1.0f);
			this.getGl2().glRotatef(this.getAngleY(), 0.0f, 1.0f, 0.0f);
			this.getGl2().glScalef(this.getScaleX(), this.getScaleY(), this.getScaleZ());

			// Draw the object
			if (Debug.getMode(Debug.Mode.LINE_MODE)) {
				this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			}
			float r = this.getRed();
			float g = this.getGreen();
			float b = this.getBlue();
			// Line mode prevails over contour mode
			if (Debug.getMode(Debug.Mode.CONTOURING) && !Debug.getMode(Debug.Mode.LINE_MODE)) {
				this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
				this.setRed(RGBColor.BLACK[0]);
				this.setGreen(RGBColor.BLACK[1]);
				this.setBlue(RGBColor.BLACK[2]);
				this.draw();
				this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
			}
			this.setRed(r);
			this.setGreen(g);
			this.setBlue(b);
			this.draw();
			if (Debug.getMode(Debug.Mode.DRAW_COLLISIONS)) {
				this.drawCollisions();
			}
			if (Debug.getMode(Debug.Mode.DRAW_INFO)) {
				this.drawInfos();
			}
			if (Debug.getMode(Debug.Mode.LINE_MODE)) {
				this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
			}
		}
		// Restore the saved transformations
		this.getGl2().glPopMatrix();
	}

	/**
	 * Move the graphical object
	 * 
	 * @param x The X position
	 * @param y The Y position
	 * @param z The Z position
	 * 
	 */
	public void move(float x, float y, float z) {
		this.setPosX(this.getPosX() + x);
		this.setPosY(this.getPosY() + y);
		this.setPosZ(this.getPosZ() + z);
	}
	/**
	 * Rotate this graphical object (relative to its current position)
	 * 
	 * @param angleX The angle in X
	 * @param angleY The angle in Y
	 * @param angleZ The angle in Z
	 * 
	 * @see GL2
	 */
	public void rotate(float angleX, float angleY, float angleZ) {
		this.setAngleX(this.getAngleX() + angleX);
		this.setAngleY(this.getAngleY() + angleY);
		this.setAngleZ(this.getAngleZ() + angleZ);
	}

	/**
	 * Set the rotation of this graphical object
	 * 
	 * @param angleX The angle in X
	 * @param angleY The angle in Y
	 * @param angleZ The angle in Z
	 * 
	 * @see GraphicalObject#rotate(float, float, float)
	 */
	public void setRotation(float angleX, float angleY, float angleZ) {
		this.setAngleX(angleX);
		this.setAngleY(angleY);
		this.setAngleZ(angleZ);
	}

	/**
	 * Get the information of the 3D object
	 * 
	 * @return The information of the 3D object
	 */
	public String get3DInfo() {
		return "pX: " + this.getPosX() + ", pY: " + this.getPosY() + ", pZ: " + this.getPosZ() + "\n"
				+ "aX: " + this.getAngleX() + ", aY: " + this.getAngleY() + ", aZ: " + this.getAngleZ() + "\n"
				+ "sX: " + this.getScaleX() + ", sY: " + this.getScaleY() + ", sZ: " + this.getScaleZ() + "\n"
				+ "R: " + this.getRed() + ", G: " + this.getGreen() + ", B: " + this.getBlue() + "\n"
				+ "vX: " + this.getSpeedX() + ", vY: " + this.getSpeedY() + ", vZ: " + this.getSpeedZ() + "\n"
				+ "rX: " + this.getRotationX() + ", rY: " + this.getRotationY() + ", rZ: " + this.getRotationZ() + "\n";
	}

	/**
	 * Display the characteristics of this graphical object in the console
	 */
	@Override
	public String toString() {
		return "GraphicalObject: " + "\n"
				+ "\tType: " + this.getClass().getSimpleName() + "\n"
				+ "\tPosition: X: " + this.getPosX() + ", Y: " + this.getPosY() + ", Z: " + this.getPosZ() + "\n"
				+ "\tAngle: X: " + this.getAngleX() + ", Y: " + this.getAngleY() + ", Z: " + this.getAngleZ() + "\n"
				+ "\tScale: X: " + this.getScaleX() + ", Y: " + this.getScaleY() + ", Z: " + this.getScaleZ() + "\n"
				+ "\tColor: R: " + this.getRed() + ", G: " + this.getGreen() + ", B: " + this.getBlue() + "\n"
				+ "\tSpeed: X: " + this.getSpeedX() + ", Y: " + this.getSpeedY() + ", Z: " + this.getSpeedZ() + "\n"
				+ "\tRotation: X: " + this.getRotationX() + ", Y: " + this.getRotationY() + ", Z: "
				+ this.getRotationZ() + "\n"
				+ "\tBoundingBox: " + "\n"
				+ "\t\tTop (MAX_Y): " + this.getBoundary(Boundary.MAX_Y) + "\n"
				+ "\t\tFront (MAX_Z): " + this.getBoundary(Boundary.MAX_Z) + "\n"
				+ "\t\tBack (MIN_Z): " + this.getBoundary(Boundary.MIN_Z) + "\n"
				+ "\t\tLeft: (MIN_X): " + this.getBoundary(Boundary.MIN_X) + "\n"
				+ "\t\tRight: (MAX_X): " + this.getBoundary(Boundary.MAX_X) + "\n"
				+ "\t\tBottom: (MIN_Y): " + this.getBoundary(Boundary.MIN_Y) + "\n"
				+ "\tVisible: " + this.isVisible();
	}
}