package Carte;

import Personnage.Entity;

public class Carte {
	private Colonne[] carte;
	private int nbColonne;
	private int nbLigne;

	public Carte(int ligne, int colonne) {
		this.nbColonne = colonne;
		this.nbLigne = ligne;
		this.carte = new Colonne[nbColonne];
		for (int i = 0; i < nbColonne; i++) {
			this.carte[i] = new Colonne(nbLigne);
		}
		System.out.println("La carte a bien été créée");
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
			System.out.println(String.format("Les cases comprises entre [%d,%d] et [%d,%d] ont été attribuées comme safezone pour "+ville+" .",departX, departY, finX, finY));
		}
	}

	public void setOccupation(int x, int y, Entity entity) {
		this.carte[x].getCases()[y].setOccupied(true);
		this.carte[x].getCases()[y].setIsOccupiedBy(entity);
		System.out.println(String.format("Entity "+entity+" as been set at [%d,%d]", x, y));
	}

	public void isOccupied(int x, int y) {
		boolean occupied = this.carte[x].getCases()[y].isOccupied();
		if (occupied) {
			System.out.println(String.format("La case [%d,%d] est occupée", x, y));
		} else {
			System.out.println(String.format("La case [%d,%d] n'est pas occupée", x, y));
		}
	}

	public void isASafeZone(int x, int y) {
		boolean isASafezone = this.carte[x].getCases()[y].isASafeZone();
		if (isASafezone) {
			SafeZone safezone = this.carte[x].getCases()[y].getSafeZone();
			System.out.println(String.format("La case [%d,%d] est une safezone de "+ safezone, x, y));
		} else {
			System.out.println(String.format("La case [%d,%d] n'est pas une safezone", x, y));
		}
	}
}
