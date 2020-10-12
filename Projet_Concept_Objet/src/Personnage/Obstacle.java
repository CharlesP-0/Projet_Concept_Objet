package Personnage;

public class Obstacle extends Entity {
	int x;
	int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Obstacle( int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() { 
        return String.format("an obstacle"); 
    } 
}
