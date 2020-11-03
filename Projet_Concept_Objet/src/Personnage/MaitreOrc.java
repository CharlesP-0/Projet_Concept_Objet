package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class MaitreOrc extends Maitre {

	private static MaitreOrc lUnique = null;
	private List<Message> listeMessages;
	public int valeur = 0;
	private MaitreOrc() {
	};

	public static MaitreOrc getInstance() {
		if (lUnique == null) {
			lUnique = new MaitreOrc();
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
			if (!(this.listeMessages.contains(message))) {
				this.listeMessages.add(message);
				this.valeur+= message.getPoids();
			}
		}
	}
}
