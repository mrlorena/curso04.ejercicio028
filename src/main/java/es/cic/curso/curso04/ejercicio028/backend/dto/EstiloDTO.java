package es.cic.curso.curso04.ejercicio028.backend.dto;



public class EstiloDTO {
    Long id;
    String nombreEstilo;
    
	public EstiloDTO() {
		super();
	}
	
	public EstiloDTO(String nombreEstilo) {
		super();
		this.nombreEstilo = nombreEstilo;
		
	}
	

	public String getNombreEstilo() {
		return nombreEstilo;
	}

	public void setNombreEstilo (String nombreEstilo) {
		this.nombreEstilo = nombreEstilo;
	}

	@Override
	public String toString() {
		return "EstiloDTO [id=" + id + ", nombreEstilo=" + nombreEstilo + "]";
	}

}