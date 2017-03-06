package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;

public interface TipoService {

	Tipo aniadirTipo(Tipo tipo);

	List<Tipo> listarTipo();

	Tipo obtenerTipo(Long id);

	void borrarTipo(Long id);

	Tipo modificarTipo(Tipo tipo);

	

}
