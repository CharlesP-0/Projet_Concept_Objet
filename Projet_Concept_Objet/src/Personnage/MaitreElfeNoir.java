package Personnage;

public class MaitreElfeNoir {
private static MaitreElfeNoir lUnique;
	
	private MaitreElfeNoir() {};
	
	public static MaitreElfeNoir getInstance() {
		if(lUnique==null) {
			lUnique = new MaitreElfeNoir();
		}
		return lUnique;
	}
}
