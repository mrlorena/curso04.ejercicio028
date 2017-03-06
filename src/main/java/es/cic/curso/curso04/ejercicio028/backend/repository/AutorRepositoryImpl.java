package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;

@Repository
public class AutorRepositoryImpl extends AbstractRepositoryImpl<Long, Autor> implements AutorRepository {

	@Override
	public Class<Autor> getClassDeT() {
		return Autor.class;
	}

	@Override
	public String getNombreTabla() {
		return "AUTOR";
	}

}
