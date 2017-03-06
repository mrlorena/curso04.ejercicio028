package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.repository.AutorRepository;
 

@Service
@Transactional
public class AutorServiceImpl implements AutorService{
	@Autowired
	private AutorRepository autorRepository;

    @Override
	public Autor aniadirAutor(Autor autor) {
        return autorRepository.add(autor);
	}

    @Override
    public Autor modificarAutor(Autor autor) {
    	return autorRepository.update(autor);
    }
   
    @Override
    public void borrarAutor(Long id) {
    	Autor autorABorrar = obtenerAutor(id);
    	autorRepository.delete(autorABorrar);
    }

    @Override
    public Autor obtenerAutor(Long id) {
        return autorRepository.read(id);
    }

    @Override
    public List<Autor> listarAutor() {
        return autorRepository.list();
    }
    
}
