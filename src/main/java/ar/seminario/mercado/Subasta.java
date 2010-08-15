package ar.seminario.mercado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Subasta extends Persistent {

	private List<Oferta> ofertas = new ArrayList<Oferta>();

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

}
