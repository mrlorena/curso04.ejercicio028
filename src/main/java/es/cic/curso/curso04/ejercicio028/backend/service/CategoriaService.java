package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;

public interface CategoriaService {

	Categoria aniadirCategoria(Categoria categoria);

    void borrarCategoria(Long id);
    
    Categoria modificarCategoria(Categoria categoria);

    Categoria obtenerCategoria(Long id);

    List<Categoria> listarCategoria();

	
}
