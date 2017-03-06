package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;

@Repository
public class ObraRepositoryImpl extends AbstractRepositoryImpl<Long, Obra> implements ObraRepository {

	@Override
	public Class<Obra> getClassDeT() {
		return Obra.class;
	}

	@Override
	public String getNombreTabla() {
		return "OBRA";
	}

}
