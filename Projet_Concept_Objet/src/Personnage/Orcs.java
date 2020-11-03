package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class Orcs extends Personnage {
	private String name;
	private Message[] messages;
	private String lastDirection;
	private MaitreNordique maitre;
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
	public Orcs(String nom, Message[] messagenordique, MaitreNordique master) {
		if (this.nbInstance < 4) {
			this.name = nom;
			this.nbInstance++;
			this.messages = messagenordique;
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
		}
	}
}
