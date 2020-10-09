package Main;

import java.util.concurrent.TimeUnit;

import Carte.Carte;
import Carte.SafeZone;
import Personnage.Khajit;
import Personnage.MaitreOrc;

public class Simulation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("je suis un projet java");
		
		MaitreOrc.getInstance();
		//Test de la carte
		Carte carte = new Carte (20,20);
		Khajit test = new Khajit();
		carte.setCaseAsSafeZone(0,0, 2, 3, SafeZone.EPERVINE);
		//TimeUnit.SECONDS.sleep(1);
		carte.setOccupation(5, 3, test);
		//TimeUnit.SECONDS.sleep(1);
		carte.isOccupied(5, 3);
		//TimeUnit.SECONDS.sleep(1);
		carte.isOccupied (13,3);
		//TimeUnit.SECONDS.sleep(1);
		carte.isASafeZone(1,1);
		//TimeUnit.SECONDS.sleep(1);
		carte.isASafeZone(7, 12);
		//TimeUnit.SECONDS.sleep(1);
		carte.displayMap();
	}

}
