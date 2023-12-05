package objects;

import com.jogamp.opengl.GL2;

public abstract class GraphicalObject {
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Position
	protected float posX, posY, posZ;
	// Angle
	protected float angleX, angleY, angleZ;
	// Taille
	protected float scale;
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
	 * @param scale  La taille
	 * @param r      La couleur rouge
	 * @param g      La couleur verte
	 * @param b      La couleur bleue
	 * 
	 */
	public GraphicalObject(GL2 gl, float posX, float posY, float posZ, float angleX, float angleY, float angleZ, float scale,
			float r, float g, float b) {
		this.gl = gl;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.angleX = angleX;
		this.angleY = angleY;
		this.angleZ = angleZ;
		this.scale = scale;
		this.red = r;
		this.green = g;
		this.blue = b;
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
	
	public float getScale() {
		return this.scale;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public float getRed() {
		return this.red;
	}
	
	public void setRed(float red) {
		this.red = red;
	}
	
	public float getGreen() {
		return this.green;
	}
	
	public void setGreen(float green) {
		this.green = green;
	}
	
	public float getBlue() {
		return this.blue;
	}
	
	public void setBlue(float blue) {
		this.blue = blue;
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
			this.getGl().glRotatef(this.angleX, 1.0f, 0.0f, 0.0f);
			this.getGl().glRotatef(this.angleY, 0.0f, 1.0f, 0.0f);
			this.getGl().glRotatef(this.angleZ, 0.0f, 0.0f, 1.0f);
			this.getGl().glScalef(this.scale, this.scale, this.scale);
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
		this.posX += x;
		this.posY += y;
		this.posZ += z;
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
