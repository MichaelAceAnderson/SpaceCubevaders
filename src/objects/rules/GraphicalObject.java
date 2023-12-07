package objects.rules;

import com.jogamp.opengl.GL2;

public abstract class GraphicalObject {
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Position
	protected float posX, posY, posZ;
	// Angle
	protected float angleX, angleY, angleZ;
	// Taille
	protected float scaleX, scaleY, scaleZ;
	// Couleur
	protected float red, green, blue;
	// Contexte OpenGL
	protected GL2 gl;

	/* CONSTRUCTEURS */
	/**
	 * Créer un objet graphique
	 * 
	 * @param gl     Le contexte OpenGL
	 * @param posX   La position en X
	 * @param posY   La position en Y
	 * @param posZ   La position en Z
	 * @param angleX L'angle en X
	 * @param angleY L'angle en Y
	 * @param angleZ L'angle en Z
	 * @param scaleX La taille sur l'axe X
	 * @param scaleY La taille sur l'axe Y
	 * @param scaleZ La taille sur l'axe Z
	 * @param r      La couleur rouge
	 * @param g      La couleur verte
	 * @param b      La couleur bleue
	 * 
	 */
	public GraphicalObject(GL2 gl, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float r, float g, float b) {
		this.setGl(gl);
		this.setPosX(posX);
		this.setPosY(posY);
		this.setPosZ(posZ);
		this.setAngleX(angleX);
		this.setAngleY(angleY);
		this.setAngleZ(angleZ);
		this.setScaleX(scaleX);
		this.setScaleY(scaleY);
		this.setScaleZ(scaleZ);
		this.setRed(r);
		this.setGreen(g);
		this.setBlue(b);
	}

	/* MÉTHODES */
	/* Getters/Setters */
	public float getPosX() {
		return this.posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return this.posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getPosZ() {
		return this.posZ;
	}

	public void setPosZ(float posZ) {
		this.posZ = posZ;
	}

	public float getAngleX() {
		return this.angleX;
	}

	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	public float getAngleY() {
		return this.angleY;
	}

	public void setAngleY(float angleY) {
		this.angleY = angleY;
	}

	public float getAngleZ() {
		return this.angleZ;
	}

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
	 * Définir la taille de cet objet graphique sur l'axe Z
	 * 
	 * @param scaleZ La taille de l'objet sur l'axe Z
	 */
	public void setScaleZ(float scaleZ) {
		this.scaleZ = scaleZ;
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
	public GL2 getGl() {
		return this.gl;
	}

	/**
	 * Définir le contexte OpenGL
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see GL2
	 */
	public void setGl(GL2 gl) {
		this.gl = gl;
	}

	/**
	 * Afficher cet objet graphique
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see GL2
	 */
	public abstract void draw();

	/**
	 * Afficher cet objet graphique dans son contexte courant
	 * 
	 * @see GL2
	 */
	public void display() {
		gl.glPushMatrix();
		{
			this.getGl().glTranslatef(this.posX, this.posY, this.posZ);
			// Note: L'ordre des rotations est important car elles sont appliquées dans
			// l'ordre inverse et non dans l'ordre de déclaration (bottom-up)
			this.getGl().glRotatef(this.angleY, 0.0f, 1.0f, 0.0f);
			this.getGl().glRotatef(this.angleZ, 0.0f, 0.0f, 1.0f);
			this.getGl().glRotatef(this.angleX, 1.0f, 0.0f, 0.0f);
			this.getGl().glScalef(this.scaleX, this.scaleY, this.scaleZ);
			this.getGl().glColor3f(this.red, this.green, this.blue);
			this.draw();
		}
		gl.glPopMatrix();
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
}
