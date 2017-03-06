package es.cic.curso.curso04.ejercicio028.backend.dto;

import org.springframework.stereotype.Component;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;

@Component
public class AutorConverter {

	public AutorDTO entity2DTO(Autor autor) {

		AutorDTO resultado = new AutorDTO();
		resultado.setNombre(autor.getNombre());
		resultado.setFechaNacimiento(autor.getFechaNacimiento());
		return resultado;

	}

	public Autor dto2Entity(AutorDTO autorDTO) {
		Autor resultado = new Autor();
		resultado.setNombre(autorDTO.getNombre());
		resultado.setFechaNacimiento(autorDTO.getFechaNacimiento());
		return resultado;
	}

}
