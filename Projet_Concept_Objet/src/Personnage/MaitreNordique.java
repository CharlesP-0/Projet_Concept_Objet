package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class MaitreNordique extends Maitre {

	private static MaitreNordique lUnique =null;
	private List<Message> listeMessages;
	public int valeur = 0;
	private MaitreNordique() {
	};

	public static MaitreNordique getInstance() {
		if (lUnique == null) {
			lUnique = new MaitreNordique();
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
