package es.cic.curso.curso04.ejercicio028.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.repository.AutorRepository;
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
	private AutorRepository autorRepository;
	
	@Autowired
	private EstiloRepository estiloRepository;
	

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
    	Tipo tipo1 = new Tipo("Escultura");
    	Tipo tipo2 = new Tipo("Dibujo");
    	Tipo tipo3 = new Tipo("Fotografía");
    	Tipo tipo4 = new Tipo("Mosaico");
    	
    	Estilo estilo1 = new Estilo("Gótico");
    	Estilo estilo2 = new Estilo("Renacentista");
    	
    	Autor autor = new Autor("Desconocido", 0);
    	Autor autor1 = new Autor("Leonardo Da Vinci", 1452);
    
    	tipoRepository.add(tipo1);
    	tipoRepository.add(tipo2);
    	tipoRepository.add(tipo3);
    	tipoRepository.add(tipo4);
    	
    	estiloRepository.add(estilo1);
    	estiloRepository.add(estilo2);
    	
    	autorRepository.add(autor);
    	autorRepository.add(autor1);
    	
		Obra obra1 = new Obra("titulo1", autor, 1234, tipo1, estilo1, true, "imagen");
		Obra obra2 = new Obra("titulo2", autor, 1234, tipo2, estilo2, true, "imagen");	
		
		obraRepository.add(obra1);
		obraRepository.add(obra2);
		
	}

}