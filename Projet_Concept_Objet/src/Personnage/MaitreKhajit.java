package Personnage;

public class MaitreKhajit {

private static MaitreKhajit lUnique;
	
	private MaitreKhajit() {};
	
	public static MaitreKhajit getInstance() {
		if(lUnique==null) {
			lUnique = new MaitreKhajit();
		}
		return lUnique;
	}
}
