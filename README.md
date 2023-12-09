# Space Cubevaders

## Description

Ce projet est un jeu de type Space Invaders, réalisé en Java avec la librairie JOGL (Java OpenGL).

## Détails techniques

### To-do

- [ ] Créer une méthode permettant de déterminer si un objet est visible par la caméra selon sa position et sa taille
- [ ] Calculer la vitesse de déplacement/rotation des objets selon la fréquence d'affichage
- [ ] Ajouter un système de score
- [ ] Ajouter un système de vies
- [ ] Mettre à jour la documentation

### Bugs connus

- L'affichage d'un volume change les couleurs des autres objets graphiques

### Architecture

#### Diagramme de classes

- [Diagramme de classes draw.io](Docs/Doc.drawio)
- [Diagramme de classes PlantUML](Docs/Doc.plantuml)

#### Packages

Le projet est découpé en plusieurs packages :

- **common** : contient les classes communes à tous les packages, notamment les constantes et les classes utilitaires
- **gl** : contient les classes permettant de gérer l'affichage du jeu
  - **frames** : contient les classes permettant de gérer les fenêtres du jeu
  - **objects** :
    - **rules** : contient les classes permettant de gérer les règles de construction d'objets graphiques
    - **items** : contient les classes des objets du jeu (vaisseau, ennemis, missiles, bonus, etc.)
    - **shapes** : contient les classes des formes géométriques (carré, cercle, etc.) qui permettront de construire des volumes
    - **volumes** : contient les classes des volumes (cubes, sphères, etc.) qui permettront de construire des objets 3D

### Affichage d'un objet

Pour afficher un objet, il faut le dessiner, puis le placer dans l'espace.
Un objet graphique ([GraphicalObject](src/objects/rules/GraphicalObject.java)) peut être une forme géométrique ([Shape](src/objects/rules/Shape.java)) ou un volume ([Volume](src/objects/rules/Volume.java)).  
Créer une forme revient à créer un fichier dans le package **shapes** qui implémente les méthodes héritées de [Shape](src/objects/rules/Shape.java).  
Créer un volume revient donc à créer un fichier dans le package **volumes** qui implémente les méthodes héritées de [Volume](src/objects/rules/Volume.java) et se dessine avec un ensemble de formes géométriques ([Shape](src/objects/rules/Shape.java)).

OpenGL fonctionne avec un système de pile. Chaque affichage d'objet doit donc être encadré par un `glPushMatrix()` et un `glPopMatrix()`, dans lesquels on peut effectuer des transformations ([glTranslate](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glTranslate.xml), [glRotate](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glRotate.xml), [glScale](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glScale.xml)).  
Pour dessiner un objet, il faut donc :

- appeler `glPushMatrix()` pour sauvegarder la matrice de transformation courante
- effectuer les transformations nécessaires
- dessiner les objets à partir d'autres dessins `glPushMatrix()`/`glPopMatrix()`
- appeler `glPopMatrix()` pour restaurer la matrice de transformation précédente
- recommencer pour chaque objet à dessiner

Ces opérations sont ici dans la méthode `display()` de [GraphicalObject](src/objects/rules/GraphicalObject.java).
On notera que cette méthode est lue à l'envers (bottom-up) en raison du système de pile d'OpenGL et des matrices.

On notera également que les affichages sont donc récursifs puisque le dessin d'un volume nécessite l'appel à display sur chacune de ses formes géométriques qui le composent.  
Exemple:

```java
/* Afficher un volume */
 public void display() {
  // Sauvegarder la matrice actuelle
  this.getGl().glPushMatrix();
  {
   // Déplacer l'objet graphique
   [...]
   // Tourner l'objet graphique
   [...]
   // Mettre à l'échelle l'objet graphique
    [...]
   // Dessiner l'objet graphique
   this.draw();
  }
  // Restaurer la matrice précédente
  this.getGl().glPopMatrix();
}

/* Dessiner le volume */
public void draw() {
  // Dessiner ce volume consiste à afficher toutes les formes qui le composent
  for (Shape shape : this.getShapes()) {
   shape.display();
  }
}

```
