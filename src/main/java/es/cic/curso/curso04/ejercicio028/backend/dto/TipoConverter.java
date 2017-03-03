package es.cic.curso.curso04.ejercicio028.backend.dto;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.estilo;


@Component
public class TipoConverter {
	
	public TipoDTO entity2DTO(estilo tipo) {
		
		TipoDTO resultado = new TipoDTO();
		resultado.setNombreTipo(tipo.getNombreTipo());
		return resultado;
		
	}
	public estilo DTO2Entity(TipoDTO tipoDTO) {
		estilo resultado = new estilo();
		resultado.setNombreTipo(tipoDTO.getNombreTipo());
		return resultado;		
	}
	
	
	
}
