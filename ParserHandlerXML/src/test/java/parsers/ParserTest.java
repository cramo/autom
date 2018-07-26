package parsers;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.Assert;

public class ParserTest {

	@Test
	public void FirstTest() throws Exception
	{
		ParserDOM parser = new ParserDOM("src/main/resources/Exercice6.xml");
		System.out.println(parser.toString());
		assertEquals("Paul Pappel", parser.trouverPateintParDateDeNaissance("19551217"));
		assertEquals("Paul Pappel", parser.trouverPateintParDateDeNaissanceV2("19551217"));
		System.out.println(parser.trouverPateintParDateDeNaissance("19551217"));
		parser.ecrireLeDocumentDansUnfichier("target/");
		/*ParserDOM parserDOM = new ParserDOM("");
		parserDOM.creerParser();*/
		//parser.afficherToutLesNoeuds();
	}
}
