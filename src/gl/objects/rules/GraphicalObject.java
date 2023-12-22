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

	// Limites de la boîte de collision
	public static enum Boundary {
		MIN_X, MAX_X, MIN_Y, MAX_Y, MIN_Z, MAX_Z
	}

	/**
	 * Créer un objet graphique
	 * 
	 * @param canvas    Le canvas OpenGL
	 * @param posX      La position en X
	 * @param posY      La position en Y
	 * @param posZ      La position en Z
	 * @param angleX    L'angle en X
	 * @param angleY    L'angle en Y
	 * @param angleZ    L'angle en Z
	 * @param scaleX    La taille sur l'axe X
	 * @param scaleY    La taille sur l'axe Y
	 * @param scaleZ    La taille sur l'axe Z
	 * @param speedX    La vitesse sur l'axe X
	 * @param speedY    La vitesse sur l'axe Y
	 * @param speedZ    La vitesse sur l'axe Z
	 * @param rotationX La rotation sur l'axe X
	 * @param rotationY La rotation sur l'axe Y
	 * @param rotationZ La rotation sur l'axe Z
	 * @param r         La couleur rouge
	 * @param g         La couleur verte
	 * @param b         La couleur bleue
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
	 * Récupérer la position en X de cet objet graphique
	 * 
	 * @return La position en X
	 */
	public float getPosX() {
		return this.posX;
	}

	/**
	 * Définir la position en X de cet objet graphique
	 */
	public void setPosX(float posX) {
		this.posX = posX;
	}

	/**
	 * Récupérer la position en Y de cet objet graphique
	 * 
	 * @return La position en Y
	 */
	public float getPosY() {
		return this.posY;
	}

	/**
	 * Définir la position en Y de cet objet graphique
	 * 
	 * @param posY La position en Y
	 */
	public void setPosY(float posY) {
		this.posY = posY;
	}

	/**
	 * Récupérer la position en Z de cet objet graphique
	 * 
	 * @return La position en Z
	 */
	public float getPosZ() {
		return this.posZ;
	}

	/**
	 * Définir la position en Z de cet objet graphique
	 * 
	 * @param posZ La position en Z
	 */
	public void setPosZ(float posZ) {
		this.posZ = posZ;
	}

	/**
	 * Récupérer l'angle en X de cet objet graphique
	 * 
	 * @return L'angle en X
	 */
	public float getAngleX() {
		return this.angleX;
	}

	/**
	 * Définir l'angle en X de cet objet graphique
	 * 
	 * @param angleX L'angle en X
	 */
	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	/**
	 * Récupérer l'angle en Y de cet objet graphique
	 * 
	 * @return L'angle en Y
	 */
	public float getAngleY() {
		return this.angleY;
	}

	/**
	 * Définir l'angle en Y de cet objet graphique
	 */
	public void setAngleY(float angleY) {
		this.angleY = angleY;
	}

	/**
	 * Récupérer l'angle en Z de cet objet graphique
	 */
	public float getAngleZ() {
		return this.angleZ;
	}

	/**
	 * Définir l'angle en Z de cet objet graphique
	 * 
	 * @param angleZ L'angle en Z
	 */
	public void setAngleZ(float angleZ) {
		this.angleZ = angleZ;
	}

	/**
	 * Définir la taille de cet objet graphique sur l'axe X
	 * 
	 * @param scaleX La taille de l'objet sur l'axe X
	 */
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * Récupérer la taille de cet objet graphique sur l'axe X
	 * 
	 * @return La taille de l'objet sur l'axe X
	 */
	public float getScaleX() {
		return this.scaleX;
	}

	/**
	 * Définir la taille de cet objet graphique sur l'axe Y
	 * 
	 * @param scaleY La taille de l'objet sur l'axe Y
	 */
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * Récupérer la taille de cet objet graphique sur l'axe Y
	 * 
	 * @return La taille de l'objet sur l'axe Y
	 */
	public float getScaleY() {
		return this.scaleY;
	}

	/**
	 * Récupérer la vitesse de cet objet graphique sur l'axe X
	 * 
	 * @return La vitesse de l'objet sur l'axe X
	 */
	public float getSpeedX() {
		return this.speedX;
	}

	/**
	 * Définir la vitesse de cet objet graphique sur l'axe X
	 * 
	 * @param speedX La vitesse de l'objet sur l'axe X
	 */
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	/**
	 * Récupérer la vitesse de cet objet graphique sur l'axe Y
	 * 
	 * @return La vitesse de l'objet sur l'axe Y
	 */
	public float getSpeedY() {
		return this.speedY;
	}

	/**
	 * Définir la vitesse de cet objet graphique sur l'axe Y
	 * 
	 * @param speedY La vitesse de l'objet sur l'axe Y
	 */
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	/**
	 * Récupérer la vitesse de cet objet graphique sur l'axe Z
	 * 
	 * @return La vitesse de l'objet sur l'axe Z
	 */
	public float getSpeedZ() {
		return this.speedZ;
	}

	/**
	 * Définir la vitesse de cet objet graphique sur l'axe Z
	 * 
	 * @param speedZ La vitesse de l'objet sur l'axe Z
	 */
	public void setSpeedZ(float speedZ) {
		this.speedZ = speedZ;
	}

	/**
	 * Récupérer la rotation de cet objet graphique sur l'axe X
	 * 
	 * @return La rotation de l'objet sur l'axe X
	 */
	public float getRotationX() {
		return this.rotationX;
	}

	/**
	 * Définir la rotation de cet objet graphique sur l'axe X
	 * 
	 * @param rotationX La rotation de l'objet sur l'axe X
	 */
	public void setRotationX(float rotationX) {
		this.rotationX = rotationX;
	}

	/**
	 * Récupérer la rotation de cet objet graphique sur l'axe Y
	 * 
	 * @return La rotation de l'objet sur l'axe Y
	 */
	public float getRotationY() {
		return this.rotationY;
	}

	/**
	 * Définir la rotation de cet objet graphique sur l'axe Y
	 * 
	 * @param rotationY La rotation de l'objet sur l'axe Y
	 */
	public void setRotationY(float rotationY) {
		this.rotationY = rotationY;
	}

	/**
	 * Récupérer la rotation de cet objet graphique sur l'axe Z
	 * 
	 * @return La rotation de l'objet sur l'axe Z
	 */
	public float getRotationZ() {
		return this.rotationZ;
	}

	/**
	 * Définir la rotation de cet objet graphique sur l'axe Z
	 * 
	 * @param rotationZ La rotation de l'objet sur l'axe Z
	 */
	public void setRotationZ(float rotationZ) {
		this.rotationZ = rotationZ;
	}

	/**
	 * Récupérer la taille de cet objet graphique sur l'axe Z
	 * 
	 * @return La taille de l'objet sur l'axe Z
	 */
	public float getScaleZ() {
		return this.scaleZ;
	}

	/**
	 * Définir la taille de cet objet graphique sur l'axe Z
	 * 
	 * @param scaleZ La taille de l'objet sur l'axe Z
	 */
	public void setScaleZ(float scaleZ) {
		this.scaleZ = scaleZ;
	}

	/**
	 * Définir le taux de rouge de cet objet graphique
	 * 
	 * @param red Le taux de couleur rouge (entre 0.0 et 1.0)
	 */
	public void setRed(float red) {
		this.red = red;
	}

	/**
	 * Récupérer le taux de rouge de cet objet graphique
	 * 
	 * @return Le taux de couleur rouge
	 */
	public float getRed() {
		return this.red;
	}

	/**
	 * Définir le taux de vert de cet objet graphique
	 * 
	 * @param green Le taux de couleur verte (entre 0.0 et 1.0)
	 */
	public void setGreen(float green) {
		this.green = green;
	}

	/**
	 * Récupérer le taux de vert de cet objet graphique
	 * 
	 * @return Le taux de couleur verte
	 */
	public float getGreen() {
		return this.green;
	}

	/**
	 * Définir le taux de bleu de cet objet graphique
	 * 
	 * @param blue Le taux de couleur bleue (entre 0.0 et 1.0)
	 */
	public void setBlue(float blue) {
		this.blue = blue;
	}

	/**
	 * Récupérer le taux de bleu de cet objet graphique
	 * 
	 * @return Le taux de couleur bleu
	 */
	public float getBlue() {
		return this.blue;
	}

	/**
	 * Récupérer le contexte OpenGL
	 * 
	 * @return Le contexte OpenGL
	 * 
	 * @see GL2
	 */
	public GL2 getGl2() {
		return this.getCanvas().getGL().getGL2();
	}

	/**
	 * Définir le contexte OpenGL
	 * 
	 * @param canvas Le canvas OpenGL
	 * 
	 * @see Canvas
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * Récupérer le canvas OpenGL
	 * 
	 * @return Le canvas OpenGL
	 * 
	 * @see Canvas
	 */
	public Canvas getCanvas() {
		return this.canvas;
	}

	/**
	 * Récupérer les coordonnées des limites de cet objet
	 * 
	 * @return Les coordonnées des limites
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
	 * Récupérer la coordonnée d'une frontière de cet objet graphique
	 * 
	 * @param boundary La frontière à récupérer
	 */
	public float getBoundary(Boundary boundary) {
		return this.getBoundaries()[boundary.ordinal()];
	}

	/**
	 * Vérifier si cet objet graphique est en collision avec un autre objet
	 * graphique
	 * 
	 * @param object L'objet graphique avec lequel vérifier la collision
	 * 
	 * @return true si les objets sont en collision, false sinon
	 */
	public boolean isColliding(GraphicalObject object) {
		float[] thisBoundingBox = this.getBoundaries();
		float[] objectBoundingBox = object.getBoundaries();

		// Si le dessus de cet objet est plus haut que le dessous de l'autre objet
		boolean isColliding = (thisBoundingBox[Boundary.MAX_Y.ordinal()] >= objectBoundingBox[Boundary.MIN_Y.ordinal()]
				// Si le dessous de cet objet est plus bas que le dessus de l'autre objet
				&& thisBoundingBox[Boundary.MIN_Y.ordinal()] <= objectBoundingBox[Boundary.MAX_Y.ordinal()]
				// Si l'arrière de cet objet est plus loin que l'avant de l'autre objet
				&& thisBoundingBox[Boundary.MAX_Z.ordinal()] >= objectBoundingBox[Boundary.MIN_Z.ordinal()]
				// Si l'avant de cet objet est plus proche que l'arrière de l'autre objet
				&& thisBoundingBox[Boundary.MIN_Z.ordinal()] <= objectBoundingBox[Boundary.MAX_Z.ordinal()]
				// Si la gauche de cet objet est plus à gauche que la droite de l'autre objet
				&& thisBoundingBox[Boundary.MIN_X.ordinal()] <= objectBoundingBox[Boundary.MAX_X.ordinal()]
				// Si la droite de cet objet est plus à droite que la gauche de l'autre objet
				&& thisBoundingBox[Boundary.MAX_X.ordinal()] >= objectBoundingBox[Boundary.MIN_X.ordinal()]);

		return isColliding;
	}

	/**
	 * Vérifier si cet objet graphique est visible par la caméra du canvas à partir
	 * de la position et de la taille de sa boîte de collision. NOTE: La caméra doit
	 * être positionnée à (0, 0, 0) et orientée vers l'axe négatif des Z.
	 * 
	 * @return true si l'objet est visible, false sinon
	 */
	public boolean isVisible() {
		// Si l'objet est derrière le champ de vision de la caméra
		if (this.getBoundary(Boundary.MAX_Z) > 0) {
			return false;
		}

		// Si l'objet est plus loin que la profondeur maximum
		if (this.getBoundary(Boundary.MIN_Z) < -this.getCanvas().getDrawDistance()) {
			return false;
		}

		// Si l'objet est plus à gauche que la limite de gauche
		if (this.getBoundary(Boundary.MIN_X) < -this.getCanvas().getDrawDistance()) {
			return false;
		}

		// Si l'objet est plus à droite que la limite de droite
		if (this.getBoundary(Boundary.MAX_X) > this.getCanvas().getDrawDistance()) {
			return false;
		}

		// Si l'objet est plus haut que la limite haute
		if (this.getBoundary(Boundary.MAX_Y) > this.getCanvas().getDrawDistance()) {
			return false;
		}

		// Si l'objet est plus bas que la limite basse
		if (this.getBoundary(Boundary.MAX_X) < -this.getCanvas().getDrawDistance()) {
			return false;
		}

		return true;
	}

	/**
	 * Dessiner cet objet graphique.
	 * Cette méthode doit être implémentée dans les classes filles afin de
	 * déterminer comment l'objet doit être dessiné et affiché dans la méthode
	 * display().
	 * 
	 * @see GraphicalObject#display()
	 */
	public abstract void draw();

	/**
	 * Dessiner les collisions de cet objet graphique
	 * 
	 * @return true si les collisions ont été dessinées, false sinon
	 */
	public boolean drawCollisions() {
		if (!this.getCanvas().getObjects().contains(this)) {
			return false;
		}

		// Dessiner les lignes de la boîte de collision
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
	 * Attacher des informations à cet objet graphique
	 * 
	 * @return true si les informations ont été dessinées, false sinon
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
	 * Afficher cet objet graphique dans son contexte courant.
	 * Note: Les transformations sont appliquées dans l'ordre inverse de leur
	 * apparition dans le code en raison du fonctionnement des matrices
	 * 
	 * @see GL2
	 */
	public void display() {
		// Sauvegarder l'état actuel du rendu
		this.getGl2().glPushMatrix();
		{
			// Effectuer des transformations sur l'objet à dessiner
			this.getGl2().glTranslatef(this.getPosX(), this.getPosY(), this.getPosZ());
			this.getGl2().glRotatef(this.getAngleX(), 1.0f, 0.0f, 0.0f);
			this.getGl2().glRotatef(this.getAngleZ(), 0.0f, 0.0f, 1.0f);
			this.getGl2().glRotatef(this.getAngleY(), 0.0f, 1.0f, 0.0f);
			this.getGl2().glScalef(this.getScaleX(), this.getScaleY(), this.getScaleZ());

			// Dessiner l'objet
			if (Debug.getMode(Debug.Mode.LINE_MODE)) {
				this.getGl2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			}
			float r = this.getRed();
			float g = this.getGreen();
			float b = this.getBlue();
			// Le mode lignes prévaut sur le mode contour
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
		// Restaurer les transformations sauvegardées
		this.getGl2().glPopMatrix();
	}

	/**
	 * Déplacer l'objet graphique
	 * 
	 * @param x La position en X
	 * @param y La position en Y
	 * @param z La position en Z
	 * 
	 */
	public void move(float x, float y, float z) {
		this.setPosX(this.getPosX() + x);
		this.setPosY(this.getPosY() + y);
		this.setPosZ(this.getPosZ() + z);
	}

	/**
	 * Faire tourner cet objet graphique (par rapport à sa position actuelle)
	 * 
	 * @param angleX L'angle en X
	 * @param angleY L'angle en Y
	 * @param angleZ L'angle en Z
	 * 
	 * @see GL2
	 */
	public void rotate(float angleX, float angleY, float angleZ) {
		this.setAngleX(this.getAngleX() + angleX);
		this.setAngleY(this.getAngleY() + angleY);
		this.setAngleZ(this.getAngleZ() + angleZ);
	}

	/**
	 * Définir la rotation de cet objet graphique
	 * 
	 * @param angleX L'angle en X
	 * @param angleY L'angle en Y
	 * @param angleZ L'angle en Z
	 * 
	 * @see GraphicalObject#rotate(float, float, float)
	 */
	public void setRotation(float angleX, float angleY, float angleZ) {
		this.setAngleX(angleX);
		this.setAngleY(angleY);
		this.setAngleZ(angleZ);
	}

	/**
	 * Récupérer les informations de l'objet 3D
	 * 
	 * @return Les informations de l'objet 3D
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
	 * Afficher en console les caractéristiques de cet objet graphique
	 */
	@Override
	public String toString() {
		return "GraphicalObject: " + "\n"
				+ "\tType: " + this.getClass().getSimpleName() + "\n"
				+ "\tPosition: X: " + this.getPosX() + ", Y: " + this.getPosY() + ", Z: " + this.getPosZ() + "\n"
				+ "\tAngle: X: " + this.getAngleX() + ", Y: " + this.getAngleY() + ", Z: " + this.getAngleZ() + "\n"
				+ "\tScale: X: " + this.getScaleX() + ", Y: " + this.getScaleY() + ", Z: " + this.getScaleZ() + "\n"
				+ "\tCouleur: R: " + this.getRed() + ", G: " + this.getGreen() + ", B: " + this.getBlue() + "\n"
				+ "\tVitesse: X: " + this.getSpeedX() + ", Y: " + this.getSpeedY() + ", Z: " + this.getSpeedZ() + "\n"
				+ "\tRotation: X: " + this.getRotationX() + ", Y: " + this.getRotationY() + ", Z: "
				+ this.getRotationZ() + "\n"
				+ "\tBoundingBox: " + "\n"
				+ "\t\tDessus (MAX_Y): " + this.getBoundary(Boundary.MAX_Y) + "\n"
				+ "\t\tAvant (MAX_Z): " + this.getBoundary(Boundary.MAX_Z) + "\n"
				+ "\t\tArrière (MIN_Z): " + this.getBoundary(Boundary.MIN_Z) + "\n"
				+ "\t\tGauche: (MIN_X): " + this.getBoundary(Boundary.MIN_X) + "\n"
				+ "\t\tDroite: (MAX_X): " + this.getBoundary(Boundary.MAX_X) + "\n"
				+ "\t\tDessous: (MIN_Y): " + this.getBoundary(Boundary.MIN_Y) + "\n"
				+ "\tVisible: " + this.isVisible();
	}
}
