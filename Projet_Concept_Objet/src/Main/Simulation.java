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
		int tour = 0;
		Carte carte = new Carte(20, 20);
		Maitre winner = null;
		
		
		
		
		MaitreKhajit maitreKhajit = MaitreKhajit.getInstance();
		MaitreNordique maitreNordique = MaitreNordique.getInstance();
		MaitreOrc maitreOrc = MaitreOrc.getInstance();
		MaitreElfeNoir maitreElfeNoir = MaitreElfeNoir.getInstance();
		
		
		
		
		
		
		Khajit testk1 = new Khajit("Do'Kheran", maitreKhajit);
		Khajit testk2 = new Khajit("Harassa-Ko", maitreKhajit);
		Khajit testk3 = new Khajit("Ri'Dar-Jo", maitreKhajit);
		Orcs testo1 = new Orcs("Shazog gro-Dumul", maitreOrc);
		Orcs testo2 = new Orcs("Mazoga gra-Dula", maitreOrc);
		Orcs testo3 = new Orcs("Rogbut gro-Drublog", maitreOrc);
		Nordique testn1 = new Nordique("Lisaa", maitreNordique);
		Nordique testn2 = new Nordique("Narfar", maitreNordique);
		Nordique testn3 = new Nordique("Ulfeidr", maitreNordique);
		ElfeNoir teste1 = new ElfeNoir("Farwil Herandus", maitreElfeNoir);
		ElfeNoir teste2 = new ElfeNoir("Velis Indoran", maitreElfeNoir);
		ElfeNoir teste3 = new ElfeNoir("Falena Kaushminipu", maitreElfeNoir);
		
		
		
		
		
		
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

		Random rand = new Random();
		int quantity = rand.nextInt(150);
		Obstacle[] listObstacles = null;
		generateObstacle(quantity, carte, listObstacles);

		Personnage[] listePersonnage = new Personnage[20];
		listePersonnage[0] = maitreKhajit;
		listePersonnage[1] = maitreNordique;
		listePersonnage[2] = maitreOrc;
		listePersonnage[3] = maitreElfeNoir;
		listePersonnage[4] = testo1;
		listePersonnage[5] = testk1;
		listePersonnage[6] = testn1;
		listePersonnage[7] = teste1;
		listePersonnage[8] = testo2;
		listePersonnage[9] = testk2;
		listePersonnage[10] = testn2;
		listePersonnage[11] = teste2;
		listePersonnage[12] = testo3;
		listePersonnage[13] = testk3;
		listePersonnage[14] = testn3;
		listePersonnage[15] = teste3;
		while (!won && tour != 200) {
			tour++;
			for (Personnage personnage : listePersonnage) {
				if (personnage == null) {
					continue;
				}
				if (!(personnage instanceof Maitre)) {
					System.out.println("C'est à " + personnage.toString());
					personnage.setTarget(carte);
					personnage.move(carte);
				}
			}
			if (maitreNordique.valeur >= 12) {
				won = true;
				winner = maitreNordique;
			} else if (maitreKhajit.valeur >= 12) {
				won = true;
				winner = maitreKhajit;
			} else if (maitreOrc.valeur >= 12) {
				won = true;
				winner = maitreOrc;
			} else if (maitreElfeNoir.valeur >= 12) {
				won = true;
				winner = maitreElfeNoir;
			}
			System.out.println();
			System.out.println();
			System.out.println("Fit du tour : " + tour);
			System.out.println();
			System.out.println();
			for (Personnage personnage : listePersonnage) {
				if (personnage == null) {
					continue;
				}
				if (!(personnage instanceof Maitre)) {
					System.out.println(personnage.toString() + " est en [" + personnage.getPositionX() + ","
							+ personnage.getPositionY() + "], a "+ personnage.getPointAction()+" actions restantes et cible " + personnage.getTarget() + " avec un total de "
							+ personnage.getValeur() + " points.");

				} else {
					System.out.println(personnage.toString() + " a "+ personnage.getValeur());
				}
			}
			TimeUnit.SECONDS.sleep(10);
		}

		if (winner != null) {
			System.out.println("The game is won by " + winner);
		} else {
			System.out.println("No winner because we are at " + tour);
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
