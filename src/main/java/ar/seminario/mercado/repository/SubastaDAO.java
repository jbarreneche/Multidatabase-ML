package ar.seminario.mercado.repository;

import ar.seminario.mercado.Subasta;

public class SubastaDAO {

	public Subasta save(Subasta nuevaSubasta) {
		ObjectId id = new ObjectId();
		nuevaSubasta.setId(id);
		return nuevaSubasta;
	}

}
