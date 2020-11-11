package Personnage;

import Carte.SafeZone;
import Message.Message;

public class Khajit extends Personnage {
	private String name;
	private Message[] messages = new Message[4];
	private String lastDirection;
	private MaitreKhajit maitre;
	private static int nbInstance = 0;

	public Khajit(String nom, MaitreKhajit master) {
		if (Khajit.nbInstance < 4) {
			this.name = nom;
			Khajit.nbInstance++;
			this.messages[0] = new Message("Message Khajiit 1", 1);
			this.messages[1] = new Message("Message Khajiit 2", 1);
			this.messages[2] = new Message("Message Khajiit 3", 1);
			this.messages[3] = new Message("Message Khajiit 4", 1);
			this.maitre = master;
			this.incrPointAction();
			super.maitre = this.maitre;
			this.setSafezone(SafeZone.FORTDHIVER);
		}
	}			
}