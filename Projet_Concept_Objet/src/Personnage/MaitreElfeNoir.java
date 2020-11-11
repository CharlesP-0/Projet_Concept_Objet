package Personnage;

import java.util.ArrayList;
import java.util.List;
import Carte.Carte;
import Message.Message;

public class MaitreElfeNoir extends Maitre {
	private static MaitreElfeNoir lUnique = null;
	private List<Message> listeMessages= new ArrayList<Message>();
	public int valeur = 0;
	private MaitreElfeNoir() {
		this.setName("Apo Dondos");
	};

	public static MaitreElfeNoir getInstance() {
		if (lUnique == null) {
			lUnique = new MaitreElfeNoir();
		}
		return lUnique;
	}

	@Override
	public void meet(Personnage personnage, Carte carte) {
		this.takeMsgFrom(personnage);
	}

	@Override
	public void takeMsgFrom(Personnage personnage) {
		for (Message message : personnage.getMessagesReceived()) {
			if (message == null) {
				continue;
			}if (!(this.listeMessages.contains(message))) {
				this.listeMessages.add(message);
				this.valeur+= message.getPoids();
			}
		}
	}
	@Override
	public String toString() {
		return ("Maitre Nordique : " + this.getName());
	}
}
