package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class MaitreKhajit extends Maitre {

	private static MaitreKhajit lUnique = null;
	private List<Message> listeMessages;
	public int valeur;

	private MaitreKhajit() {
	};

	public static MaitreKhajit getInstance() {
		if (lUnique == null) {
			lUnique = new MaitreKhajit();
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
