package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.repository.EstiloRepository;

@Service
@Transactional
public class EstiloServiceImpl implements EstiloService{

	@Autowired
	private EstiloRepository estiloRepository;

    @Override
	public Estilo aniadirEstilo(Estilo estilo) {
        return estiloRepository.add(estilo);
	}

    @Override
    public Estilo modificarEstilo(Estilo estilo) {
    	return estiloRepository.update(estilo);
    }
   
    @Override
    public void borrarEstilo(Long id) {
    	Estilo estiloABorrar = obtenerEstilo(id);
        estiloRepository.delete( estiloABorrar);
    }

    @Override
    public Estilo obtenerEstilo(Long id) {
        return estiloRepository.read(id);
    }

    @Override
    public List<Estilo> listarEstilo() {
        return estiloRepository.list();
    }
    
}