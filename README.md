# Space Cubevaders

- [Space Cubevaders](#space-cubevaders)
	- [Description](#description)
	- [Détails techniques](#détails-techniques)
		- [To-do](#to-do)
		- [Bugs connus](#bugs-connus)
		- [Architecture](#architecture)
			- [Diagramme de classes](#diagramme-de-classes)
			- [Packages](#packages)
		- [Mode DEBUG](#mode-debug)
		- [Affichage d'un objet](#affichage-dun-objet)

## Description

Ce projet est un jeu de type Space Invaders, réalisé en Java avec la librairie JOGL (Java OpenGL).

## Détails techniques

Ce projet a été pensé pour pouvoir être facilement étendu et supporter plusieurs jeux.
Il a un mode de debug graphique et informationnel dont les paramètres sont disponibles dans la classe [/src/common/DebugMode.java](src/common/Debug.java).
Ces modes peuvent être dynamiquement changés et sont définis dans le point d'entrée du programme (MainCanvas ou DebugGL).
N'hésitez pas à jouer avec pour comprendre le fonctionnement du rendu.

### To-do

- [ ] Régler les bugs connus
- [ ] (Optionnel) Refactoriser les codes de positions et de collisions
- [ ] (Optionnel) Ajouter des menus
  - [ ] (Optionnel) Ajouter un menu d'accueil
  - [ ] (Optionnel) Ajouter un menu de pause
  - [ ] (Optionnel) Ajouter un menu de fin de partie
  - [ ] (Optionnel) Ajouter un menu de sélection de jeu
  - [ ] (Optionnel) Ajouter un menu de paramètres (son, musique, etc...)
- [ ] (Optionnel) Prendre en compte les rotations dans la BoundingBox et la détection des collisions
- [ ] (Optionnel) Encapsuler correctement les méthodes & attributs
- [ ] (Optionnel) Ajouter des constantes de messages pour un éventuel système de langues
- [ ] (Optionnel) Implémenter des méthodes à l'écoute d'évènements (Ex: `onPlayerDeath()`, `OnEnemySpawn()`, `OnEnnemyTakeDamage` etc...)

### Bugs connus

- Selon la vitesse d'exécution, le programme génère parfois des ConcurrentModificationException lors de la création de Shelters (voir [SpaceCubevadersGame](src/games/spacecubevaders/SpaceCubevaders.java))
- Les ennemis ne se déplacent pas toujours de façon uniforme (Certains ennemis se déplacent plus vite que d'autres)
- La collision des composites n'est pas détectée correctement
- La position/collision des objets graphiques en mouvement/rotation n'est pas toujours détectée correctement
- Les composites ne tournent pas autour d'eux même mais autour d'un point fixe distant de leur centre
- La détection de la visibilité d'un objet graphique n'est pas calculée à partir de la perspective de la caméra mais à partir de la profondeur d'affichage maximum (Voir `isVisible()` de [GraphicalObject](src/gl/objects/rules/GraphicalObject.java))
- La boîte de collisions ne prend pas en compte les rotations de l'objet ou la profondeur nulle d'une forme (Voir `getBoundaries()` de [GraphicalObject](src/gl/objects/rules/GraphicalObject.java))

### Architecture

#### Diagramme de classes

Note: Le diagramme ne contient ici pas les méthodes pour des raisons de lisibilité.  
[Diagramme de classes PlantUML](Docs/Doc.plantuml)  
![Diagramme de classes](Docs/Doc.jpg)

#### Packages

Le projet est découpé en plusieurs packages :

- **common** : contient les classes communes à tous les packages, notamment les constantes et les classes utilitaires
- **games** : contient les classes permettant de gérer les règles de construction des jeux
  - **rules** : contient les classes permettant de gérer les règles de construction des jeux
  - **spacecubevaders** : contient les classes permettant de gérer le jeu Space Cubevaders
    - **assets** : contient les assets du jeu (images, sons, etc...)
    - **entities** : contient les classes permettant de gérer les entités du jeu (Joueur, ennemi, etc...)
      - **rules** : contient les classes permettant de gérer les règles de construction des entités
    - **items** : contient les classes permettant de gérer les objets du jeu (missile, etc...)
    - **structures** : contient les classes permettant de gérer les structures du jeu (abris, etc...)
- **gl** : contient les classes permettant de gérer l'affichage du jeu
  - **frames** : contient les classes permettant de gérer les fenêtres
    - **rules** : contient les classes permettant de gérer les règles de construction des fenêtres
  - **canvas** : contient les classes permettant de gérer les cadres de rendu du jeu
    - **rules** : contient les classes permettant de gérer les règles de construction des cadres de rendu du jeu
  - **objects** :
    - **rules** : contient les classes permettant de gérer les règles de construction d'objets graphiques
    - **shapes** : contient les classes des formes géométriques (carré, triangle, etc...) qui permettront de construire des volumes
    - **volumes** : contient les classes des volumes (cubes, pyramides, etc...) qui permettront de construire des objets 3D
- **main** : contient les classes de lancement du programme

### Mode DEBUG

La classe [/common/DebugMode.java](src/common/DebugMode.java) contient des constantes à modifier lors du développement pour afficher des informations utiles sur les objets, l'espace, modifier leurs comportements etc...

### Affichage d'un objet

Pour afficher un objet, il faut le dessiner, puis le placer dans l'espace.
Un objet graphique ([GraphicalObject](src/objects/rules/GraphicalObject.java)) peut être une forme géométrique ([Shape](src/objects/rules/Shape.java)) ou un volume ([Volume](src/objects/rules/Volume.java)).  
Créer une forme revient à créer un fichier dans le package **shapes** qui implémente les méthodes héritées de [Shape](src/objects/rules/Shape.java).  
Créer un volume revient donc à créer un fichier dans le package **volumes** qui implémente les méthodes héritées de [Volume](src/objects/rules/Volume.java) et se dessine avec un ensemble de formes géométriques ([Shape](src/objects/rules/Shape.java)).

OpenGL fonctionne avec un système de pile. Chaque affichage d'objet doit donc être encadré par un `glPushMatrix()` et un `glPopMatrix()`, dans lesquels on peut effectuer des transformations ([glTranslate](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glTranslate.xml), [glRotate](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glRotate.xml), [glScale](https://www.khronos.org/registry/OpenGL-Refpages/gl2.1/xhtml/glScale.xml)).  
On notera que les transformations s'appliquent dans l'ordre inverse de leur appel en raison du fonctionnement des transformations qui sont effectuées par multiplication de matrices.
Pour afficher un objet, il faut donc :

- appeler `glPushMatrix()` pour sauvegarder la matrice d'états actuelle en haut de la pile
- effectuer les transformations nécessaires
- dessiner les objets
- appeler `glPopMatrix()` pour restaurer la matrice d'états précédemment sauvegardée
- recommencer pour chaque objet avec des transformations propres

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