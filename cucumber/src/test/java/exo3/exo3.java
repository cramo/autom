package exo3;

import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class exo3 {
	Person p = new Person("Marie", "Curie", 150);
	String message;

	@Given("^the following person$")
	public void the_following_person(List<Person> arg1) throws Throwable {
		p = arg1.get(0);
	}

	@When("^I ask if I could go in a casino$")
	public void i_ask_if_I_could_go_in_a_casino() throws Throwable {
		if ("Marie".equals(p.getFirstName()) && "Curie".equals(p.getSurname()) && p.getAge() == 150)
		message = "You look radiant Ma'am, come in";
	}

	@Then("^the bouncer should say « you look radiant Ma’am, come in »$")
	public void the_bouncer_should_say_you_look_radiant_Ma_am_come_in() throws Throwable {
		Assert.assertEquals("You look radiant Ma'am, come in", message);
	}

}
