package objects.volumes;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import common.RGBColor;
import objects.GraphicalObject;
import objects.shapes.Square;

public class Cube extends GraphicalObject {
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Faces du cube
	private ArrayList<Square> faces;
	private enum Face {
		TOP, FRONT, BACK, LEFT, RIGHT, BOTTOM
	}

	/* CONSTRUCTEURS */
	/**
	 * Créer un cube avec quatre instances de Square, une position, un angle, une
	 * taille et une couleur
	 * 
	 * @param gl     Le contexte OpenGL
	 * @param front  L'instance de Square pour la face avant
	 * @param back   L'instance de Square pour la face arrière
	 * @param left   L'instance de Square pour la face gauche
	 * @param right  L'instance de Square pour la face droite
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
	 * @see GL2
	 */
	public Cube(GL2 gl, float posX, float posY, float posZ, float angleX, float angleY, float angleZ, float scale,
			float r, float g, float b) {
		// Appeler le constructeur de la classe mère pour instancier l'objet graphique
		super(gl, posX, posY, posZ, angleX, angleY, angleZ, scale, r, g, b);

		// Créer la liste des faces
		this.faces = new ArrayList<Square>();
		// Ajouter la face supérieure
		faces.add(new Square(gl, 0, 1, 0,
				90, 0, 0,
				1,
				RGBColor.CYAN[0], RGBColor.CYAN[1], RGBColor.CYAN[2]));
		// Ajouter la face avant
		faces.add(new Square(gl, 0, 0, 1,
				0, 0, 0,
				1,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]));
		// Ajouter la face arrière
		faces.add(new Square(gl, 0, 0, -1, 0,
				0, 0, 1,
				RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]));
		// Ajouter la face gauche
		faces.add(new Square(gl, -1, 0, 0,
				0, -90, 0,
				1,
				RGBColor.YELLOW[0], RGBColor.YELLOW[1], RGBColor.YELLOW[2]));
		// Ajouter la face droite
		faces.add(new Square(gl, 1, 0, 0,
				0, 90, 0,
				1,
				RGBColor.BLUE[0], RGBColor.BLUE[1], RGBColor.BLUE[2]));
		// Ajouter la face inférieure
		faces.add(new Square(gl, 0, -1, 0,
				90, 0, 0,
				1,
				RGBColor.MAGENTA[0], RGBColor.MAGENTA[1], RGBColor.MAGENTA[2]));
	}

	/**
	 * Créer un cube par défaut
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see Cube#Cube(GL2, float, float, float, float, float, float, float, float,
	 *      float, float)
	 */
	public Cube(GL2 gl) {
		this(gl, 0.0f, 0.0f, -10.0f, 0.0f, 0.0f, 0.0f, 1.0f, RGBColor.WHITE[0], RGBColor.WHITE[1],
				RGBColor.WHITE[2]);
	}

	/* MÉTHODES */
	/* Getters/Setters */
	public ArrayList<Square> getFaces() {
		return this.faces;
	}

	/**
	 * Récupérer une face du cube
	 * 
	 * @param face La face du cube selon l'énumération Face
	 * @return La face du cube
	 * 
	 * @see Face
	 */
	public Square getFace(Face face) {
		return this.getFaces().get(face.ordinal());
	}

	/* Autres méthodes */
	/**
	 * Afficher ce cube
	 * 
	 * @see GraphicalObject#draw()
	 */
	public void draw() {
		// Afficher chaque face du cube
		for (Square face : this.faces) {
			face.display();
		}
	}
}