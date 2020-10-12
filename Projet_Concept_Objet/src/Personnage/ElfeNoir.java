package Personnage;

import java.util.List;

public class ElfeNoir extends Personnage{
	private String name;
	private int pointAction = 10;
	private List<String> messages;
	private String lastDirection;

	public void incrPointAction(ElfeNoir elfeNoir) {
		elfeNoir.pointAction ++;
	}
}
