package es.cic.curso.curso04.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.cic.curso.curso04.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name="TIPO")
public class Tipo implements Identificable<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 636697323429057481L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="NOMBRE_TIPO")
	private String nombreTipo;
	
	
	public Tipo() {
		super();
	}

	public Tipo(String nombreTipo) {
		super();
		this.nombreTipo = nombreTipo;
	}

	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
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
		Tipo other = (Tipo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tipo [id=" + id + ", nombreTipo=" + nombreTipo + "]";
	}

	
}
