package exo1;

import java.util.ArrayList;
import java.util.List;

import cucumber.Metier;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Exercices {

	List<Integer> parameters = new ArrayList<Integer>();
	int resultat;
	Personne p;
	String message;
	int salaire;
	
	@Given("^I have entered (\\d+) into the calculator$")
	public void i_have_entered_into_the_calculator(int arg1) throws Throwable {
		parameters.add(arg1);
	}

	@When("^I press add$")
	public void i_press_add() throws Throwable {
		Metier metier = new Metier();
		resultat = metier.addition(parameters.get(0), parameters.get(1));
		parameters.clear();
	}

	@Then("^the result should be (\\d+) on the screen$")
	public void the_result_should_be_on_the_screen(int arg1) throws Throwable {
		Assert.assertEquals(arg1,resultat);
	}

	@Given("^the following person$")
	public void the_following_person(List<Personne> arg1) throws Throwable {
	   p = arg1.get(0);
	}

	@When("^I ask if I could go in a casino$")
	public void i_ask_if_I_could_go_in_a_casino() throws Throwable {
	    Metier metier = new Metier();
	    message = metier.accueil(p.firstName, p.surname, p.age);
	}

	@Then("^the bouncer should say « you look radiant Ma’am, come in »$")
	public void the_bouncer_should_say_you_look_radiant_Ma_am_come_in() throws Throwable {
	    Assert.assertEquals("you look radiant Ma’am, come in", message);
	}

	@Given("^the name of the employee$")
	public void the_name_of_the_employee() throws Throwable {
	   ///???????
	}

	@When("^it is a (.*)$")
	public void it_is_a_(String nom) throws Throwable {
	    Metier metier = new Metier();
	    salaire = metier.salaireEnKiloEuro(nom);
	}

	@Then("^the salary should be (\\d+)k€$")
	public void the_salary_should_be_k(int arg1) throws Throwable {
		Assert.assertEquals(arg1, salaire);
	}

}
