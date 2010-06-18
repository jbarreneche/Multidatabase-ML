package ar.seminario.mercado;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SubastaTestCase {
	private Subasta unaSubasta;
	@Before
	public void crearSubasta() {
		unaSubasta = new Subasta();
	}
	@Test
	public void nuevaSubasta() throws Exception {
		assertTrue(unaSubasta.getOfertas().isEmpty());
		assertFalse(unaSubasta.isFinalizada());
	}
	@Test
	public void recibirPrimerOferta() throws Exception {
		Oferta unaOferta = new Oferta(unaSubasta, 2.0);
		unaSubasta.recibirOferta(unaOferta);
		assertEquals(unaSubasta.getOfertas().size(), 1);
		assertTrue(unaSubasta.getOfertas().contains(unaOferta));
		assertEquals(unaSubasta.getMejorOferta(), unaOferta);
	}
	@Test
	public void recibirVariasOfertas() throws Exception {
		Oferta unaOferta = new Oferta(unaSubasta, 2.0);
		unaSubasta.recibirOferta(unaOferta);
		Oferta otraOferta = new Oferta(unaSubasta, 4.0);
		unaSubasta.recibirOferta(otraOferta);
		assertEquals(unaSubasta.getOfertas().size(), 2);
		assertTrue(unaSubasta.getOfertas().contains(unaOferta));
		assertTrue(unaSubasta.getOfertas().contains(otraOferta));
		assertEquals(unaSubasta.getMejorOferta(), otraOferta);
	}
	@Test
	public void testname() throws Exception {
		
	}
}
