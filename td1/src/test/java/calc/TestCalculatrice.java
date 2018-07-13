package calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestCalculatrice {
	int operande1;
	int operande2;
	int resultatAttendu;

	public TestCalculatrice(int operande1, int operande2, int resultatAttendu) {
		this.operande1 = operande1;
		this.operande2 = operande2;
		this.resultatAttendu = resultatAttendu;
	}

	@Test
	public void testAddition() {
		Calculatrice c = new Calculatrice();

		int result = c.addition(5, 4);
		assertEquals("Addition", 9, result);
	}

	@Test
	public void testSoustraction() {
		Calculatrice c = new Calculatrice();
		int result = c.soustraction(10, 1);
		assertEquals("Soustraction", 9, result);
	}

	@Test
	public void testMultiplication() {
		Calculatrice c = new Calculatrice();
		int result = c.multiplication(5, 4);
		assertEquals("Multiplication", 20, result);
	}

	@Test
	public void testDivision() {
		Calculatrice c = new Calculatrice();
		int result = c.division(operande1, operande2);
		assertEquals("Division", resultatAttendu, result);
	}

	@Test
	public void testTab() {
		int result = 0;
		int tab[] = new int[10];
		for (int i = 1; i < 11; i++) {
			tab[i - 1] = i;
			result = result + tab[i - 1];
		}

		assertEquals("Tab", 55, result);
	}

	@Test
	public void testEnum() {
		String str = Operateur.addition.getOperateur();
		assertEquals("Enum", "plus", str);
	}

	@Parameters
	public static Collection valeurs(){
	Object tableau[][] = new Object[][] {
		{2,1,2},
		{4,2,2},
		{6,2,3}
	};
	return Arrays.asList(tableau);
	}

}
