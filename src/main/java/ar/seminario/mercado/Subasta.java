package ar.seminario.mercado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Subasta extends Persistent {

	private List<Oferta> ofertas = new ArrayList<Oferta>();

	public Collection<Oferta> getOfertas() {
		return this.ofertas;
	}

	@JsonIgnore
	public boolean isFinalizada() {
		// TODO Auto-generated method stub
		return false;
	}

	public void recibirOferta(Oferta unaOferta) {
		this.ofertas.add(0, unaOferta);
	}

	@JsonIgnore
	public Oferta getMejorOferta() {
		return this.ofertas.get(0);
	}

}
