package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;

public interface AutorService {

	Autor aniadirAutor(Autor tipo);

	List<Autor> listarAutor();

	Autor obtenerAutor(Long id);

	void borrarAutor(Long id);

	Autor modificarAutor(Autor tipo);

}
