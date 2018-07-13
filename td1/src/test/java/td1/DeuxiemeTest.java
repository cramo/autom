package td1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;;

public class DeuxiemeTest {
	@Test
	public void monTestPassant() {
		int result = 5 + 4;
		assertEquals("addition", 9, result);
	}
	
	/*@Test
	public void monTestNonPassant() {
		int result = 5 + 4;
		assertEquals("addition", 10, result);
	}*/
	
	
	@Test
	public void monTestTrim() {
		String str = "   bonjour   ";
		assertEquals("testBonjourTrim", "bonjour",str.trim());
	}
	
	@Test
	public void monTestLenght() {
		String str = "bonjour";
		assertEquals("testBonjourTrim", 7, str.length());
	}
	
	@Test
	public void monTestLenght2() {
		String str = "bonjour";
		int i = str.length();
		boolean b = i > 2;
		assertEquals("testBonjourTrim", true, b);
	}
	
	@Test
	public void monTestLenght3() {
		assertTrue("bonjour".length() > 2);
	}
	
	@Test
	public void monTestSubstring() {
		String str = "bonjour";
		str = str.substring(3);
		System.out.println(str);
		assertEquals("testBonjourSub", "jour", str);
	}
}
