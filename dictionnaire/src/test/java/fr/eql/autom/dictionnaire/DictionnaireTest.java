package fr.eql.autom.dictionnaire;

import static org.junit.Assert.*;

import java.awt.List;
import java.rmi.UnexpectedException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Categories;

import fr.eql.autom.dictionnaire.exceptions.CategorieNonSupporteeException;
import fr.eql.autom.dictionnaire.exceptions.EntreeInexistanteException;
import fr.eql.autom.dictionnaire.exceptions.ProprieteDupliqueeException;
import fr.eql.autom.dictionnaire.exceptions.ProprieteObligatoireIndefinieException;
import fr.eql.autom.modele.proprietes.Categorie;
import fr.eql.autom.modele.proprietes.Genre;
import fr.eql.autom.modele.entrees.Entree;
import fr.eql.autom.modele.entrees.EntreeNominale;
import fr.eql.autom.modele.mots.Mot;
import fr.eql.autom.modele.proprietes.*;

public class DictionnaireTest {

	@Test
	public void testAjouterEntree1() {
		Dictionnaire d = new Dictionnaire();
		try {
			Boolean bool = d.ajouterEntree("aller", Categorie.VERBE);
			assertTrue(bool);
		} catch (CategorieNonSupporteeException e) {
			System.out.println("nop");
			e.printStackTrace();
		}
		// bonus
		assertEquals("entree effectivment crée dans la map", 1, d.entrees.size());
		Entree entree = d.entrees.get("aller");
		String identite = entree.getIdentite();
		String texteDeLaCategorie = entree.getCategorie().getValeur();
		assertEquals("entree creee avec la bonne identité", "aller", identite);
		assertEquals("entree creee avec la bonne categorie", "VERBE", texteDeLaCategorie);

	}

	@Test(expected = CategorieNonSupporteeException.class)
	public void testAjouterEntree2() throws CategorieNonSupporteeException {
		Dictionnaire d = new Dictionnaire();
		Boolean bool = d.ajouterEntree("pouet", Categorie.NOM);
		assertFalse(bool);
	}

	@Test
	public void testAjouterEntree3() throws CategorieNonSupporteeException {
		Dictionnaire d = new Dictionnaire();
		d.ajouterEntree("pouet", Categorie.VERBE);
		Boolean bool = d.ajouterEntree("pouet", Categorie.VERBE);
		assertFalse(bool);
	}

	@Test
	public void testAjouterEntree4() throws CategorieNonSupporteeException {
		Dictionnaire d = new Dictionnaire();
		Boolean bool = d.ajouterEntree("pouet", Categorie.VERBE, Genre.F);
		assertTrue(bool);
	}

	@Test
	public void testAjouterEntree5() throws CategorieNonSupporteeException {
		Dictionnaire d = new Dictionnaire();
		Boolean bool = d.ajouterEntree("pouet", Categorie.NOM, Genre.F);
		assertTrue(bool);

		assertEquals("entree cree dans map", 1, d.entrees.size());
		Entree entree = d.entrees.get("pouet");

		if (entree instanceof EntreeNominale) {
			EntreeNominale entreeNominale = (EntreeNominale) entree;
			String identite = entreeNominale.getIdentite();
			String texteDeLaCategorie = entreeNominale.getCategorie().getValeur();
			String genre = entreeNominale.getGenre().getValeur();
			assertEquals("entre creee avec la bonne identité", "pouet", identite);
			assertEquals("entre creee avec la bonne categorie", "NOM", texteDeLaCategorie);
			assertEquals("entre creee avec le bon genre", "F", genre);
		} else
			fail();
	}

	@Test
	public void testAjouterEntree6() throws CategorieNonSupporteeException {
		Dictionnaire d = new Dictionnaire();
		d.ajouterEntree("pouet", Categorie.NOM, Genre.F);
		Boolean bool = d.ajouterEntree("pouet", Categorie.NOM, Genre.F);
		assertFalse(bool);
	}

	@Test
	public void testAjouterEntree7()
			throws CategorieNonSupporteeException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();

		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		Boolean bool = d.ajouterEntree("pouet", p);
		assertTrue(bool);

	}

	@Test
	public void testAjouterEntree8()
			throws CategorieNonSupporteeException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();

		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		d.ajouterEntree("pouet", p);
		Boolean bool = d.ajouterEntree("pouet", p);
		assertFalse(bool);
	}

	@Test(expected = ProprieteObligatoireIndefinieException.class)
	public void testAjouterEntree9()
			throws CategorieNonSupporteeException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();

		Set<IPropriete> p = new HashSet<>();
		// p.add(Categorie.ADJ);
		// p.add(Genre.F);
		d.ajouterEntree("pouet", p);
	}

	@Test(expected = ProprieteDupliqueeException.class)
	public void testAjouterEntree10()
			throws CategorieNonSupporteeException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();

		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Categorie.ADJ);
		p.add(Categorie.ADJ);
		p.add(Categorie.ADJ);
		p.add(Categorie.NOM);
		p.add(Categorie.NOM);
		p.add(Categorie.NOM);
		// seulement deux entités car on a un set, donc
		d.ajouterEntree("pouet", p);
	}

	@Test
	public void testAjouterEntree11()
			throws CategorieNonSupporteeException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();

		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Genre.F);
		Boolean b = d.ajouterEntree("pouet", p);
		assertTrue(b);
	}

	@Test
	public void testAjouterMot12()
			throws EntreeInexistanteException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();
		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		Boolean b = d.ajouterMot("test", "test2", p);
		assertTrue(b);
	}

	@Test(expected = EntreeInexistanteException.class)
	public void testAjouterMot13()
			throws EntreeInexistanteException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();
		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		// d.ajouterEntree("test", p);
		Boolean b = d.ajouterMot("test", "test2", p);
		assertTrue(b);
	}

	@Test(expected = ProprieteObligatoireIndefinieException.class)
	public void testAjouterMot14()
			throws EntreeInexistanteException, ProprieteObligatoireIndefinieException, ProprieteDupliqueeException {
		Dictionnaire d = new Dictionnaire();
		Set<IPropriete> p = new HashSet<>();
		p.add(Genre.F);
		d.ajouterEntree("test", p);
		d.ajouterMot("test", "test2", p);
	}

	@Ignore // (expected = ProprieteDupliqueeException.class)
	public void testAjouterMot15()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		d.ajouterMot("test", "test2", p);
	}

	@Test
	public void testAjouterMot16()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Genre.F);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		boolean b = d.ajouterMot("test", "test2", p);
		assertTrue(b);
	}

	@Test
	public void testTrouverMotAssocie17()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<Mot> m = new HashSet<>();
		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		d.ajouterMot("test", "testjuihihu", p);
		d.ajouterMot("test", "testhyuy", p);
		d.ajouterMot("test", "testuhihhui", p);
		m = d.trouverMotsAssociesAUneEntree("test");
		int i = m.size();
		// String lul = m.iterator().next().getForme();
		System.out.println("iiiiii = " + i);

		// d.mots.get("test2");
		// m.forEach(System.out::println);
		String lul;
		// for (int j = 0; j < m.size() ; j++)
		for (Mot mot : m) {
			lul = m.iterator().next().getForme();
			System.out.println("lul = " + lul);
		}
		/*
		 * for (Mot mot : m ) { System.out.println(mot); }
		 */
		assertEquals("", 2, i);
	}

	@Test
	public void testTrouverMotAssocie18()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<Mot> m, n = new HashSet<>();
		Set<IPropriete> p = new HashSet<>();

		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		/*
		 * d.ajouterMot("test", "test2", p); d.ajouterMot("test", "test3", p);
		 */
		m = d.trouverMotsAssociesAUneEntree("test");
		// m.forEach(System.out::println);
		/*
		 * for (Mot mot : m ) { System.out.println(mot); }
		 */
		// assertArrayEquals("array", [], m);
		assertEquals("", n, m);
		// assertNull(m);
	}

	@Ignore // ?
	public void testTrouverMotAssocie19()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<Mot> m = new HashSet<>();
		Set<IPropriete> p = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		/*
		 * d.ajouterMot("test", "test2", p); d.ajouterMot("test", "test3", p);
		 */
		m = d.trouverMotsAssociesAUneEntree("test2");
		// m.forEach(System.out::println);
		/*
		 * for (Mot mot : m ) { System.out.println(mot); }
		 */
	}

	@Test // ?
	public void testTrouverEntree20()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<Mot> m = new HashSet<>();
		Set<IPropriete> p = new HashSet<>();
		Set<Entree> e = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		d.ajouterMot("test", "forme", p);
		d.ajouterEntree("test2", p);
		d.ajouterMot("test2", "forme", p);
		e = d.trouverEntreesAssocieesAuMot("forme");
		// e.add((Entree) d.trouverEntreesAssocieesAuMot("test2"));
		int i = e.size();
		System.out.println("i = " + i);
		System.out.println(d.mots.get("forme"));
		// e.forEach(System.out::println);
		// assertNotNull(e);
		/*
		 * for (Mot mot : m ) { System.out.println(mot); }
		 */
		assertEquals("", 2, i);
	}

	@Ignore // ?
	public void testTrouverEntree21()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<Mot> m = new HashSet<>();
		Set<IPropriete> p = new HashSet<>();
		Set<Entree> e = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		d.ajouterMot("test", "biche", p);
		e = d.trouverEntreesAssocieesAuMot("biches");
		// assertNull(e);
		e.forEach(System.out::println);

		/*
		 * for (Mot mot : m ) { System.out.println(mot); }
		 */

	}

	@Ignore // ?
	public void testTrouverEntreesAssocieesAuMotParCategorie22()
			throws ProprieteObligatoireIndefinieException, ProprieteDupliqueeException, EntreeInexistanteException {
		Dictionnaire d = new Dictionnaire();
		Set<Mot> m = new HashSet<>();
		Set<IPropriete> p = new HashSet<>();
		Set<Entree> e = new HashSet<>();
		p.add(Categorie.ADJ);
		p.add(Genre.F);
		p.add(Mode.IMP);
		p.add(Personne.PL1);
		p.add(Nombre.PL);
		d.ajouterEntree("test", p);
		d.ajouterMot("test", "biche", p);
		e = d.trouverEntreesAssocieesAuMot("biches");
		// assertNull(e);
		// e.forEach(System.out::println);
		/*
		 * for (Mot mot : m ) { System.out.println(mot); }
		 */

	}

}
