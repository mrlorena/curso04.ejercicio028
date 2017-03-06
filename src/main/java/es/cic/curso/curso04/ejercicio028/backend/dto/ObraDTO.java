package es.cic.curso.curso04.ejercicio028.backend.dto;

public class ObraDTO {
	private String titulo;
	private String autor;
	private int anio;
	private String tipo;
	private String estilo;
	private boolean habilitada;
	private String imagen;
	
	public ObraDTO() {
		super();
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
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
	public String toString() {
		return "ObraDTO [titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + ", tipo=" + tipo + ", estilo="
				+ estilo + ", precio=" + habilitada + ", imagen=" + imagen + "]";
	}


	
	
}
