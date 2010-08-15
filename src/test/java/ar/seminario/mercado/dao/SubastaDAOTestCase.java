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
}
