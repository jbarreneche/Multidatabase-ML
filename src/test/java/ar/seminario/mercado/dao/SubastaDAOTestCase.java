package ar.seminario.mercado.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.seminario.mercado.Subasta;
import ar.seminario.mercado.repository.SubastaDAO;


public class SubastaDAOTestCase {
	private SubastaDAO dao;

	@Before
	public void crearSubastaDAO() {
		dao = new SubastaDAO();
	}
	@Test
	public void guardando() throws Exception {
		Subasta nuevaSubasta = new Subasta();
		dao.save(nuevaSubasta);
		assertNotNull(nuevaSubasta.getId());
	}
	@Test
	public void recuperando() throws Exception {
		Subasta nuevaSubasta = new Subasta();
		dao.save(nuevaSubasta);
		assertEquals(nuevaSubasta, dao.find(nuevaSubasta.getId()));
	}
	@Test
	public void recuperandoTodos() throws Exception {
		assertNotNull(dao.getAll());
		assertEquals(0, dao.getAll().size());
		Subasta subasta1 = new Subasta();
		Subasta subasta2 = new Subasta();
		dao.save(subasta1);
		dao.save(subasta2);
		assertEquals(2, dao.getAll().size());
		assertTrue(dao.getAll().contains(subasta1));
		assertTrue(dao.getAll().contains(subasta2));
	}
}
