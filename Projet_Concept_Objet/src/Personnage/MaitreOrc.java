package Personnage;

public class MaitreOrc {
	
	private static MaitreOrc lUnique;
	
	private MaitreOrc() {};
	
	public static MaitreOrc getInstance() {
		if(lUnique==null) {
			lUnique = new MaitreOrc();
		}
		return lUnique;
	}
}
