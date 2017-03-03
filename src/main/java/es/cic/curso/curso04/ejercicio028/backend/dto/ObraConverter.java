package es.cic.curso.curso04.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.estilo;



@Component
public class ObraConverter {
	
	
	private TipoConverter tipoConverter;
	private EstiloConverter estiloConverter;
	
	public ObraDTO entityToDto(Obra obra, estilo tipo, Estilo estilo) {
		ObraDTO resultado = new ObraDTO();
		resultado.setTitulo(obra.getTitulo());
		resultado.setAutor(obra.getAutor());
		resultado.setAnio(obra.getAnio());
		resultado.setTipo(tipo.getNombreTipo());
		resultado.setEstilo(estilo.getNombreEstilo());
		resultado.setPrecio(obra.getPrecio());
		resultado.setImagen(obra.getImagen());
		
		return resultado;
		
	}

	
	public Obra DTO2Entity(ObraDTO obraDTO, TipoDTO tipoDTO, EstiloDTO estiloDTO) {
		Obra resultado = new Obra();
		resultado.setTitulo(obraDTO.getTitulo());
		resultado.setAutor(obraDTO.getAutor());
		resultado.setAnio(obraDTO.getAnio());
		resultado.setTipo(tipoConverter.DTO2Entity(tipoDTO));
		resultado.setEstilo(estiloConverter.DTO2Entity(estiloDTO));
		resultado.setPrecio(obraDTO.getPrecio());
		resultado.setImagen(obraDTO.getImagen());
		
		return resultado;		
	}
	
	public List<ObraDTO> entity2DTO(List<Obra> obras, List<estilo> tipos, List<Estilo> estilos) {
		List<ObraDTO> resultado = new ArrayList<ObraDTO>();
		for(Obra obra: obras) {
			for(estilo tipo : tipos){
				for(Estilo estilo : estilos){
					resultado.add(entityToDto(obra, tipo, estilo));
				}
			}
		}
		return resultado;
	}
	
	public List<Obra> DTO2Entity(List<ObraDTO> obrasDTO, List<TipoDTO> tiposDTO, List<EstiloDTO> estilosDTO) {
		List<Obra> resultado = new ArrayList<Obra>();
		for(ObraDTO obra: obrasDTO) {
			for(TipoDTO tipo : tiposDTO){
				for(EstiloDTO estilo : estilosDTO){
					resultado.add(DTO2Entity(obra, tipo, estilo));
				}
			}
		}
		return resultado;		
	}	

}
