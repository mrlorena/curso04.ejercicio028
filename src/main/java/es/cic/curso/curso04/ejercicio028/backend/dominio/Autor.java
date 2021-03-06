package es.cic.curso.curso04.ejercicio028.backend.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.cic.curso.curso04.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "AUTOR")
public class Autor implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5556422682642816178L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "FECHA_NACIMIENTO")
	private String fechaNacimiento;
	
	@Column(name = "HABILITADO")
	private boolean habilitado;


	@OneToMany(mappedBy = "autor")
	private List<Obra> listaObras = new ArrayList<>();

	public Autor() {
		super();
	}

	public Autor(String nombre, String fechaNacimiento, boolean habilitado) {
		super();
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.habilitado= habilitado;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Obra> getListaObras() {
		return listaObras;
	}

	public void setListaObras(List<Obra> listaObras) {
		this.listaObras = listaObras;
	}
	
	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", habilitado="
				+ habilitado + ", listaObras=" + listaObras + "]";
	}
	

}
