package es.cic.curso.curso04.ejercicio028.backend.dto;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;


@Component
public class TipoConverter {
	
	public TipoDTO entity2DTO(Tipo tipo) {
		
		TipoDTO resultado = new TipoDTO();
		resultado.setNombreTipo(tipo.getNombreTipo());
		return resultado;
		
	}
	public Tipo dto2Entity(TipoDTO tipoDTO) {
		Tipo resultado = new Tipo();
		resultado.setNombreTipo(tipoDTO.getNombreTipo());
		return resultado;		
	}
	
	
	
}
