package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.ScacchiModel;
import model.punto;
import rules.Alfiere;
import rules.Cavallo;
import rules.Donna;
import rules.Pedina;
import rules.Pedone;
import rules.Re;
import rules.Torre;
import view.ScacchiView;

public class ScacchiControllerTest {
	ScacchiModel m = new ScacchiModel();
	ScacchiController c = new ScacchiController(m,null);
	@Test
		public void testScaccoT() {
		m.initialize();
		m.set(6,4,null);
		m.set(5, 4, new Donna(0, m));
		assertTrue(c.scacco(1));

	}
	@Test
	public void testScaccoF() {
		
		m.initialize();
		assertFalse(c.scacco(1));

	}
	@Test
	public void testScaccoMattoT() {
		
		m.initialize();
		m.set(0, 3, null);
		m.set(1, 4, null);
		m.set(3, 4, new Pedone(0,m));
		m.set(4, 6, new Pedone(1,m));
		m.set(5, 5, new Pedone(1,m));
		m.set(4, 7, new Donna(0, m));
		m.set(6, 6, null);
		m.set(6, 5, null);
		assertTrue(c.scaccoMatto(1));

	}
	@Test
	public void testScaccoMattoF() {
		
		m.initialize();
		m.set(0, 3, null);
		m.set(1, 4, null);
		m.set(3, 4, new Pedone(0,m));
		m.set(4, 6, new Pedone(1,m));
		m.set(5, 5, new Pedone(1,m));
		m.set(4, 7, new Donna(0, m));
		m.set(6, 6, null);
		m.set(6, 5, null);
		assertFalse(c.scaccoMatto(0));

	}
	
	
}
