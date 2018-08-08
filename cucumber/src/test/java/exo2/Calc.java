package exo2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Calc {
	private List<Integer> data = new ArrayList<Integer>();
	private int result = 0;

	@Given("^I have entered (\\d+) into the calculator$")
	public void i_have_entered_into_the_calculator(int arg1) throws Throwable {
		data.add(arg1);
	}

	@When("^I press add$")
	public void i_press_add() throws Throwable {
		result = 0;
		for (Iterator<Integer> i = data.iterator(); i.hasNext();) {
			result = result + i.next();
			System.out.println(result);
		}
	}

	@Then("^the result should be (\\d+) on the screen$")
	public void the_result_should_be_on_the_screen(int arg1) throws Throwable {
		System.out.println("result = " + result);
		Assert.assertEquals(120, result);
	}

}
