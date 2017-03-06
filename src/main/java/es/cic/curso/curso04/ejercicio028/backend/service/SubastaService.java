package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;

public interface SubastaService {

	Subasta aniadirSubasta(Subasta subasta);

	List<Subasta> listarSubasta();

	Subasta obtenerSubasta(Long id);

	void borrarSubasta(Long id);

	Subasta modificarSubasta(Subasta subasta);

}
