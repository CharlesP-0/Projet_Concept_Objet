package Personnage;

import java.util.ArrayList;
import java.util.List;

import Carte.Carte;
import Message.Message;

public class MaitreKhajit extends Maitre {

	private static MaitreKhajit lUnique = null;
	private List<Message> listeMessages = new ArrayList<Message>();
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

		System.out.println("Boucle");
		for (Message message : personnage.getMessagesReceived()) {
			System.out.println("test existance");
			if (message == null) {
				continue;
			}if (!(this.listeMessages.contains(message))) {
				System.out.println("Pas contenu");
				this.listeMessages.add(message);
				this.valeur+= message.getPoids();
			}
		}
	}
}
