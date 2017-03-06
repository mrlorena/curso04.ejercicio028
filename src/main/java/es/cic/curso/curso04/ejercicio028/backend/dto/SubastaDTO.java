package es.cic.curso.curso04.ejercicio028.backend.dto;

public class SubastaDTO {

	private String obra;
	private double pujaInicial;
	private double precioVenta;
	private String fechaInicio;
	private String fechaFin;
	private boolean activa;

	public SubastaDTO() {
		super();
	}

	public String getObra() {
		return obra;
	}

	public void setObra(String obra) {
		this.obra = obra;
	}

	public double getPujaInicial() {
		return pujaInicial;
	}

	public void setPujaInicial(double pujaInicial) {
		this.pujaInicial = pujaInicial;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@Override
	public String toString() {
		return "SubastaDTO [obra=" + obra + ", pujaInicial=" + pujaInicial + ", precioVenta=" + precioVenta
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", activa=" + activa + "]";
	}

}
