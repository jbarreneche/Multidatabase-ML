package ar.seminario.mercado.repository;

public class ObjectId {
	private String documentId;

	public ObjectId(String documentId) {
		this.documentId = documentId;
	}

	public void setDocumentId(String id) {
		this.documentId = id;
	}

	public String getDocumentId() {
		return documentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((documentId == null) ? 0 : documentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectId other = (ObjectId) obj;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		return true;
	}
}
