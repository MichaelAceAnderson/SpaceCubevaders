package common;

public final class RGBColor {
	/* PROPRIÉTÉS/ATTRIBUTS */
	// Couleur rouge
	public static final float RED[] = { 1.0f, 0.0f, 0.0f };
	// Couleur verte
	public static final float GREEN[] = { 0.0f, 1.0f, 0.0f };
	// Couleur bleue
	public static final float BLUE[] = { 0.0f, 0.0f, 1.0f };
	// Couleur jaune
	public static final float YELLOW[] = { 1.0f, 1.0f, 0.0f };
	// Couleur cyan
	public static final float CYAN[] = { 0.0f, 1.0f, 1.0f };
	// Couleur magenta
	public static final float MAGENTA[] = { 1.0f, 0.0f, 1.0f };
	// Couleur blanche
	public static final float WHITE[] = { 1.0f, 1.0f, 1.0f };
	// Couleur noire
	public static final float BLACK[] = { 0.0f, 0.0f, 0.0f };
	// Couleur grise
	public static final float GRAY[] = { 0.5f, 0.5f, 0.5f };
	// Couleur grise foncée
	public static final float DARK_GRAY[] = { 0.3f, 0.3f, 0.3f };
	// Couleur grise claire
	public static final float LIGHT_GRAY[] = { 0.7f, 0.7f, 0.7f };
	// Couleur orange
	public static final float ORANGE[] = { 1.0f, 0.5f, 0.0f };
	// Couleur rose
	public static final float PINK[] = { 1.0f, 0.68f, 0.68f };
	// Couleur violette
	public static final float PURPLE[] = { 0.5f, 0.0f, 0.5f };
	// Couleur marron
	public static final float BROWN[] = { 0.5f, 0.35f, 0.05f };
	// Couleur olive
	public static final float OLIVE[] = { 0.5f, 0.5f, 0.0f };
	// Couleur turquoise
	public static final float TURQUOISE[] = { 0.25f, 0.88f, 0.82f };
	// Couleur bleu ciel
	public static final float SKY_BLUE[] = { 0.53f, 0.81f, 0.92f };
	// Couleur bleu marine
	public static final float NAVY[] = { 0.0f, 0.0f, 0.5f };
	// Couleur rouge foncé
	public static final float DARK_RED[] = { 0.5f, 0.0f, 0.0f };
	// Couleur vert foncé
	public static final float DARK_GREEN[] = { 0.0f, 0.5f, 0.0f };
	// Couleur bleu foncé
	public static final float DARK_BLUE[] = { 0.0f, 0.0f, 0.5f };
	// Couleur jaune foncé
	public static final float DARK_YELLOW[] = { 0.5f, 0.5f, 0.0f };
	// Couleur cyan foncé
	public static final float DARK_CYAN[] = { 0.0f, 0.5f, 0.5f };
	// Couleur magenta foncé
	public static final float DARK_MAGENTA[] = { 0.5f, 0.0f, 0.5f };
	// Couleur grise foncée
	public static final float DARK_GRAY2[] = { 0.3f, 0.3f, 0.3f };
	// Couleur grise claire
	public static final float LIGHT_GRAY2[] = { 0.7f, 0.7f, 0.7f };
	// Couleur orange foncé
	public static final float DARK_ORANGE[] = { 0.5f, 0.25f, 0.0f };
	// Couleur rose foncé
	public static final float DARK_PINK[] = { 0.5f, 0.34f, 0.34f };
	// Couleur violette foncée
	public static final float DARK_PURPLE[] = { 0.25f, 0.0f, 0.25f };
	// Couleur marron foncé
	public static final float DARK_BROWN[] = { 0.25f, 0.17f, 0.025f };
	// Couleur olive foncé
	public static final float DARK_OLIVE[] = { 0.25f, 0.25f, 0.0f };
	// Couleur turquoise foncé
	public static final float DARK_TURQUOISE[] = { 0.125f, 0.44f, 0.41f };
	// Couleur bleu ciel foncé
	public static final float DARK_SKY_BLUE[] = { 0.265f, 0.405f, 0.46f };
	// Couleur bleu marine foncé
	public static final float DARK_NAVY[] = { 0.0f, 0.0f, 0.25f };
	// Couleur rouge clair
	public static final float LIGHT_RED[] = { 1.0f, 0.5f, 0.5f };
	// Couleur vert clair
	public static final float LIGHT_GREEN[] = { 0.5f, 1.0f, 0.5f };
	// Couleur bleu clair
	public static final float LIGHT_BLUE[] = { 0.5f, 0.5f, 1.0f };
	// Couleur jaune clair
	public static final float LIGHT_YELLOW[] = { 1.0f, 1.0f, 0.5f };
	// Couleur cyan clair
	public static final float LIGHT_CYAN[] = { 0.5f, 1.0f, 1.0f };
	// Couleur magenta clair
	public static final float LIGHT_MAGENTA[] = { 1.0f, 0.5f, 1.0f };
	// Couleur grise clair
	public static final float LIGHT_GRAY3[] = { 0.7f, 0.7f, 0.7f };
	// Couleur orange clair
	public static final float LIGHT_ORANGE[] = { 1.0f, 0.75f, 0.5f };
	// Couleur rose clair
	public static final float LIGHT_PINK[] = { 1.0f, 0.84f, 0.84f };
	// Couleur violette clair
	public static final float LIGHT_PURPLE[] = { 0.75f, 0.5f, 0.75f };
	// Couleur marron clair
	public static final float LIGHT_BROWN[] = { 0.75f, 0.5f, 0.25f };
	// Couleur olive clair
	public static final float LIGHT_OLIVE[] = { 0.75f, 0.75f, 0.5f };
	// Couleur turquoise clair
	public static final float LIGHT_TURQUOISE[] = { 0.375f, 0.94f, 0.875f };
	// Couleur bleu ciel clair
	public static final float LIGHT_SKY_BLUE[] = { 0.795f, 0.905f, 0.96f };
	// Couleur bleu marine clair
	public static final float LIGHT_NAVY[] = { 0.5f, 0.5f, 0.75f };
	// Couleur rouge très clair
	public static final float VERY_LIGHT_RED[] = { 1.0f, 0.75f, 0.75f };
	// Couleur vert très clair
	public static final float VERY_LIGHT_GREEN[] = { 0.75f, 1.0f, 0.75f };
	// Couleur bleu très clair
	public static final float VERY_LIGHT_BLUE[] = { 0.75f, 0.75f, 1.0f };
	// Couleur jaune très clair
	public static final float VERY_LIGHT_YELLOW[] = { 1.0f, 1.0f, 0.75f };
	// Couleur cyan très clair
	public static final float VERY_LIGHT_CYAN[] = { 0.75f, 1.0f, 1.0f };
	// Couleur magenta très clair
	public static final float VERY_LIGHT_MAGENTA[] = { 1.0f, 0.75f, 1.0f };
	// Couleur grise très clair
	public static final float VERY_LIGHT_GRAY[] = { 0.85f, 0.85f, 0.85f };
	// Couleur orange très clair
	public static final float VERY_LIGHT_ORANGE[] = { 1.0f, 0.875f, 0.75f };
	// Couleur rose très clair
	public static final float VERY_LIGHT_PINK[] = { 1.0f, 0.93f, 0.93f };
	// Couleur violette très clair
	public static final float VERY_LIGHT_PURPLE[] = { 0.875f, 0.75f, 0.875f };
	// Couleur marron très clair
	public static final float VERY_LIGHT_BROWN[] = { 0.875f, 0.75f, 0.625f };
	// Couleur olive très clair
	public static final float VERY_LIGHT_OLIVE[] = { 0.875f, 0.875f, 0.75f };
	// Couleur turquoise très clair
	public static final float VERY_LIGHT_TURQUOISE[] = { 0.625f, 0.97f, 0.94f };
	// Couleur bleu ciel très clair
	public static final float VERY_LIGHT_SKY_BLUE[] = { 0.895f, 0.955f, 0.98f };
	// Couleur bleu marine très clair
	public static final float VERY_LIGHT_NAVY[] = { 0.75f, 0.75f, 0.875f };
	// Couleur transparente
	public static final float TRANSPARENT[] = { 0.0f, 0.0f, 0.0f, 0.0f };
	
}
