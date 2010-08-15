package ar.seminario.mercado.dao;

import static org.junit.Assert.*;

import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.junit.Before;
import org.junit.Test;

import ar.seminario.mercado.Subasta;
import ar.seminario.mercado.dao.exceptions.NotFoundException;
import ar.seminario.mercado.repository.ObjectId;
import ar.seminario.mercado.repository.SubastaDAO;


public class SubastaDAOTestCase {
	private SubastaDAO dao;

	@Before
	public void crearSubastaDAO() {
		HttpClient client = new StdHttpClient.Builder().host("localhost").port(5984).build();
		StdCouchDbInstance dbInstance = new StdCouchDbInstance(client);
		dbInstance.deleteDatabase("test_ml");
		StdCouchDbConnector db = new StdCouchDbConnector("test_ml", dbInstance);
		dao = new SubastaDAO(db);
	}
	@Test
	public void guardando() throws Exception {
		Subasta nuevaSubasta = new Subasta();
		dao.store(nuevaSubasta);
		assertNotNull(nuevaSubasta.getId());
	}
	@Test
	public void recuperando() throws Exception {
		Subasta nuevaSubasta = new Subasta();
		dao.store(nuevaSubasta);
		assertEquals(nuevaSubasta, dao.find(nuevaSubasta.getId()));
	}
	@Test
	public void recuperandoInexistente() throws Exception {
		try {
			dao.find(new ObjectId("23048023948"));
			fail("Exception not raised :(");
		} catch(NotFoundException nfe) {
			// OK :)
		}
	}
	@Test
	public void recuperandoTodos() throws Exception {
		assertNotNull(dao.getAll());
		assertEquals(0, dao.getAll().size());
		Subasta subasta1 = new Subasta();
		Subasta subasta2 = new Subasta();
		dao.store(subasta1);
		dao.store(subasta2);
		assertEquals(2, dao.getAll().size());
		assertTrue(dao.getAll().contains(subasta1));
		assertTrue(dao.getAll().contains(subasta2));
	}
	@Test
	public void eliminando() throws Exception {
		Subasta nuevaSubasta = new Subasta();
		dao.store(nuevaSubasta);
		dao.remove(nuevaSubasta);
			assertEquals(0, dao.getAll().size());
		try {
			dao.find(nuevaSubasta.getId());
			fail("No exception thrown");
		} catch(NotFoundException nfe) {
			// OK :)
		}
	}
}
