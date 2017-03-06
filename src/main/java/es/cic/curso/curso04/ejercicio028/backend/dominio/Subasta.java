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
@Table(name="SUBASTA")
public class Subasta implements Identificable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8160750506943903063L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="OBRA_ID")
	private Obra obra;
	
	@Column(name="PUJA_INICIAL")
	private double pujaInicial;
	
	@Column(name="PRECIO_VENTA")
	private double precioVenta;
	
	@Column(name="FECHA_INICIO")
	private String fechaInicio;
	
	@Column(name="FECHA_FIN")
	private String fechaFin;
	
	@Column(name="ACTIVA")
	private boolean activa;
	

	
	public Subasta() {
		super();
	}



	public Subasta(Obra obra, double pujaInicial, double precioVenta, String fechaInicio, String fechaFin, boolean activa) {
		super();
		this.obra = obra;
		this.pujaInicial = pujaInicial;
		this.precioVenta = precioVenta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.activa = activa;
	}


	@Override
	public Long getId() {
		return id;
	}


	@Override
	public void setId(Long id) {
		this.id = id;
	}



	public Obra getObra() {
		return obra;
	}



	public void setObra(Obra obra) {
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
		Subasta other = (Subasta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Subasta [id=" + id + ", obra=" + obra + ", pujaInicial=" + pujaInicial + ", precioVenta=" + precioVenta
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", activa=" + activa + "]";
	}
	
	
	
}
