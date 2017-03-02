package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.springframework.stereotype.Repository;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;




@Repository
public class CategoriaRepositoryImpl extends AbstractRepositoryImpl <Long, Categoria> implements CategoriaRepository {

	@Override
    public Class<Categoria> getClassDeT() {
        return Categoria.class;
    }

	@Override
    public String getNombreTabla() {
        return "CATEGORIA";
    }
	
	
}
