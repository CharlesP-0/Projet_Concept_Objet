package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class Orcs extends Personnage {
	private String name;
	private int pointAction;
	private List<Message> messages;
	private String lastDirection;
	private MaitreOrc maitre;
	private int nbInstance= 0;

	public void incrPointAction() {
		this.pointAction = 100;
	}

	@Override
	public void move(Carte carte) throws InterruptedException {
		super.move(carte);
		if (carte.getCase(this.getPositionX(), this.getPositionY()).isASafeZone()) {
			this.maitre.meet(this, carte);
			this.incrPointAction();
		}
	}
	public Orcs(String nom, List<Message> messageClasse, MaitreOrc master) {
		if (this.nbInstance < 4) {
			this.name = nom;
			this.nbInstance++;
			this.messages = messageClasse;
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
		}
	}
}
