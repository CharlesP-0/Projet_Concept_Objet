package Personnage;

import java.util.List;
import java.util.Random;

import Message.Message;

public abstract class Personnage extends Entity{
	private String name;
	private int pointAction;
	private Message[] messages;
	private List<Message> messagesReceived;
	private String lastDirection;
	private int alliance;
	boolean isOutOfAP; 
	
	public Personnage() {
		
	}
	
	public void move() {
	}
	
	public void meet(Personnage personnage) {
		//confrontation between the two
		boolean sameAlliance = this.alliance==personnage.getAlliance();
		if(sameAlliance) {
			this.takeMsgFrom(personnage, sameAlliance);
			personnage.takeMsgFrom(this, sameAlliance);
		}
		else {
			if(this.pointAction > personnage.getPointAction()) {
				this.takeMsgFrom(personnage, sameAlliance);
			}
			else {
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

	
	
}
