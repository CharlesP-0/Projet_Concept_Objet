package Main;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Carte.Carte;
import Carte.SafeZone;
import Personnage.Khajit;
import Personnage.MaitreOrc;
import Personnage.Nordique;
import Personnage.Obstacle;

public class Simulation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("je suis un projet java");
		MaitreOrc.getInstance();
		// Test de la carte
		int quantity = 50;
		Obstacle[] listObstacles = null;
		Carte carte = new Carte(20, 20);
		Khajit test = new Khajit();
		Nordique test2 = new Nordique();
		carte.setCaseAsSafeZone(0, 0, 4, 4, SafeZone.FORTDHIVER);
		// TimeUnit.SECONDS.sleep(1);
		carte.setOccupation(5, 3, test);
		test.setPositionX(5);
		test.setPositionY(3);
		carte.setOccupation(3, 3, test2);
		test2.setPositionX(3);
		test2.setPositionY(3);
		// TimeUnit.SECONDS.sleep(1);
		carte.isOccupied(5, 3);
		carte.isOccupied(15, 13);
		// TimeUnit.SECONDS.sleep(1);
		carte.isOccupied(13, 3);
		// TimeUnit.SECONDS.sleep(1);
		carte.isASafeZone(1, 1);
		// TimeUnit.SECONDS.sleep(1);
		carte.isASafeZone(7, 12);
		// TimeUnit.SECONDS.sleep(1);
		generateObstacle(quantity, carte, listObstacles);
		test.setTarget(carte);
		List<String> directions = test.pathFinding(test.getTarget(), carte, test.getMaxMovement());
		System.out.println(test.getTarget().getPositionX());
		System.out.println("Path finding size : " + directions.size());
		for (int i = 0; i<directions.size(); i++) {
			System.out.println(directions.get(i));
		}
		
		carte.displayMap();
		test.move(directions, carte);
		carte.displayMap();
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
