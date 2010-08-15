package ar.seminario.mercado.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.seminario.mercado.Subasta;
import ar.seminario.mercado.dao.exceptions.NotFoundException;

public class SubastaDAO {
	
	private Map<ObjectId, Subasta> subastas;
	public SubastaDAO() {
		this.subastas = new HashMap<ObjectId, Subasta>();
	}

	public Subasta store(Subasta nuevaSubasta) {
		ObjectId id = new ObjectId();
		nuevaSubasta.setId(id);
		this.subastas.put(id, nuevaSubasta);
		return nuevaSubasta;
	}

	public Subasta find(ObjectId id) throws NotFoundException {
		if (!this.subastas.containsKey(id)) {
			throw new NotFoundException(Subasta.class, id);
		}
		return this.subastas.get(id);
	}

	public Collection<Subasta> getAll() {
		return this.subastas.values();
	}

	public void remove(Subasta nuevaSubasta) {
		this.subastas.remove(nuevaSubasta.getId());
	}

}
