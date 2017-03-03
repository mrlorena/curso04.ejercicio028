package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.estilo;


@Repository
public class TipoRepositoryImpl extends AbstractRepositoryImpl <Long, estilo> implements TipoRepository {

	@Override
    public Class<estilo> getClassDeT() {
        return estilo.class;
    }

	@Override
    public String getNombreTabla() {
        return "TIPO";
    }
	
	
}
