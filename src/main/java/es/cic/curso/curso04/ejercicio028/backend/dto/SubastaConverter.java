package es.cic.curso.curso04.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;

@Component
public class SubastaConverter {

	public SubastaDTO entityToDto(Subasta subasta, Obra obra) {
		SubastaDTO resultado = new SubastaDTO();
		resultado.setObra(obra.getTitulo());
		resultado.setPujaInicial(subasta.getPujaInicial());
		resultado.setPrecioVenta(subasta.getPrecioVenta());
		resultado.setFechaInicio(subasta.getFechaInicio());
		resultado.setFechaFin(subasta.getFechaFin());
		resultado.setActiva(subasta.isActiva());

		return resultado;

	}

	public List<SubastaDTO> entity2DTO(List<Subasta> subastas, List<Obra> obras) {
		List<SubastaDTO> resultado = new ArrayList<>();

		for (Subasta subasta : subastas) {
			for (Obra obra : obras) {
				resultado.add(entityToDto(subasta, obra));

			}
		}
		return resultado;
	}

}
