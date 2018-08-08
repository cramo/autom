package exo3;

public class Person {
	// Les noms correspondent aux noms du tableau de la feature
	private String firstName;
	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public int getAge() {
		return age;
	}

	private String surname;
	private int age;

	public Person(String nom, String prenom, int age) {
		this.firstName = nom;
		this.surname = prenom;
		this.age = age;
	}
}
