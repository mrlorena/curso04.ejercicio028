package es.cic.curso.curso04.ejercicio028.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;

@Component
public class ObraConverter {

	private TipoConverter tipoConverter;
	private EstiloConverter estiloConverter;
	private AutorConverter autorConverter;

	public ObraDTO entityToDto(Obra obra, Autor autor) {
		ObraDTO resultado = new ObraDTO();
		resultado.setTitulo(obra.getTitulo());
		resultado.setAutor(autor.getNombre());
		resultado.setAnio(obra.getAnio());
		resultado.setTipo(obra.getTipo().getNombreTipo());
		resultado.setEstilo(obra.getEstilo().getNombreEstilo());
		resultado.setHabilitada(obra.isHabilitada());
		resultado.setImagen(obra.getImagen());

		return resultado;

	}

	public Obra dto2Entity(ObraDTO obraDTO, TipoDTO tipoDTO, EstiloDTO estiloDTO, AutorDTO autorDTO) {
		Obra resultado = new Obra();
		resultado.setTitulo(obraDTO.getTitulo());
		resultado.setAutor(autorConverter.dto2Entity(autorDTO));
		resultado.setAnio(obraDTO.getAnio());
		resultado.setTipo(tipoConverter.dto2Entity(tipoDTO));
		resultado.setEstilo(estiloConverter.dto2Entity(estiloDTO));
		resultado.setHabilitada(obraDTO.isHabilitada());
		resultado.setImagen(obraDTO.getImagen());

		return resultado;
	}

	public List<ObraDTO> entity2DTO(List<Obra> obras, List<Autor> autores) {
		List<ObraDTO> resultado = new ArrayList<>();

		for (Obra obra : obras) {
			for (Autor autor : autores) {

				resultado.add(entityToDto(obra, autor));
				break;
			}

		}
		return resultado;
	}

	public List<Obra> dto2Entity(List<ObraDTO> obrasDTO, List<TipoDTO> tiposDTO, List<EstiloDTO> estilosDTO,
			List<AutorDTO> autoresDTO) {
		List<Obra> resultado = new ArrayList<>();
		for (ObraDTO obra : obrasDTO) {
			for (TipoDTO tipo : tiposDTO) {
				for (EstiloDTO estilo : estilosDTO) {
					for (AutorDTO autor : autoresDTO) {
						resultado.add(dto2Entity(obra, tipo, estilo, autor));
					}
				}
			}
		}
		return resultado;
	}

}
