package Carte;

import Personnage.Entity;

public class Carte {
	private Colonne[] carte;
	private int nbColonne;
	private int nbLigne;

	public Carte(int ligne, int colonne) {
		this.nbColonne = colonne;
		this.nbLigne = ligne;
		for (int i = 0; i < nbColonne; i++) {
			carte[i] = new Colonne(nbLigne);
		}
	}

	public void setCaseAsSafeZone(int departX, int departY, int finX, int finY, SafeZone ville) {
		if (departX > finX || departY > finY) {
			System.out
					.println("Impossible d'instancier une safezone, veillez à ce que departX > finX et departY > finY");
		} else {
			for (int i = departX; i <= finX; i++) {
				for (int j = departY; j <= finY; j++) {
					this.carte[i].getCases()[j].setASafeZone(true);
					this.carte[i].getCases()[j].setSafeZone(ville);
				}
			}
			System.out.println(String.format("Les cases comprises entre [ %d , %d ] et [ %d , %d ] ont été "
					+ "attribuées comme safezone pour %d.", departX, departY, finX, finY, ville));
		}
	}

	public void setOccupation(int x, int y, Entity entity) {
		this.carte[x].getCases()[y].setOccupied(true);
		this.carte[x].getCases()[y].setIsOccupiedBy(entity);
		System.out.println(String.format("Entity %d as been set at [%d,%d]", entity, x, y));
	}
}
