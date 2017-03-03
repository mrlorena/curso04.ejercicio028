package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.repository.EstiloRepository;
import es.cic.curso.curso04.ejercicio028.backend.repository.ObraRepository;
import es.cic.curso.curso04.ejercicio028.backend.repository.TipoRepository;

@Service
@Transactional
public class ObraServiceImpl implements ObraService{

	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private TipoRepository tipoRepository;
	
	@Autowired
	private EstiloRepository estiloRepository;
	
	@Autowired
	private EstiloService estiloService;
	
	@Autowired
	private TipoService tipoService;

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
    
    @Override
	public void generaBBDD() {
    	Tipo tipo = new Tipo("tipo1");
    	Estilo estilo = new Estilo("estilo1");
    
    	tipoRepository.add(tipo);
    	estiloRepository.add(estilo);
    	
		Obra obra1 = new Obra("titulo1", "autor1", 1234, tipo, estilo, 1235, "imagen");
		Obra obra2 = new Obra("titulo2", "autor2", 1234, tipo, estilo, 1235, "imagen");	
		
		obraRepository.add(obra1);
		obraRepository.add(obra2);
		
	}

}