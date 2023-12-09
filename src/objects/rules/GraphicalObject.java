package objects.rules;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

import common.DebugMode;

public abstract class GraphicalObject {
	// Position
	protected float posX, posY, posZ;
	// Angle
	protected float angleX, angleY, angleZ;
	// Taille
	protected float scaleX, scaleY, scaleZ;
	// Couleur
	protected float red, green, blue;
	// Mouvements dans le temps
	protected float speedX, speedY, speedZ;
	// Rotations dans le temps
	protected float rotationX, rotationY, rotationZ;
	// Contexte OpenGL
	protected GL2 gl;

	/**
	 * Créer un objet graphique
	 * 
	 * @param gl        Le contexte OpenGL
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
	public GraphicalObject(GL2 gl, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float speedX, float speedY, float speedZ,
			float rotationX, float rotationY, float rotationZ,
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
	 * Dessiner cet objet graphique.
	 * Cette méthode doit être implémentée dans les classes filles afin de
	 * déterminer comment l'objet doit être dessiné et affiché dans la méthode
	 * display().
	 * 
	 * @see GraphicalObject#display()
	 */
	public abstract void draw();

	/**
	 * Afficher cet objet graphique dans son contexte courant.
	 * 
	 * NOTE: Cette méthode est lue à l'envers (bottom-up) en raison du
	 * fonctionnement des matrices
	 * 
	 * @see GL2
	 */
	public void display() {
		// Sauvegarder la matrice actuelle
		this.getGl().glPushMatrix();
		{
			// Déplacer l'objet graphique
			this.getGl().glTranslatef(this.getPosX(), this.getPosY(), this.getPosZ());
			// Tourner l'objet graphique (l'ordre des rotations est important)
			this.getGl().glRotatef(this.getAngleX(), 1.0f, 0.0f, 0.0f);
			this.getGl().glRotatef(this.getAngleZ(), 0.0f, 0.0f, 1.0f);
			this.getGl().glRotatef(this.getAngleY(), 0.0f, 1.0f, 0.0f);
			// Mettre à l'échelle l'objet graphique
			this.getGl().glScalef(this.getScaleX(), this.getScaleY(), this.getScaleZ());
			if (DebugMode.LINE_MODE) {
				// Activer le mode contour
				gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
			}
			// Dessiner l'objet graphique
			this.draw();
			if (DebugMode.LINE_MODE) {
				// Revenir au mode de remplissage normal
				gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
			}
		}
		// Restaurer la matrice précédente
		this.getGl().glPopMatrix();
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
	 * Afficher en console les caractéristiques de cet objet graphique
	 */
	@Override
	public String toString() {
		return "GraphicalObject: " + "\n"
				+ "\tType: " + this.getClass().getSimpleName() + "\n"
				+ "\tPosition: X: " + this.getPosX() + ", Y: " + this.getPosY() + ", Z: " + this.getPosZ() + "\n"
				+ "\tAngle: X: " + this.getAngleX() + ", Y: " + this.getAngleY() + ", Z: " + this.getAngleZ() + "\n"
				+ "\tScale: X: " + this.getScaleX() + ", Y: " + this.getScaleY() + ", Z: " + this.getScaleZ() + "\n"
				+ "\tCouleur: R: " + this.getRed() + ", G: " + this.getGreen() + ", B: " + this.getBlue();
	}
}
