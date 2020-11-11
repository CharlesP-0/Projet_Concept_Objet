package Personnage;

import Carte.Carte;
import Carte.SafeZone;
import Message.Message;

public class Orcs extends Personnage {
	private String name;
	private String lastDirection;
	private MaitreOrc maitre;
	private static int nbInstance= 0;

	public Orcs(String nom, MaitreOrc master) {
		if (Orcs.nbInstance < 4) {
			this.name = nom;
			Orcs.nbInstance++;
			this.messages[0] = new Message("Message Orc 1", 1);
			this.messages[1] = new Message("Message Orc 2", 1);
			this.messages[2] = new Message("Message Orc 3", 1);
			this.messages[3] = new Message("Message Orc 4", 1);
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
			this.setSafezone(SafeZone.EPERVINE);
		}
	}
}
