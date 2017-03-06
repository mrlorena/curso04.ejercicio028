package es.cic.curso.curso04.ejercicio028.backend.dto;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;

@Component
public class EstiloConverter {

	public EstiloDTO entity2DTO(Estilo estilo) {

		EstiloDTO resultado = new EstiloDTO();
		resultado.setNombreEstilo(estilo.getNombreEstilo());
		return resultado;

	}

	public Estilo dto2Entity(EstiloDTO estiloDTO) {
		Estilo resultado = new Estilo();
		resultado.setNombreEstilo(estiloDTO.getNombreEstilo());
		return resultado;
	}

}
