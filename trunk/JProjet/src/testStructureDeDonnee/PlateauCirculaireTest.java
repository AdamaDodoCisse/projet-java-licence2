package testStructureDeDonnee;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import structureDeDonnee.PlateauCirculaire;

public class PlateauCirculaireTest {
	protected PlateauCirculaire plateaucirculaire;
	@Before
	public void setUp() throws Exception {
		plateaucirculaire = new PlateauCirculaire("Dossier_Teste/VAISSEAU.LIF");
	}

	@Test
	public void testEvoluer() {
		assertFalse(plateaucirculaire.estVide());
		for(int i=0;i<100;i++){
			plateaucirculaire.evoluer();
			assertEquals(plateaucirculaire.getTailleCelluleVivante(), 5);
		}
	}

	@Test
	public void testPlateauCirculaire() {
		assertFalse(plateaucirculaire.estVide());
		assertNotNull(plateaucirculaire);
		assertEquals(plateaucirculaire.getTailleCelluleVivante(),5);
	}

}
