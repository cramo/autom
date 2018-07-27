package parsers;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

public class ParserTest {

	@Ignore
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
	
	@Test
	public void SaxTest() throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance(); 
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(getClass().getResource("/Exercice6.xml").getFile(), new HandlerSax());
		
		/**
		 * Hypotheses : 
		 * Given, family et birthtime sont obligatoires
		 * Given & family précèdent birthtime
		 * Si vous ne faites pas ces hypothèses, il faut contrôler qu'on a bien tous ces élements au sein d'un même patient quel que soit l'ordre
		 */
		//objectif : retourner le patient avec la date de naissance souhaitée ( par exemple : 19551217 )
	}
	
}
