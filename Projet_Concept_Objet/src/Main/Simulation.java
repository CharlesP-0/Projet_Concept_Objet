package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Carte.Carte;
import Carte.SafeZone;
import Message.Message;
import Personnage.ElfeNoir;
import Personnage.Khajit;
import Personnage.Maitre;
import Personnage.MaitreElfeNoir;
import Personnage.MaitreKhajit;
import Personnage.MaitreNordique;
import Personnage.MaitreOrc;
import Personnage.Nordique;
import Personnage.Obstacle;
import Personnage.Orcs;
import Personnage.Personnage;

public class Simulation {

	public static void main(String[] args) throws InterruptedException {
		boolean won = false;
		Carte carte = new Carte(20, 20);

		MaitreKhajit maitreKhajit = MaitreKhajit.getInstance();
		MaitreNordique maitreNordique = MaitreNordique.getInstance();
		MaitreOrc maitreOrc = MaitreOrc.getInstance();
		MaitreElfeNoir maitreElfeNoir = MaitreElfeNoir.getInstance();
		Khajit testk1 = new Khajit("Jzargo", maitreKhajit);
		Orcs testo1 = new Orcs("Shazog gro-Dumul", maitreOrc);
		Nordique testn1 = new Nordique("Lidya", maitreNordique);
		ElfeNoir teste1 = new ElfeNoir("Erandur", maitreElfeNoir);
		Khajit testk2 = new Khajit("Jzargo", maitreKhajit);
		Orcs testo2 = new Orcs("Shazog gro-Dumul", maitreOrc);
		Nordique testn2 = new Nordique("Lidya", maitreNordique);
		ElfeNoir teste2 = new ElfeNoir("Erandur", maitreElfeNoir);
		Khajit testk3 = new Khajit("Jzargo", maitreKhajit);
		Orcs testo3 = new Orcs("Shazog gro-Dumul", maitreOrc);
		Nordique testn3 = new Nordique("Lidya", maitreNordique);
		ElfeNoir teste3 = new ElfeNoir("Erandur", maitreElfeNoir);
		carte.setCaseAsSafeZone(0, 0, 4, 4, SafeZone.FORTDHIVER);
		carte.setCaseAsSafeZone(0, 15, 4, 19, SafeZone.MARKATH);
		carte.setCaseAsSafeZone(15, 15, 19, 19, SafeZone.EPERVINE);
		carte.setCaseAsSafeZone(15, 0, 19, 4, SafeZone.SOLITUDE);
		carte.setOccupation(0, 0, maitreKhajit);
		carte.setOccupation(0, 19, maitreNordique);
		carte.setOccupation(19, 19, maitreOrc);
		carte.setOccupation(19, 0, maitreElfeNoir);
		carte.setOccupation(4, 4, testk1);
		carte.setOccupation(15, 15, testo1);
		carte.setOccupation(4, 15, testn1);
		carte.setOccupation(15, 4, teste1);
		carte.setOccupation(3, 4, testk2);
		carte.setOccupation(16, 15, testo2);
		carte.setOccupation(3, 15, testn2);
		carte.setOccupation(16, 4, teste2);
		carte.setOccupation(4, 3, testk3);
		carte.setOccupation(15, 16, testo3);
		carte.setOccupation(4, 16, testn3);
		carte.setOccupation(15, 3, teste3);

		int quantity = 50;
		Obstacle[] listObstacles = null;
		generateObstacle(quantity, carte, listObstacles);

		Personnage[] listePersonnage = new Personnage[20];
		listePersonnage[0] = maitreKhajit;
		listePersonnage[1] = maitreNordique;
		listePersonnage[2] = maitreOrc;
		listePersonnage[3] = maitreElfeNoir;
		listePersonnage[4] = testk1;
		listePersonnage[5] = testo1;
		listePersonnage[6] = testn1;
		listePersonnage[7] = teste1;
		listePersonnage[8] = testk2;
		listePersonnage[9] = testo2;
		listePersonnage[10] = testn2;
		listePersonnage[11] = teste2;
		listePersonnage[12] = testk3;
		listePersonnage[13] = testo3;
		listePersonnage[14] = testn3;
		listePersonnage[15] = teste3;
		while (!won) {
			carte.displayMap();
			TimeUnit.SECONDS.sleep(1);
			for (Personnage personnage : listePersonnage) {
				if (personnage == null) {
					continue;
				}
				if (!(personnage instanceof Maitre)) {
					System.out.println(personnage);
					personnage.setTarget(carte);
					personnage.move(carte);
				}
			}
			if (maitreNordique.valeur >= 4) {
				won = true;
			} else if (maitreKhajit.valeur >= 4) {
				won = true;
			} else if (maitreOrc.valeur >= 4) {
				won = true;
			} else if (maitreElfeNoir.valeur >= 4) {
				won = true;
			}
		}
	}

	public static void generateObstacle(int quantity, Carte carte, Obstacle[] listOfObstacles) {
		listOfObstacles = new Obstacle[quantity];
		Random rand = new Random();
		for (int i = 0; i < quantity; i++) {
			int x = rand.nextInt(20);
			int y = 0;
			if (x <= 6 || x >= 14) {
				while (y <= 6 || y >= 14) {
					y = rand.nextInt(14);
				}
			} else {
				y = rand.nextInt(20);
			}
			listOfObstacles[i] = new Obstacle(x, y);
			if (!(carte.isOccupied(x, y)) && carte.nbOccupiedNeighboor(x, y) < 4) {
				carte.setOccupation(x, y, listOfObstacles[i]);
			} else {
				i--;
			}
		}
	}
}
