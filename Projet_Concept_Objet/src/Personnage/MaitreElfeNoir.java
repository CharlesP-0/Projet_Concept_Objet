package Personnage;

import java.util.List;
import Carte.Carte;
import Message.Message;

public class MaitreElfeNoir extends Maitre {
	private static MaitreElfeNoir lUnique = null;
	private List<Message> listeMessages;
	public int valeur = 0;
	private MaitreElfeNoir() {
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
			if (!(this.listeMessages.contains(message))) {
				this.listeMessages.add(message);
				this.valeur+= message.getPoids();
			}
		}
	}
}
