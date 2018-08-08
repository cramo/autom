package cucumber;

public class Metier {

	public int addition(int a, int b) {
		return a+b;
	}
	
	public String accueil(String prenom, String nom, int age) {
		
		if("Marie".equals(prenom) && "Curie".equals(nom) && 150 == age) {
			return "you look radiant Ma’am, come in";
		}
		
		return "hello";
	}
	
	public int salaireEnKiloEuro(String name) {
		
		switch(name) {
		case "bob" : return 35; 
		case "bill" : return 50; 
		}
		
		return 0;
	}
}
