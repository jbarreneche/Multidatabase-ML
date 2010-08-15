package ar.seminario.mercado;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import ar.seminario.mercado.repository.ObjectId;


@JsonWriteNullProperties(false)
public abstract class Persistent {
	private String documentId;
	private String revision;

	@JsonIgnore
	public ObjectId getId() {
		if (documentId == null)
			return null;
			
		return new ObjectId(this.getDocumentId());
	}
	public void setId(ObjectId id) {
		this.setDocumentId(id.getDocumentId());
	}
	@JsonProperty("_id")
	private void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	@JsonProperty("_id")
	private String getDocumentId() {
		return documentId;
	}
	@JsonProperty("_rev")
	public void setRevision(String revision) {
		this.revision = revision;
	}
	@JsonProperty("_rev")
	public String getRevision() {
		return revision;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((documentId == null) ? 0 : documentId.hashCode());
		result = prime * result
				+ ((revision == null) ? 0 : revision.hashCode());
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
		Persistent other = (Persistent) obj;
		if (documentId == null) {
			if (other.documentId != null)
				return false;
		} else if (!documentId.equals(other.documentId))
			return false;
		if (revision == null) {
			if (other.revision != null)
				return false;
		} else if (!revision.equals(other.revision))
			return false;
		return true;
	}
}
