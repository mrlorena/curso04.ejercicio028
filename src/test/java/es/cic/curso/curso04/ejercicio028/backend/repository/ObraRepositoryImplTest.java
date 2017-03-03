package es.cic.curso.curso04.ejercicio028.backend.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"}
)
public class ObraRepositoryImplTest extends AbstractRepositoryImplTest<Long, Obra> {


    @PersistenceContext
	protected EntityManager em;
	
    @Autowired
	private ObraRepository sut;
	 
	private Tipo tipo;
	private Estilo estilo;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	    	super.setUp();
	    	
	    	tipo = new Tipo("cuadro");
	    	estilo = new Estilo("cuadro");
	    	em.persist(tipo);
	    	em.persist(estilo);
	        
	    }

	    @Override
	    public Obra getInstanceDeTParaNuevo() {
	    	
	    	Obra obra = new Obra();
	        
	    	obra.setTitulo("titulo");
	    	obra.setAutor("autor");
	    	obra.setAnio(1900);
	        obra.setTipo(tipo);
	        obra.setEstilo(estilo);
	        obra.setPrecio(1200);
	        obra.setImagen("imagen");
	        
	        return obra;
	        
	    }

	    @Override
	    public Obra getInstanceDeTParaLectura() {
	    	
	    	Obra obra = new Obra();
	        
	    	obra.setTitulo("titulo");
	    	obra.setAutor("autor");
	    	obra.setAnio(1900);
	        obra.setTipo(tipo);
	        obra.setEstilo(estilo);
	        obra.setPrecio(1200);
	        obra.setImagen("imagen");
	        
	        return obra;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Obra getInstanceDeTParaModificar(Long clave) {
	    	Obra obra = getInstanceDeTParaLectura();
	        obra.setId(clave);
	        obra.setTitulo("titulo");
	    	obra.setAutor("autor");
	    	obra.setAnio(1900);
	        obra.setTipo(tipo);
	        obra.setEstilo(estilo);
	        obra.setPrecio(1200);
	        obra.setImagen("imagen");
	       
	        return obra;
	    }

	    @Override
	    public IRepository<Long, Obra> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Obra t1, Obra t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getTitulo().equals(t2.getTitulo())) {
				return false;
			}

			if (!t1.getAutor().equals(t2.getAutor())) {
				return false;
			}
			
			if (!((t1.getAnio())==(t2.getAnio()))) {
				return false;
			}
			
			if (!t1.getTipo().equals(t2.getTipo())) {
				return false;
			}
			
			if (!t1.getEstilo().equals(t2.getEstilo())) {
				return false;
			}
			
			if (!(t1.getPrecio()==(t2.getPrecio()))) {
				return false;
			}
			
			if (!t1.getImagen().equals(t2.getImagen())) {
				return false;
			}
			
	        return true;
	    }
	}