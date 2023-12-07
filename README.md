# Space Cubevaders

## Description

Ce projet est un jeu de type Space Invaders, réalisé en Java avec la librairie JOGL (Java OpenGL).

## Détails techniques

### Architecture

Le projet est découpé en plusieurs packages :

- **common** : contient les classes communes à tous les packages, notamment les constantes et les classes utilitaires
- **gl** : contient les classes permettant de gérer l'affichage du jeu
  - **frames** : contient les classes permettant de gérer les fenêtres du jeu
  - **objects** :
    - **rules** : contient les classes permettant de gérer les règles de construction d'objets graphiques
    - **items** : contient les classes des objets du jeu (vaisseau, ennemis, missiles, bonus, etc.)
    - **shapes** : contient les classes des formes géométriques (carré, cercle, etc.) qui permettront de construire des volumes
    - **volumes** : contient les classes des volumes (cubes, sphères, etc.) qui permettront de construire des objets 3D
