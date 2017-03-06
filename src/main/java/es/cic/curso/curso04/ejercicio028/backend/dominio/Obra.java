package es.cic.curso.curso04.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.cic.curso.curso04.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name = "OBRA")
public class Obra implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6777173737846819360L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TITULO")
	private String titulo;

	@JoinColumn(name = "AUTOR_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Autor autor;

	@Column(name = "ANIO")
	private int anio;

	@OneToOne
	@JoinColumn(name = "TIPO_ID")
	private Tipo tipo;

	@OneToOne
	@JoinColumn(name = "ESTILO_ID")
	private Estilo estilo;

	@Column(name = "HABILITADA")
	private boolean habilitada;

	@Column(name = "IMAGEN")
	private String imagen;

	public Obra() {
		super();
	}

	public Obra(String titulo, Autor autor, int anio, Tipo tipo, Estilo estilo, boolean habilitada, String imagen) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.tipo = tipo;
		this.estilo = estilo;
		this.habilitada = habilitada;
		this.imagen = imagen;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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
		Obra other = (Obra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + ", tipo=" + tipo
				+ ", estilo=" + estilo + ", precio=" + habilitada + ", imagen=" + imagen + "]";
	}

}
