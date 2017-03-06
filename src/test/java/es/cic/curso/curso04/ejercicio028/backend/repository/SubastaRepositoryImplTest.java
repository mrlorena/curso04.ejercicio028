package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"}
)
public class SubastaRepositoryImplTest extends AbstractRepositoryImplTest<Long, Subasta> {

	
	 @Autowired
	    private SubastaRepository sut;
		private Tipo tipo;
		private Estilo estilo;
		private Autor autor;
		private Obra obra;

	    @Before
	    @Override
	    public void setUp() throws Exception {
	        super.setUp();
	        
	        tipo = new Tipo("cuadro");
	    	estilo = new Estilo("cuadro");
	    	autor = new Autor("autor1",1234);
	    	
	    	em.persist(tipo);
	    	em.persist(estilo);
	    	em.persist(autor);
	    	
	    	obra = new Obra("titulo", autor, 123, tipo, estilo, true, "imagen");
	    	
	    	em.persist(obra);
	    }

	    @Override
	    public Subasta getInstanceDeTParaNuevo() {
	    	
	    	Subasta subasta = new Subasta();
	    	subasta.setObra(obra);
	    	subasta.setPujaInicial(11);
	    	subasta.setPrecioVenta(22);
	    	subasta.setFechaInicio("hoy");
	    	subasta.setFechaFin("mañana");
	    	subasta.setActiva(true);
	    	
	        return subasta;
	    }

	    @Override
	    public Subasta getInstanceDeTParaLectura() {
	    	Subasta subasta = new Subasta();
	    	subasta.setObra(obra);
	    	subasta.setPujaInicial(11);
	    	subasta.setPrecioVenta(22);
	    	subasta.setFechaInicio("hoy");
	    	subasta.setFechaFin("mañana");
	    	subasta.setActiva(true);
	    	
	        return subasta;
	    }

	    @Override
	    public Long getClavePrimariaNoExistente() {
	        return Long.MAX_VALUE;
	    }

	    @Override
	    public Subasta getInstanceDeTParaModificar(Long clave) {
	    	Subasta subasta = getInstanceDeTParaLectura();
	    	subasta.setId(clave);
	    	subasta.setObra(obra);
	    	subasta.setPujaInicial(11);
	    	subasta.setPrecioVenta(22);
	    	subasta.setFechaInicio("hoy");
	    	subasta.setFechaFin("mañana");
	    	subasta.setActiva(true);
	       
	        return subasta;
	    }

	    @Override
	    public IRepository<Long, Subasta> getRepository() {
	        return sut;
	    }

	    @Override
	    public boolean sonDatosIguales(Subasta t1, Subasta t2) {
	        if (t1 == null || t2 == null) {
	            throw new UnsupportedOperationException("No puedo comparar nulos");
	        }
	        
			if (!t1.getObra().equals(t2.getObra())) {
				return false;
			}
			
			if (!(t1.getPujaInicial()==(t2.getPujaInicial()))) {
				return false;
			}
		
			if (!(t1.getPrecioVenta()==(t2.getPrecioVenta()))) {
				return false;
			}
			
			if (!t1.getFechaInicio().equals(t2.getFechaInicio())) {
				return false;
			}
			
			if (!t1.getFechaFin().equals(t2.getFechaFin())) {
				return false;
			}
			
			if (!(t1.isActiva()==(t2.isActiva()))) {
				return false;
			}
		
	        return true;
	    }
	}