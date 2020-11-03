package Personnage;

import java.util.List;

import Carte.Carte;
import Message.Message;

public class Khajit extends Personnage {
	private String name;
	private int pointAction = 100;
	private Message[] messages;
	private String lastDirection;
	private MaitreKhajit maitre;
	private int nbInstance = 0;

	public void incrPointAction() {
		this.pointAction = 100;
	}

	public Khajit(String nom, Message[] messageKhajit, MaitreKhajit master) {
		if (this.nbInstance < 4) {
			this.name = nom;
			this.nbInstance++;
			this.messages = messageKhajit;
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