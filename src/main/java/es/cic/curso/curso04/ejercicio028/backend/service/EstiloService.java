package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;

public interface EstiloService {

	Estilo aniadirEstilo(Estilo estilo);

    void borrarEstilo(Long id);
    
    Estilo modificarEstilo(Estilo estilo);

    Estilo obtenerEstilo(Long id);

    List<Estilo> listarEstilo();

	void generaBBDD();

	
}
