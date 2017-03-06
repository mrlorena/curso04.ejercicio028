package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;


@Repository
public class SubastaRepositoryImpl extends AbstractRepositoryImpl <Long, Subasta> implements SubastaRepository {

	@Override
    public Class<Subasta> getClassDeT() {
        return Subasta.class;
    }

	@Override
    public String getNombreTabla() {
        return "SUBASTA";
    }
	
	
}
