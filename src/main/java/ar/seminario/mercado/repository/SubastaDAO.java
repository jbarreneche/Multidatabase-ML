package ar.seminario.mercado.repository;

import org.ektorp.CouchDbConnector;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.support.CouchDbRepositorySupport;

import ar.seminario.mercado.Subasta;
import ar.seminario.mercado.dao.exceptions.NotFoundException;

public class SubastaDAO extends CouchDbRepositorySupport<Subasta> {
	
	public SubastaDAO(CouchDbConnector db) {
		super(Subasta.class, db);
	}

	public Subasta store(Subasta nuevaSubasta) {
		this.add(nuevaSubasta);
		return nuevaSubasta;
	}

	public Subasta find(ObjectId id) throws NotFoundException {
		try {
			return this.get(id.getDocumentId());
		} catch (DocumentNotFoundException dnfe) {
			throw new NotFoundException(Subasta.class, id);
		}
	}
}
