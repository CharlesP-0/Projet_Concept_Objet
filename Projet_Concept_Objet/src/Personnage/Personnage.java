package Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Carte.Carte;
import Message.Message;

public abstract class Personnage extends Entity {
	private String name;
	private int pointAction;
	private Message[] messages;
	private List<Message> messagesReceived;
	private String lastDirection;
	private int alliance;
	private boolean isOutOfAP;
	private int positionX;
	private int positionY;
	private Personnage target;
	private Personnage previousTarget;

	public Personnage() {
	}

	public void move() {
	}

	public void meet(Personnage personnage) {
		// confrontation between the two
		boolean sameAlliance = this.alliance == personnage.getAlliance();
		if (sameAlliance) {
			this.takeMsgFrom(personnage, sameAlliance);
			personnage.takeMsgFrom(this, sameAlliance);
		} else {
			if (this.pointAction > personnage.getPointAction()) {
				this.takeMsgFrom(personnage, sameAlliance);
			} else {
				personnage.takeMsgFrom(this, sameAlliance);
			}
		}
	}

	public void takeMsgFrom(Personnage peronnage, boolean sameAlliance) {
		Message messageReceived = giveMsg();
		this.messagesReceived.add(messageReceived);

	}

	public Message giveMsg() {
		Random gen = new Random();
		return this.messages[gen.nextInt(this.messages.length)];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointAction() {
		return pointAction;
	}

	public void setPointAction(int pointAction) {
		this.pointAction = pointAction;
	}

	public Message[] getMessages() {
		return messages;
	}

	public void setMessages(Message[] messages) {
		this.messages = messages;
	}

	public List<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(List<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	public String getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(String lastDirection) {
		this.lastDirection = lastDirection;
	}

	public int getAlliance() {
		return alliance;
	}

	public void setAlliance(int alliance) {
		this.alliance = alliance;
	}

	public boolean isOutOfAP() {
		return isOutOfAP;
	}

	public void setOutOfAP(boolean isOutOfAP) {
		this.isOutOfAP = isOutOfAP;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public Personnage getTarget() {
		return target;
	}

	public void setTarget(Carte carte) {
		float distance = (carte.getNbColonne() * carte.getNbLigne()) + 1;
		this.previousTarget = this.target;
		for (int i = 0; i < carte.getNbLigne(); i++) {
			for (int j = 0; j < carte.getNbColonne(); j++) {
				if (carte.isOccupied(i, j) && carte.getOccupation(i, j).getClass() != this.getClass()
						&& (carte.getOccupation(i, j) instanceof Personnage)
						&& carte.getOccupation(i, j) != previousTarget) {
					if (distance(this.positionX, this.positionY, i, j) < distance) {
						this.target = (Personnage) carte.getOccupation(i, j);
					}
				}
			}
		}
	}

	/**
	 * Method to determine the path to follow to go to another character. The path may not be the most optimized path
	 * 
	 * @param target The character that we want to go to
	 * @param carte  The map that the character will move through
	 * @return Return the list of directions that the character must follow to go to
	 *         the target
	 * @throws InterruptedException
	 */
	public List<String> pathFinding(Personnage target, Carte carte) throws InterruptedException {
		List<String> directions = new ArrayList<String>();
		int targetX = target.getPositionX();
		int targetY = target.getPositionY();
		int currentX = this.getPositionX();
		int currentY = this.getPositionY();
		int testX = currentX;
		int testY = currentY;
		System.out.println("Je suis à testX : " + testX);
		System.out.println("Je suis à testY : " + testY);
		if (currentX != targetX || currentY != targetY) {
			directions.add(path(currentX, currentY, targetX, targetY, carte));
			for (int i = 0; i < 9; i++) {
				testX = directionToIndexX(testX, directions.get(i));
				System.out.println("TestX : " + testX);
				testY = directionToIndexY(testY, directions.get(i));
				System.out.println("TestY : " + testY);
				directions.add(path(testX, testY, targetX, targetY, carte));
				if (testX == targetX && testY == targetY) {
					break;
				}
			}
		}

		return directions;
	}

	/**
	 * Method that determines the path we must follow step by step
	 * 
	 * @param currentX The X coordinate of the character that must move
	 * @param currentY The Y coordinate of the character that must move
	 * @param targetX  The Y coordinate of the target
	 * @param targetY  The Y coordinate of the target
	 * @param carte    The map that the character will move through
	 * @return Return the direction that we must follow in order to be as close as
	 *         possible of the target
	 */
	public String path(int currentX, int currentY, int targetX, int targetY, Carte carte) {
		int min = (carte.getNbColonne() * carte.getNbLigne()) + 1;
		System.out.println("min : " + min);
		int distance;
		int coordoneeX = 0;
		int coordoneeY = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (currentX + i < 0 || currentX + i > (carte.getNbColonne() - 1) || currentY + j < 0
						|| currentY + j > (carte.getNbLigne() - 1)) {
					continue;
				} else {
					if ((carte.isOccupied(currentX + i, currentY + j)
							&& carte.getOccupation(currentX + i, currentY + j) != this.target)) {
						continue;
					} else if (i != 0 || j != 0) {
						distance = distance(currentX + i, currentY + j, targetX, targetY);
						System.out.println("Distance : " + distance);
						System.out.println("Path i : " + i);
						System.out.println("path j : " + j);
						System.out.println(distance < min);
						if (distance < min) {
							min = distance;
							System.out.println("Min boucle : " + min);
							System.out.println(min);
							coordoneeX = i;
							coordoneeY = j;
							System.out.println();
							System.out.println("X : " + coordoneeX);
							System.out.println("Y : " + coordoneeY);
						}
					}
				}
			}
		}
		System.out.println();
		System.out.println("i : " + coordoneeX);
		System.out.println("j : " + coordoneeY);
		System.out.println("CurrentX + i " + (currentX + coordoneeX));
		System.out.println("CurrentY + j " + (currentY + coordoneeY));
		System.out.println(chooseDirection(coordoneeX, coordoneeY));
		return chooseDirection(coordoneeX, coordoneeY);
	}

	/**
	 * Method to calculate the distance between two characters
	 * 
	 * @param currentX The X coordinate of the character that must move
	 * @param currentY The Y coordinate of the character that must move
	 * @param targetX  The Y coordinate of the target
	 * @param targetY  The Y coordinate of the target
	 * @return The distance between the two characters
	 */
	public int distance(int currentX, int currentY, int targetX, int targetY) {
		int distance = Math.abs(targetX - currentX) + Math.abs(targetY - currentY);
		return distance;
	}

	/**
	 * Method to choose the direction depending on the value of two integers
	 * 
	 * @param i The X coordinates
	 * @param j The Y coordinates
	 * @return return the direction that must be followed
	 */
	public String chooseDirection(int i, int j) {
		if (i == -1) {
			if (j == -1) {
				return "NO";
			} else if (j == 0) {
				return "O";
			} else {
				return "SO";
			}
		} else if (i == 0) {
			if (j == -1) {
				return "N";
			} else if (j == 0) {
				return "";
			} else {
				return "S";
			}
		} else {
			if (j == -1) {
				return "NE";
			} else if (j == 0) {
				return "E";
			} else {
				return "SE";
			}
		}
	}

	public int directionToIndexX(int currentX, String direction) {
		int indexX = 0;
		if (direction == "SO" || direction == "NO" || direction == "O") {
			indexX = -1;
		} else if (direction == "SE" || direction == "NE" || direction == "E") {
			indexX = 1;
		}
		return (currentX + indexX);
	}

	public int directionToIndexY(int currentY, String direction) {
		int indexY = 0;
		if (direction == "SO" || direction == "SE" || direction == "S") {
			indexY = 1;
		} else if (direction == "NO" || direction == "NE" || direction == "N") {
			indexY = -1;
		}
		return (currentY + indexY);
	}

}
