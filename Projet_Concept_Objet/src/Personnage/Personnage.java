package Personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Carte.Carte;
import Carte.SafeZone;
import Message.Message;

public abstract class Personnage extends Entity {
	private String name;
	private SafeZone safezone;
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
	protected Maitre maitre;
	private int maxMovement;

	public Personnage() {
		this.maxMovement = 15;
	}

	public String getName() {
		return name;
	}

	public SafeZone getSafezone() {
		return safezone;
	}

	public void setSafezone(SafeZone safezone) {
		this.safezone = safezone;
	}

	public int getMaxMovement() {
		return maxMovement;
	}

	public void setMaxMovement(int maxMovement) {
		this.maxMovement = maxMovement;
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

	public Personnage getPreviousTarget() {
		return previousTarget;
	}

	public void setPreviousTarget(Personnage previousTarget) {
		this.previousTarget = previousTarget;
	}

	public void move(Carte carte) throws InterruptedException {
		int x;
		int y;
		List<String> directions = pathFinding(this, carte, this.maxMovement);
		for (String direction : directions) {
			x = directionToIndexX(this.positionX, direction);
			y = directionToIndexY(this.positionY, direction);
			if (carte.isOccupied(x, y)) {
				if (carte.getOccupation(x, y).getClass() != this.getClass()
						&& (carte.getOccupation(x, y) instanceof Personnage)) {
					// this.meet((Personnage) carte.getOccupation(x, y), carte);
					System.out.println("Meet");
					break;
				}
			} else {
				carte.freeOccupation(this.positionX, this.positionY);
				carte.setOccupation(x, y, this);
				this.positionX = x;
				this.positionY = y;
				this.lastDirection = direction;
				carte.displayMap();
				TimeUnit.SECONDS.sleep(1);
			}
		}
	}

	public void meet(Personnage personnage, Carte carte) {
		// confrontation between the two
		boolean sameAlliance = this.alliance == personnage.getAlliance();
		if (sameAlliance) {
			this.takeMsgFrom(personnage);
			personnage.takeMsgFrom(this);
		} else {
			if (this.pointAction > personnage.getPointAction()) {
				this.takeMsgFrom(personnage);
			} else {
				personnage.takeMsgFrom(this);
			}
			this.setTarget(carte);
			personnage.setPreviousTarget(this);
			this.pointAction -= 10;
		}
	}

	public void takeMsgFrom(Personnage peronnage) {
		Message messageReceived = giveMsg();
		this.messagesReceived.add(messageReceived);

	}

	public Message giveMsg() {
		Random gen = new Random();
		return this.messages[gen.nextInt(this.messages.length)];
	}

	public void setTarget(Carte carte) {
		float distance = (carte.getNbColonne() * carte.getNbLigne()) + 1;
		this.previousTarget = this.target;
		if (this.pointAction > 20) {
			for (int i = 0; i < carte.getNbLigne(); i++) {
				for (int j = 0; j < carte.getNbColonne(); j++) {
					if (carte.isOccupied(i, j) && carte.getOccupation(i, j).getClass() != this.getClass()
							&& (carte.getOccupation(i, j) instanceof Personnage) && !(carte.getOccupation(i, j) instanceof Maitre)
					 && carte.getOccupation(i, j) != previousTarget ) {
						/*
						 * On vérifie que l'entité qui occupe la case n'est pas un personnage de la même
						 * race (et par la même occasion, qu'il ne se prenne pas lui-même pour cible) ou
						 * un personnage qu'il a déjà rencontré précédemment
						 */
						if (distance(this.positionX, this.positionY, i, j) < distance) {
							this.target = (Personnage) carte.getOccupation(i, j);
						}
					}
				}
			}
		} else {
			this.target = maitre;
		}
	}

	/**
	 * Method to determine the path to follow to go to another character. The path
	 * may not be the most optimized path
	 * 
	 * @param target The character that we want to go to
	 * @param carte  The map that the character will move through
	 * @return Return the list of directions that the character must follow to go to
	 *         the target
	 * @throws InterruptedException
	 */
	public List<String> pathFinding(Personnage target, Carte carte, int maxMovement) {
		List<String> directions = new ArrayList<String>();
		int targetX = target.getPositionX();
		int targetY = target.getPositionY();
		int currentX = this.getPositionX();
		int currentY = this.getPositionY();
		int testX = currentX;
		int testY = currentY;
		if (currentX != targetX || currentY != targetY) {
			directions.add(path(currentX, currentY, targetX, targetY, carte));
			for (int i = 0; i < maxMovement; i++) {
				testX = directionToIndexX(testX, directions.get(i));
				testY = directionToIndexY(testY, directions.get(i));
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
		int distance;
		int coordoneeX = 0;
		int coordoneeY = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (currentX + i < 0 || currentX + i > (carte.getNbColonne() - 1) || currentY + j < 0
						|| currentY + j > (carte.getNbLigne() - 1) || (carte.isASafeZone(currentX + i, currentY + j)
								&& carte.getSafeZone(currentX + i, currentY + j) != this.getSafezone())) {
					continue;
				} else {
					if ((carte.isOccupied(currentX + i, currentY + j)
							&& carte.getOccupation(currentX + i, currentY + j) != this.target)) {
						continue;
					} else if (i != 0 || j != 0) {
						distance = distance(currentX + i, currentY + j, targetX, targetY);
						if (distance < min) {
							// Dans le cas où on a une distance égale, il est possible que l'une des
							// propostions conduise vers un chemin plus court.
							// On pourrait vérifier le chemin plus court en testant la distance restante du
							// déplacement suivant pour chacunes des propositions.
							// La partie mise en commentaire est une tentative de résolution de ce problème.
							min = distance;
							coordoneeX = i;
							coordoneeY = j;
							/*
							 * } else if (distance == min) { int distance1 =
							 * distanceNext(currentX+coordoneeX, currentY+coordoneeY, targetX, targetY,
							 * carte)[0]; int distance2 = distanceNext(currentX+i, currentY+j, targetX,
							 * targetY, carte)[0]; if (distance2<distance1) { min = distance2; coordoneeX =
							 * i; coordoneeY = j; }
							 */
						}
					}
				}
			}
		}
		return chooseDirection(coordoneeX, coordoneeY);
	}

	/*
	 * public int[] distanceNext(int x, int y, int targetX, int targetY, Carte
	 * carte) { int distance; int min = 999; int coordonneesX = 0; int coordonneesY
	 * = 0; for (int i = -1; i < 2; i++) { for (int j = -1; j < 2; j++) { if (x + i
	 * < 0 || x + i > (carte.getNbColonne() - 1) || y + j < 0 || y + j >
	 * (carte.getNbLigne() - 1) || (carte.isASafeZone(x + i, y + j) &&
	 * carte.getSafeZone(x + i, y + j) != this.getSafezone())) { continue; } else if
	 * (i != 0 || j != 0) { distance = distance(x + i, y + j, targetX, targetY); if
	 * (distance < min) { min = distance; coordonneesX = x+i; coordonneesY = y+j; }
	 * else if (distance == min) { int[] test1 = distanceNext(coordonneesX,
	 * coordonneesY, targetX, targetY, carte); int[] test2 = distanceNext(x+i, y+j,
	 * targetX, targetY, carte); int distance1 = test1[0]; int distance2 = test2[0];
	 * if(distance1 > distance2) { min = distance2; coordonneesX = x+i; coordonneesY
	 * = y+j; } } } } } int[] results = new int[] {min, coordonneesX, coordonneesY};
	 * return results; }
	 */

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
		int distance = (int) Math.sqrt(Math.pow((targetX - currentX), 2) + Math.pow(targetY - currentY, 2));
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
