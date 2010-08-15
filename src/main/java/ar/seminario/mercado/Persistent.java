package ar.seminario.mercado;

import ar.seminario.mercado.repository.ObjectId;

public abstract class Persistent {
	private ObjectId id;
	public ObjectId getId() {
		return this.id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}

}
