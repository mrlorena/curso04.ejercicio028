package es.cic.curso.curso04.ejercicio028.backend.dto;

public class TipoDTO {
	Long id;
	String nombreTipo;

	public TipoDTO() {
		super();
	}

	public TipoDTO(String nombreTipo) {
		super();
		this.nombreTipo = nombreTipo;

	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	@Override
	public String toString() {
		return "TipoDTO [id=" + id + ", nombreTipo=" + nombreTipo + "]";
	}

}