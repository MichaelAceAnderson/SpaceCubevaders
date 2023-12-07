package objects.volumes;

import java.util.ArrayList;

import com.jogamp.opengl.GL2;

import common.RGBColor;
import objects.rules.GraphicalObject;
import objects.shapes.Square;
import objects.shapes.Triangle;

public class Pyramid extends GraphicalObject {
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Faces de la pyramide
	private ArrayList<GraphicalObject> faces;

	private enum Face {
		BASE, FRONT, BACK, LEFT, RIGHT
	}

	/* CONSTRUCTEURS */
	/**
	 * Créer une pyramide avec cinq instances de Triangle, une position, un angle,
	 * une
	 * taille et une couleur
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
	 * @see GL2
	 */
	public Pyramid(GL2 gl, float posX, float posY, float posZ,
			float angleX, float angleY, float angleZ,
			float scaleX, float scaleY, float scaleZ,
			float r, float g, float b) {
		// Appeler le constructeur de la classe mère pour instancier l'objet graphique
		super(gl, posX, posY, posZ, angleX, angleY, angleZ, scaleX, scaleY, scaleZ, r, g, b);

		// Créer la liste des faces
		this.setFaces(new ArrayList<GraphicalObject>());
		// Ajouter la base de la pyramide
		faces.add(new Square(gl, 0, 0, 0,
				90, 0, 0,
				1, 1, 1,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]));
		// Ajouter la face avant
		faces.add(new Triangle(gl, 0, 0, 1,
				-45, 0, 0,
				1, 1.5f, 1,
				RGBColor.RED[0], RGBColor.RED[1], RGBColor.RED[2]));
		// Ajouter la face arrière
		faces.add(new Triangle(gl, 0, 0, -1,
				45, 0, 0,
				1, 1.5f, 1,
				RGBColor.GREEN[0], RGBColor.GREEN[1], RGBColor.GREEN[2]));
		// Ajouter la face gauche
		faces.add(new Triangle(gl, -1, 0, 0,
				-45, -90, 0,
				1, 1.5f, 1,
				RGBColor.YELLOW[0], RGBColor.YELLOW[1], RGBColor.YELLOW[2]));
		// Ajouter la face droite
		Triangle test = new Triangle(gl, 1, 0, 0,
				-45, 90, 0,
				1, 1.5f, 1,
				RGBColor.BLUE[0], RGBColor.BLUE[1], RGBColor.BLUE[2]);
		faces.add(test);
		System.out.println(test.getAngleX() + " " + test.getAngleY() + " " + test.getAngleZ());

	}

	/**
	 * Créer une pyramide par défaut
	 * 
	 * @param gl Le contexte OpenGL
	 * 
	 * @see Pyramid#Pyramid(GL2, float, float, float, float, float, float, float,
	 *      float,
	 *      float, float)
	 */
	public Pyramid(GL2 gl) {
		this(gl, 0.0f, 0.0f, -10.0f,
				0.0f, 0.0f, 0.0f,
				1.0f, 1.0f, 1.0f,
				RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]);
	}

	/**
	 * Définir les faces de la pyramide
	 * 
	 * @param faces Un tableau de triangles (faces) de la pyramide
	 */
	public void setFaces(ArrayList<GraphicalObject> faces) {
		this.faces = faces;
	}

	/**
	 * Récupérer les faces de la pyramide
	 * 
	 * @return Un tableau de triangles (faces) de la pyramide
	 */
	public ArrayList<GraphicalObject> getFaces() {
		return this.faces;
	}

	/**
	 * Récupérer une face de la pyramide
	 * 
	 * @param face La face de la pyramide selon l'énumération Face
	 * @return La face de la pyramide
	 * 
	 * @see Face
	 */
	public GraphicalObject getFace(Face face) {
		return this.getFaces().get(face.ordinal());
	}

	/* Autres méthodes */
	/**
	 * Afficher cette pyramide
	 * 
	 * @see GraphicalObject#draw()
	 */
	public void draw() {
		// Afficher chaque face de la pyramide
		for (GraphicalObject face : this.faces) {
			face.display();
		}
	}
}