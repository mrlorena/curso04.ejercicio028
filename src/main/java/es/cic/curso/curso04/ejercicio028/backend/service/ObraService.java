package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;

public interface ObraService {

	Obra aniadirObra(Obra obra);

	void borrarObra(Long id);

	Obra modificarObra(Obra obra);

	Obra obtenerObra(Long id);

	List<Obra> listarObra();

	void generaBBDD();
}
