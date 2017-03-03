package es.cic.curso.curso04.ejercicio028.backend.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.cic.curso.curso04.ejercicio028.backend.repository.Identificable;

@Entity
@Table(name="OBRA")
public class Obra implements Identificable<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="TITULO")
	private String titulo;
	
	@Column(name="AUTOR")
	private String autor;
	
	@Column(name="ANIO")
	private int anio;
	
	@OneToOne
	@JoinColumn(name="TIPO_ID")
	private estilo tipo;
	
	@OneToOne
	@JoinColumn(name="ESTILO_ID")
	private Estilo estilo;
	
	@Column(name="PRECIO")
	private double precio;
	
	@Column(name="IMAGEN")
	private String imagen;
	
	
	public Obra() {
		super();
	}
	
	public Obra(String titulo, String autor, int anio, estilo tipo, Estilo estilo, double precio, String imagen) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.tipo = tipo;
		this.estilo = estilo;
		this.precio = precio;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public estilo getTipo() {
		return tipo;
	}

	public void setTipo(estilo tipo) {
		this.tipo = tipo;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	
}
