package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso04.ejercicio028.backend.repository.CategoriaRepository;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;

    @Override
	public Categoria aniadirCategoria(Categoria categoria) {
        return categoriaRepository.add(categoria);
	}

    @Override
    public Categoria modificarCategoria(Categoria categoria) {
    	return categoriaRepository.update(categoria);
    }
   
    @Override
    public void borrarCategoria(Long id) {
    	Categoria categoriaABorrar = obtenerCategoria(id);
        categoriaRepository.delete( categoriaABorrar);
    }

    @Override
    public Categoria obtenerCategoria(Long id) {
        return categoriaRepository.read(id);
    }

    @Override
    public List<Categoria> listarCategoria() {
        return categoriaRepository.list();
    }

}