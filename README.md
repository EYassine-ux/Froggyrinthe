# Froggyrinthe 🐸

A console-based maze game built in Java using the EKO framework.
Navigate through 5 rooms, collect the key, avoid enemies, and escape!

## Gameplay
- Find the **key** to unlock the exit door
- Avoid enemies or lose a heart (5 hearts max)
- Collect **potions** to restore full health
- Survive all 5 rooms to win

## Enemies
- **Ghost** — bounces horizontally or vertically
- **Frog** — shoots its tongue left or right
- **Insect** — wall-hugging patrol movement
- **Skeleton** — tracks and chases the player
- **Fire** — stationary hazard with random animation

## Tech Stack
Java, EKO Framework, OOP, File I/O (map loading from `.txt`), Audio

## Key Concepts
- Singleton pattern (`GestionnaireObjetsJeu`, `GestionnaireNiveaux`)
- Collision system via `Collisionnable` interface
- Level loading from text-based map files
- Layer-based rendering (z-ordering)

## Controls
| Key | Action |
|-----|--------|
| WASD / Arrow keys | Move |
| SPACE | Confirm / Continue |

## How to Run
Requires the EKO framework. Open in NetBeans or your Java IDE and run `Main.java`.

## Credits
- **EKO Framework** — created by teacher Drouin Eric
- **Game loop** — provided by teacher Drouin Eric
- Built in collaboration with **Eric Zanacan** — [@Agera63](https://github.com/Agera63)
