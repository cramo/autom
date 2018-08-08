package exo4;

import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class exo4 {
	private String salaire;

	@Given("^the gender of the employee$")
	public void the_gender_of_the_employee() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^it is a (.*)$")
	public void it_is_a_(String arg1) throws Throwable {
		if ("bob".equals(arg1))
		salaire = "35k€";
		if ("bill".equals(arg1))
			salaire = "50k€";
	}

	@Then("^the salary should be (\\d+)k€$")
	public void the_salary_should_be_k€(int arg1) throws Throwable {
		Assert.assertEquals(arg1, salaire);
	}

}
