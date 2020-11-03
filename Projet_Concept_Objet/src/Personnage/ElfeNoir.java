package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class ElfeNoir extends Personnage {
	private String name;
	private List<Message> messages;
	private String lastDirection;
	private int pointAction;
	private static int nbInstance = 0;
	private MaitreElfeNoir maitre;

	public void incrPointAction() {
		this.pointAction = 100;
	}

	public ElfeNoir(String nom, List<Message> messageClasse, MaitreElfeNoir master) {
		if (this.nbInstance < 4) {
			this.name = nom;
			this.nbInstance++;
			this.messages = messageClasse;
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
		}
	}

	@Override
	public void move(Carte carte) throws InterruptedException {
		super.move(carte);
		if (carte.getCase(this.getPositionX(), this.getPositionY()).isASafeZone()) {
			this.maitre.meet(this, carte);
			this.incrPointAction();
		}
	}
}
