package Carte;

public class Colonne {
	private Case[] cases;
	private int nbCases;

	public Colonne(int cases) {
		this.nbCases = cases;
		this.cases = new Case[nbCases];
		for (int i = 0; i < nbCases; i++) {
			this.cases[i] = new Case();
		}
	}

	public Case[] getCases() {
		return cases;
	}

	public void setColonne(Case[] cases) {
		this.cases = cases;
	}

	public int getNbCases() {
		return nbCases;
	}

	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}
}
