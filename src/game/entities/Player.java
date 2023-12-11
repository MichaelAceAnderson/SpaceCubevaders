package game.entities;

import game.entities.rules.Entity;
import gl.common.RGBColor;
import gl.objects.items.Missile;
import gl.objects.rules.GraphicalObject;

public class Player extends Entity {
	// Missile du joueur
	private Missile missile;

	/**
	 * Créer un joueur
	 * 
	 * @param representation Objet graphique représentant le joueur
	 */
	public Player(GraphicalObject representation) {
		super(representation);
	}

	/**
	 * Tirer un projectile
	 */
	public void shoot() {
		if (this.getMissile() == null) {
			float posX = this.getRepresentation().getPosX();
			float posY = this.getRepresentation().getPosY();
			float posZ = this.getRepresentation().getPosZ();
			this.setMissile(new Missile(this.getRepresentation().getCanvas(), posX, posY, posZ,
					0.0f, 0.0f, 0.0f,
					1.0f, 1.0f, 1.0f,
					0.0f, 1.0f, 0.0f,
					0.0f, 0.0f, 0.0f,
					RGBColor.WHITE[0], RGBColor.WHITE[1], RGBColor.WHITE[2]));
			this.getRepresentation().getCanvas().getObjects().add(this.getMissile());
		}

	}

	/**
	 * Définir le missile du joueur
	 * 
	 * @param missile Missile du joueur
	 */
	public void setMissile(Missile missile) {
		this.missile = missile;
	}

	/**
	 * Récupérer le missile du joueur
	 * 
	 * @return Missile du joueur
	 */
	public Missile getMissile() {
		return this.missile;
	}

}
