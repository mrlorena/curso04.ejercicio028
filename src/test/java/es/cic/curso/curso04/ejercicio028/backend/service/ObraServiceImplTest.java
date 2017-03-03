package es.cic.curso.curso04.ejercicio028.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class ObraServiceImplTest {

    @PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private ObraService obraService;
	 

	private Obra obra1;
	private Obra obra2;
	private Obra obra3;
	

	private Tipo tipo1;
	private Tipo tipo2;
	private Tipo tipo3;
	
	private Estilo estilo1;
	private Estilo estilo2;
	private Estilo estilo3;
 
	
	
	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirObra() {
		Obra historicoCreado = obraService.aniadirObra(obra2);
		assertNotNull(historicoCreado.getId());
	}

	@Test
	public void testModificarObra() {
		obra2.setAutor("autor");
		obraService.modificarObra(obra2);
		assertEquals(obra2.getAutor(), "autor");
	}

	@Test
	public void testBorrarObra() {
		Obra historicoABorrar = new Obra("titulo","autor",1234,tipo1,estilo1,1234,"imagen");
		obraService.aniadirObra(historicoABorrar);
		obraService.borrarObra(historicoABorrar.getId());
		List<Obra> listaHistorico = obraService.listarObra();
		assertEquals(listaHistorico.size(), 3);
	}

	@Test
	public void testListarObra() {
		List<Obra> listaHistorico = obraService.listarObra();
		for (Obra u : listaHistorico) {
			assertNotNull(u.getId());
		}

	}
	
	private void inicializaBaseDeDatos() {
 	 
		tipo1 = new Tipo("tipo1");
		tipo2 = new Tipo("tipo2");
		tipo3 = new Tipo("tipo3");
	
		entityManager.persist(tipo1);
		entityManager.persist(tipo2);
		entityManager.persist(tipo3);
		
		estilo1 = new Estilo("estilo1");
		estilo2 = new Estilo("estilo2");
		estilo3 = new Estilo("estilo3");

		entityManager.persist(estilo1);
		entityManager.persist(estilo2);
		entityManager.persist(estilo3);
		
		obra1 = new Obra("titulo","autor",1234,tipo1,estilo1,1234,"imagen");
		obra2 = new Obra("titulo","autor",1234,tipo2,estilo2,1234,"imagen");
		obra3 = new Obra("titulo","autor",1234,tipo3,estilo3,1234,"imagen");

		entityManager.persist(obra1);
		entityManager.persist(obra2);
		entityManager.persist(obra3);

	}


}