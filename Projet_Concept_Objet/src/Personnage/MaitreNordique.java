package Personnage;

public class MaitreNordique {
	
private static MaitreNordique lUnique;
	
	private MaitreNordique() {};
	
	public static MaitreNordique getInstance() {
		if(lUnique==null) {
			lUnique = new MaitreNordique();
		}
		return lUnique;
	}
}
