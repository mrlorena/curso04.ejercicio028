package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.estilo;

public interface TipoService {

	estilo aniadirTipo(estilo tipo);

	List<estilo> listarTipo();

	estilo obtenerTipo(Long id);

	void borrarTipo(Long id);

	estilo modificarTipo(estilo tipo);

}
