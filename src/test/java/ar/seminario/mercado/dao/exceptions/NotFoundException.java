package ar.seminario.mercado.dao.exceptions;

import ar.seminario.mercado.repository.ObjectId;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1433066451502113060L;
	
	private Class<?> clazz;
	private ObjectId id;

	public NotFoundException(Class<?> clazz, ObjectId id) {
		this.clazz = clazz;
		this.id = id;
	}
	@Override
	public String getMessage() {
		if (this.clazz == null)
			return super.getMessage();
		else {
			StringBuilder sb = new StringBuilder();
			sb.append(this.clazz).append(" with Id ").append(this.id).append(" Not Found!");
			return sb.append(super.getMessage()).toString();
		}
	}
}
