package Personnage;

import Carte.Carte;
import Carte.SafeZone;
import Message.Message;

public class Nordique extends Personnage {
	private String name;
	private int pointAction = 100;
	private String lastDirection;
	private MaitreNordique maitre;
	private static int nbInstance = 0;

	public Nordique(String nom, MaitreNordique master) {
		if (Nordique.nbInstance < 4) {
			this.name = nom;
			Nordique.nbInstance++;
			this.messages[0] = new Message("Message Nordique 1", 1);
			this.messages[1] = new Message("Message Nordique 2", 1);
			this.messages[2] = new Message("Message Nordique 3", 1);
			this.messages[3] = new Message("Message Nordique 4", 1);
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
			this.setSafezone(SafeZone.MARKATH);
		}
	}
	@Override
	public String toString() {
		return this.name;
	}
}
