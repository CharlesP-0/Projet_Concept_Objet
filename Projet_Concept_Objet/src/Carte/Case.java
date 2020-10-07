package Carte;

import Personnage.Entity;

public class Case {
	private boolean isOccupied = false;
	private boolean isASafeZone = false;
	private SafeZone safeZone;
	private Entity isOccupiedBy;

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public boolean isASafeZone() {
		return isASafeZone;
	}

	public void setASafeZone(boolean isASafeZone) {
		this.isASafeZone = isASafeZone;
	}

	public SafeZone getSafeZone() {
		return safeZone;
	}

	public void setSafeZone(SafeZone safeZone) {
		this.safeZone = safeZone;
	}

	public Entity getIsOccupiedBy() {
		return isOccupiedBy;
	}

	public void setIsOccupiedBy(Entity isOccupiedBy) {
		this.isOccupiedBy = isOccupiedBy;
	}

}
