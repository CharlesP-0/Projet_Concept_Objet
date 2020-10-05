package Personnage;

import java.util.List;

public abstract class Personnage {
	private String name;
	private int pointAction;
	private List<String> messages;
	private String lastDirection;
	boolean isOutOfAP; 
	
	public void move() {
	}
	
	public void meet() {
	}
	
	public void takeMsg() {	
	}
	
	public void giveMsg() {
	}
}
