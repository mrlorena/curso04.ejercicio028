package es.cic.curso.curso04.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.cic.curso.curso04.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "ESTILO")
public class Estilo implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8085438588627987895L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NOMBRE_ESTILO")
	private String nombreEstilo;

	public Estilo() {
		super();
	}

	public Estilo(String nombreEstilo) {
		super();
		this.nombreEstilo = nombreEstilo;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEstilo() {
		return nombreEstilo;
	}

	public void setNombreEstilo(String nombreEstilo) {
		this.nombreEstilo = nombreEstilo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Estilo other = (Estilo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estilo [id=" + id + ", nombreEstilo=" + nombreEstilo + "]";
	}

}
