# Space Cubevaders

## Description

Ce projet est un jeu de type Space Invaders, réalisé en Java avec la librairie JOGL (Java OpenGL).

## Détails techniques

### To-do

- [ ] Régler les bugs connus
- [ ] Ajouter un système de score
- [ ] Ajouter un système de vies
- [ ] Mettre à jour la documentation
- [ ] (Optionnel) Ajouter une icône à la fenêtre du jeu
- [ ] (Optionnel) Ajouter une abstraction de la classe Game pour permettre de créer des jeux différents (Space Invaders, Pacman, etc...)
- [ ] (Optionnel) Prendre en compte les rotations dans la BoundingBox et la détection des collisions
- [ ] (Optionnel) Vérifier si les commentaires sont à jour par rapport au fonctionnement actuel du code

### Bugs connus

- La collision des items n'est pas détectée correctement
- La position/collision des objets graphiques en mouvement/rotation n'est pas toujours détectée correctement
- Les items ne tournent pas autour d'eux même mais autour d'un point fixe distant de leur centre
- La détection de la visibilité d'un objet graphique n'est pas calculée à partir de la perspective de la caméra mais à partir de la profondeur d'affichage maximum (Voir `isVisible()` de [GraphicalObject](src/gl/objects/rules/GraphicalObject.java))
- La boîte de collisions est pour l'instant un cube qui ne prend pas en compte les rotations de l'objet ou la profondeur nulle d'une forme (Voir `getBoundingBox()` de [GraphicalObject](src/gl/objects/rules/GraphicalObject.java))

### Architecture

#### Diagramme de classes

- [Diagramme de classes draw.io](Docs/Doc.drawio)
- [Diagramme de classes PlantUML](Docs/Doc.plantuml)

#### Packages

Le projet est découpé en plusieurs packages :

- **common** : contient les classes communes à tous les packages, notamment les constantes et les classes utilitaires
- **game** : contient les classes permettant de gérer le jeu
  - **entities** : contient les classes permettant de gérer les entités du jeu (Joueur, ennemi, etc...)
    - **rules** : contient les classes permettant de gérer les règles de construction des entités
- **gl** : contient les classes permettant de gérer l'affichage du jeu
  - **canvas** : contient les classes permettant de gérer les fenêtres du jeu
    - **rules** : contient les classes permettant de gérer les règles de construction des fenêtres
  - **objects** :
    - **rules** : contient les classes permettant de gérer les règles de construction d'objets graphiques
    - **items** : contient les classes des objets du jeu (vaisseau, ennemis, missiles, bonus, etc.)
    - **shapes** : contient les classes des formes géométriques (carré, cercle, etc.) qui permettront de construire des volumes
    - **volumes** : contient les classes des volumes (cubes, sphères, etc.) qui permettront de construire des objets 3D
- **main** : contient les classes de lancement du programme

### Mode DEBUG

La classe [/common/DebugMode.java](/src/common/DebugMode.java) contient des constantes à modifier lors du développement pour afficher des informations utiles sur les objets, l'espace, modifier leurs comportements etc...

### Affichage d'un objet

Pour afficher un objet, il faut le dessiner, puis le placer dans l'espace.
Un objet graphique ([GraphicalObject](src/objects/rules/GraphicalObject.java)) peut être une forme géométrique ([Shape](src/objects/rules/Shape.java)) ou un volume ([Volume](src/objects/rules/Volume.java)).  
Créer une forme revient à créer un fichier dans le package **shapes** qui implémente les méthodes héritées de [Shape](src/objects/rules/Shape.java).  
Créer un volume revient donc à créer un fichier dans le package **volumes** qui implémente les méthodes héritées de [Volume](src/objects/rules/Volume.java) et se dessine avec un ensemble de formes géométriques ([Shape](src/objects/rules/Shape.java)).

OpenGL fonctionne avec un système de pile. Chaque affichage d'objet doit donc être encadré par un `glPushMatrix()` et un `glPopMatrix()`, dans lesquels on peut effectuer des transformations ([glTranslate](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glTranslate.xml), [glRotate](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glRotate.xml), [glScale](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glScale.xml)).  
On notera que les transformations s'appliquent dans l'ordre inverse de leur appel en raison du fonctionnement de la pile de matrices.  
Pour dessiner un objet, il faut donc :

- appeler `glPushMatrix()` pour sauvegarder la matrice de transformation courante
- effectuer les transformations nécessaires
- dessiner les objets à partir d'autres dessins `glPushMatrix()`/`glPopMatrix()`
- appeler `glPopMatrix()` pour restaurer la matrice de transformation précédente
- recommencer pour chaque objet à dessiner

Ces opérations sont ici dans la méthode `display()` de [GraphicalObject](src/objects/rules/GraphicalObject.java).

On notera que les affichages sont donc récursifs puisque le dessin d'un volume nécessite l'appel à display sur chacune de ses formes géométriques qui le composent.  
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
   // Dessiner l'objet graphique en position 0,0,0 avant de le déplacer à sa position réelle
   this.draw();
   // Dessiner les collisions en position 0,0,0 avant de déplacer l'objet graphique à sa position réelle
   this.drawCollisions();
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
