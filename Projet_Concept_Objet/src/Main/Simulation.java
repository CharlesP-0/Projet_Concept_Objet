package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Carte.Carte;
import Carte.SafeZone;
import Message.Message;
import Personnage.Khajit;
import Personnage.MaitreKhajit;
import Personnage.MaitreNordique;
import Personnage.MaitreOrc;
import Personnage.Nordique;
import Personnage.Obstacle;

public class Simulation {

	public static void main(String[] args) throws InterruptedException {
		boolean won = false;
		MaitreOrc.getInstance();
		Message[] messageKhajit = new Message[4];
		messageKhajit[0] = new Message("Test 1", 1);
		messageKhajit[1] = new Message("Test 2", 1);
		messageKhajit[2] = new Message("Test 3", 1);
		messageKhajit[3] = new Message("Test 4", 1);

		Message[] messagenordique = new Message[4];
		messagenordique[0] = new Message("Nordique 1", 1);
		messagenordique[1] = new Message("Nordique 2", 1);
		messagenordique[2] = new Message("Nordique 3", 1);
		messagenordique[3] = new Message("Nordique 4", 1);
		int quantity = 50;
		Obstacle[] listObstacles = null;
		Carte carte = new Carte(20, 20);
		MaitreKhajit maitreKhajit = MaitreKhajit.getInstance();
		MaitreNordique maitreNordique = MaitreNordique.getInstance();
		Khajit test = new Khajit("Jzargo", messageKhajit, maitreKhajit);
		Nordique test2 = new Nordique("Lidya", messagenordique, maitreNordique);
		carte.setCaseAsSafeZone(0, 0, 4, 4, SafeZone.FORTDHIVER);
		carte.setOccupation(0, 0, maitreKhajit);
		maitreKhajit.setPositionX(0);
		maitreKhajit.setPositionY(0);
		carte.setOccupation(1, 1, maitreNordique);
		maitreNordique.setPositionX(1);
		maitreNordique.setPositionY(1);
		carte.setOccupation(5, 3, test);
		test.setPositionX(5);
		test.setPositionY(3);
		test.setPointAction(100);
		carte.setOccupation(13, 13, test2);
		System.out.println("Test 2 set");
		test2.setPositionX(3);
		test2.setPositionY(3);
		carte.isOccupied(5, 3);
		carte.isOccupied(15, 13);
		carte.isOccupied(13, 3);
		carte.isASafeZone(1, 1);
		carte.isASafeZone(7, 12);
		generateObstacle(quantity, carte, listObstacles);
		while (!won) {
			test.setTarget(carte);
			System.out.println(test.getTarget());
			TimeUnit.SECONDS.sleep(1);
			List<String> directions = test.pathFinding(test.getTarget(), carte, test.getMaxMovement());
			carte.displayMap();
			test.move(carte);
			carte.displayMap();
			if (maitreNordique.valeur >= 4) {
				won = true;
			} else if (maitreKhajit.valeur >= 4) {
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
