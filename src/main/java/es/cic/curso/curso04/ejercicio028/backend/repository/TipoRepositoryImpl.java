package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;

@Repository
public class TipoRepositoryImpl extends AbstractRepositoryImpl<Long, Tipo> implements TipoRepository {

	@Override
	public Class<Tipo> getClassDeT() {
		return Tipo.class;
	}

	@Override
	public String getNombreTabla() {
		return "TIPO";
	}

}
