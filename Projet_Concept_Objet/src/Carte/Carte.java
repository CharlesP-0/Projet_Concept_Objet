package Carte;

import Personnage.EfleNoir;
import Personnage.Entity;
import Personnage.Khajit;
import Personnage.Nordique;
import Personnage.Orcs;

public class Carte {
	private Colonne[] carte;
	private int nbColonne;
	private int nbLigne;
	//Penser à ajouter le plugin https://marketplace.eclipse.org/content/ansi-escape-console#group-detailsgithub.com/fusesource/jansi
	//Sinon les couleurs ne s'afficheront pas pour les prints
	private String red = "\033[31m";
	private String blue = "\033[34m";
	private String green = "\033[32m";
	private String yellow = "\033[33m";
	private String purple = "\033[35m";
	private String black = "\u001B[0m";

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
			System.out.println(
					String.format("Les cases comprises entre [%d,%d] et [%d,%d] ont été attribuées comme safezone pour "
							+ ville + " .", departX, departY, finX, finY));
		}
	}

	public void setOccupation(int x, int y, Entity entity) {
		this.carte[x].getCases()[y].setOccupied(true);
		this.carte[x].getCases()[y].setIsOccupiedBy(entity);
		System.out.println(String.format("Entity " + entity + " as been set at [%d,%d]", x, y));
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
			System.out.println(String.format("La case [%d,%d] est une safezone de " + safezone, x, y));
		} else {
			System.out.println(String.format("La case [%d,%d] n'est pas une safezone", x, y));
		}
	}

	public void displayMap() {
		for (int i = 0; i < this.nbLigne; i++) {
			for (int j = 0; j < this.nbColonne; j++) {
				if (this.carte[j].getCases()[i].isASafeZone()) {
					if (this.carte[j].getCases()[i].getSafeZone() == SafeZone.EPERVINE) {
						System.out.print(red + " E" + black);
					} else if (this.carte[j].getCases()[i].getSafeZone() == SafeZone.MARKATH) {
						System.out.print(green +" M"+ black);
					} else if (this.carte[j].getCases()[i].getSafeZone() == SafeZone.FORTDHIVER) {
						System.out.print(blue+" F"+black);
					} else if (this.carte[j].getCases()[i].getSafeZone() == SafeZone.SOLITUDE) {
						System.out.print(purple+" S"+black);
					}
				} else if (this.carte[j].getCases()[i].isOccupied()) {
					if (this.carte[j].getCases()[i].getIsOccupiedBy().getClass() == Khajit.class) {
						System.out.print(blue+" K"+black);
					} else if (this.carte[j].getCases()[i].getIsOccupiedBy().getClass() == EfleNoir.class) {
						System.out.print(purple+" N"+black);
					} else if (this.carte[j].getCases()[i].getIsOccupiedBy().getClass() == Nordique.class) {
						System.out.print(green+" N"+black);
					} else if (this.carte[j].getCases()[i].getIsOccupiedBy().getClass() == Orcs.class) {
						System.out.print(red+" O"+black);
					} else {
						System.out.print(yellow+" X"+black);
					}
				} else {
					System.out.print(" .");
				}
				if (j != (this.nbColonne - 1)) {
					System.out.print(" -");
				}
			}
			System.out.println();
		}
	}
}
