package parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author formation
 *
 */
public class ParserDOM {

	/**
	 * Document représentant le fichier à lire
	 */
	private Document xmlDocument;

	private String fichier;

	/**
	 * Permet d'initialiser un parseur DOM sur le fichier passé en paramètre
	 * 
	 * @param fichier
	 *            XML à lire
	 * @throws Exception
	 */
	public ParserDOM(String file) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		this.fichier = file;
		this.xmlDocument = builder.parse(new File(file));
	}

	/**
	 * 
	 * Permet de retouner le prénom et nom du patient d'après sa date de naissance
	 * et retourne null si aucun patient ne correspond
	 * 
	 * @param dateDeNaissance
	 *            du patient au format AAAAMMJJ
	 * @return prénom et nom du patient
	 * @throws Exception
	 */
	public String trouverPateintParDateDeNaissance(String dateDeNaissance) throws Exception {
		// Construire une expression Xpath en Java
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile("//birthtime[@value='" + dateDeNaissance + "']/../../name");

		// Appliquer un Xpath à mon document
		Object result = expr.evaluate(this.xmlDocument, XPathConstants.NODE);
		NodeList node = (NodeList) result;

		XPathExpression expr2 = xpath.compile("given");
		Object prenom = (String) expr2.evaluate(node, XPathConstants.STRING);
		XPathExpression expr3 = xpath.compile("family");
		Object nom = (String) expr3.evaluate(node, XPathConstants.STRING);
		return prenom + " " + nom;
	}

	/**
	 * 
	 * Permet de retouner le prénom et nom du patient d'après sa date de naissance
	 * et retourne null si aucun patient ne correspond
	 * 
	 * @param dateDeNaissance
	 *            du patient au format AAAAMMJJ
	 * @return prénom et nom du patient
	 * @throws Exception
	 */
	public String trouverPateintParDateDeNaissanceV2(String dateDeNaissance) throws Exception {
		// Construire une expression Xpath en Java
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile("//birthtime[@value='" + dateDeNaissance + "']/../../name");

		// Appliquer un Xpath à mon document
		Object result = expr.evaluate(this.xmlDocument, XPathConstants.NODE);
		Node node = (Node) result;
		// node.insertBefore(node, node);
		NodeList enfants = node.getChildNodes();
		System.out.println(enfants.getLength());
		String nom = "", prenom = "";
		for (int i = 0; i < enfants.getLength(); i++) {
			System.out.println(i + ": " + enfants.item(i).getTextContent());
			if ("given".equals(enfants.item(i).getLocalName())) {
				prenom = enfants.item(i).getTextContent();
			}
			if ("family".equals(enfants.item(i).getLocalName())) {
				nom = enfants.item(i).getTextContent();
			}
		}
		this.insererDeuxiemePrenom("Jacques", node);
		return prenom + " " + nom;
	}

	private void insererDeuxiemePrenom(String prenom, Node name) {
		NodeList enfants = name.getChildNodes();
		Node reference = null;
		for (int i = 0; i < enfants.getLength(); i++) {
			if ("family".equals(enfants.item(i).getLocalName())) {
				reference = enfants.item(i);
			}
		}
		if(reference != null) {
			Node nouveau = this.xmlDocument.createElement("given2");
			Node attribut = this.xmlDocument.createAttribute("toto");
			name.insertBefore(nouveau, reference);
			nouveau.setTextContent(prenom);
			nouveau.getAttributes().setNamedItem(attribut);
			attribut.setTextContent("tits");
		}
	}

	public void ecrireLeDocumentDansUnfichier(String nomFichier) throws Exception {
		TransformerFactory transformFactory = TransformerFactory.newInstance();
		Transformer transformer = transformFactory.newTransformer();
		DOMSource source = new DOMSource(this.xmlDocument);
		StreamResult result = new StreamResult(new File(nomFichier));
		transformer.transform(source, result);
	}

	public void creerParser() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new File(getClass().getResource("/Exercice6.xml").getFile()));
		// document.getDocumentElement();
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		XPathExpression expr = xpath.compile("//*");
		// XPathExpression expr =
		// xpath.compile("//administrativegendercode/birthtime[@value='19551217']/../../name/family");
		Object result = expr.evaluate(document, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		/*
		 * for(int i = 0, i < nodes.item()) {
		 */
		System.out.println(nodes.item(0).getTextContent());
		/*
		 * System.out.println(nodes.item(0).getNodeName().toString());
		 * System.out.println(nodes.item(1).getNodeName().toString());
		 * System.out.println(nodes.item(2).getNodeName().toString());
		 */
		// }
	}
	
	private void afficherToutLesNoeuds() {
		affichertoutLesNoeudsRec(this.xmlDocument);
	}

	private void affichertoutLesNoeudsRec(Node noeud){
		System.out.println(noeud.getNodeName() + "contenu: " + noeud.getNodeValue());
		
		
	}
	
	public String toString() {
		return this.fichier;
	}
}
