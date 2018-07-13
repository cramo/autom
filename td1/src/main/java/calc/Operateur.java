package calc;

public enum Operateur {

	addition("plus"), soustraction("moins"), multiplication("étoile"), division("slash");

	private String operateur;

	Operateur(String op) {
		this.operateur = op;
	}

	public String getOperateur() {
		return this.operateur;
	}

	public String getNomOperateur() {
		switch (this.operateur) {
		case "plus":
			return Operateur.addition.getOperateur();
		case "moins":
			return Operateur.soustraction.getOperateur();
		case "étoile":
			return Operateur.multiplication.getOperateur();
		case "slash":
			return Operateur.division.getOperateur();
			default : return "operateur inconnu";
		}
	}
}
