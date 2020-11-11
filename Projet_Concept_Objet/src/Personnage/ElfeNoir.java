package Personnage;

import Carte.Carte;
import Carte.SafeZone;
import Message.Message;

public class ElfeNoir extends Personnage {
	private String name;
	private Message[] messages = new Message[4];
	private String lastDirection;
	private static int nbInstance = 0;
	private MaitreElfeNoir maitre;

	public ElfeNoir(String nom, MaitreElfeNoir master) {
		if (ElfeNoir.nbInstance < 4) {
			this.name = nom;
			ElfeNoir.nbInstance++;
			this.messages[0] = new Message("Message ElfeNoir 1", 1);
			this.messages[1] = new Message("Message ElfeNoir 2", 1);
			this.messages[2] = new Message("Message ElfeNoir 3", 1);
			this.messages[3] = new Message("Message ElfeNoir 4", 1);
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
			this.setSafezone(SafeZone.SOLITUDE);
		}
	}

}
