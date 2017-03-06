package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;
import es.cic.curso.curso04.ejercicio028.backend.repository.SubastaRepository;

@Service
@Transactional
public class SubastaServiceImpl implements SubastaService {
	@Autowired
	private SubastaRepository subastaRepository;

	@Override
	public Subasta aniadirSubasta(Subasta subasta) {
		return subastaRepository.add(subasta);
	}

	@Override
	public Subasta modificarSubasta(Subasta subasta) {
		return subastaRepository.update(subasta);
	}

	@Override
	public void borrarSubasta(Long id) {
		Subasta subastaABorrar = obtenerSubasta(id);
		subastaRepository.delete(subastaABorrar);
	}

	@Override
	public Subasta obtenerSubasta(Long id) {
		return subastaRepository.read(id);
	}

	@Override
	public List<Subasta> listarSubasta() {
		return subastaRepository.list();
	}

}
