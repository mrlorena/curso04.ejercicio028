package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;

@Repository
public class EstiloRepositoryImpl extends AbstractRepositoryImpl<Long, Estilo> implements EstiloRepository {

	@Override
	public Class<Estilo> getClassDeT() {
		return Estilo.class;
	}

	@Override
	public String getNombreTabla() {
		return "ESTILO";
	}

}
