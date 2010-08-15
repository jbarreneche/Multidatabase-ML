package ar.seminario.mercado.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.seminario.mercado.Subasta;

public class SubastaDAO {
	
	private Map<ObjectId, Subasta> subastas;
	public SubastaDAO() {
		this.subastas = new HashMap<ObjectId, Subasta>();
	}

	public Subasta save(Subasta nuevaSubasta) {
		ObjectId id = new ObjectId();
		nuevaSubasta.setId(id);
		this.subastas.put(id, nuevaSubasta);
		return nuevaSubasta;
	}

	public Subasta find(ObjectId id) {
		return this.subastas.get(id);
	}

	public Collection<Subasta> getAll() {
		return this.subastas.values();
	}

}
