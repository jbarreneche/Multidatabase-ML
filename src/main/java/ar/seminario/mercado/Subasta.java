package ar.seminario.mercado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.seminario.mercado.repository.ObjectId;

public class Subasta {

	private List<Oferta> ofertas = new ArrayList<Oferta>();
	private ObjectId id;
	public Collection<Oferta> getOfertas() {
		return this.ofertas;
	}

	public boolean isFinalizada() {
		// TODO Auto-generated method stub
		return false;
	}

	public void recibirOferta(Oferta unaOferta) {
		this.ofertas.add(0, unaOferta);
	}

	public Oferta getMejorOferta() {
		return this.ofertas.get(0);
	}

	public ObjectId getId() {
		return this.id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
}
