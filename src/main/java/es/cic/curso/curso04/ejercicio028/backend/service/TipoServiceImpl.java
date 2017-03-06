package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.repository.TipoRepository;
 

@Service
@Transactional
public class TipoServiceImpl implements TipoService{
	@Autowired
	private TipoRepository tipoRepository;

    @Override
	public Tipo aniadirTipo(Tipo tipo) {
        return tipoRepository.add(tipo);
	}

    @Override
    public Tipo modificarTipo(Tipo tipo) {
    	return tipoRepository.update(tipo);
    }
   
    @Override
    public void borrarTipo(Long id) {
    	Tipo tipoABorrar = obtenerTipo(id);
    	tipoRepository.delete(tipoABorrar);
    }

    @Override
    public Tipo obtenerTipo(Long id) {
        return tipoRepository.read(id);
    }

    @Override
    public List<Tipo> listarTipo() {
        return tipoRepository.list();
    }
    
   
}
