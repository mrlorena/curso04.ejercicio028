package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.estilo;
import es.cic.curso.curso04.ejercicio028.backend.repository.TipoRepository;
 

@Service
@Transactional
public class TipoServiceImpl implements TipoService{
	@Autowired
	private TipoRepository tipoRepository;

    @Override
	public estilo aniadirTipo(estilo tipo) {
        return tipoRepository.add(tipo);
	}

    @Override
    public estilo modificarTipo(estilo tipo) {
    	return tipoRepository.update(tipo);
    }
   
    @Override
    public void borrarTipo(Long id) {
    	estilo tipoABorrar = obtenerTipo(id);
    	tipoRepository.delete(tipoABorrar);
    }

    @Override
    public estilo obtenerTipo(Long id) {
        return tipoRepository.read(id);
    }

    @Override
    public List<estilo> listarTipo() {
        return tipoRepository.list();
    }
}
