package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.repository.ObraRepository;

@Service
@Transactional
public class ObraServiceImpl implements ObraService{

	@Autowired
	private ObraRepository obraRepository;

    @Override
	public Obra aniadirObra(Obra obra) {
        return obraRepository.add(obra);
	}

    @Override
    public Obra modificarObra(Obra obra) {
    	return obraRepository.update(obra);
    }
   
    @Override
    public void borrarObra(Long id) {
    	Obra obraABorrar = obtenerObra(id);
        obraRepository.delete( obraABorrar);
    }

    @Override
    public Obra obtenerObra(Long id) {
        return obraRepository.read(id);
    }

    @Override
    public List<Obra> listarObra() {
        return obraRepository.list();
    }

}