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

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class SubastaServiceImplTest {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private SubastaService subastaService;

	private Obra obra1;
	private Obra obra2;
	private Obra obra3;

	private Subasta subasta1;
	private Subasta subasta2;
	private Subasta subasta3;

	private Tipo tipo1;
	private Tipo tipo2;
	private Tipo tipo3;

	private Estilo estilo1;
	private Estilo estilo2;
	private Estilo estilo3;

	private Autor autor1;
	private Autor autor2;
	private Autor autor3;

	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirSubasta() {
		Subasta historicoCreado = subastaService.aniadirSubasta(subasta2);
		assertNotNull(historicoCreado.getId());
	}

	@Test
	public void testModificarSubasta() {
		subasta2.setFechaFin("aa");
		subastaService.modificarSubasta(subasta2);
		assertEquals(subasta2.getFechaFin(), "aa");
	}

	@Test
	public void testBorrarObra() {
		Subasta subastaABorrar = new Subasta(obra1, 11, 22, "hoy", "mañana", true);
		subastaService.aniadirSubasta(subastaABorrar);
		subastaService.borrarSubasta(subastaABorrar.getId());
		List<Subasta> listaSubasta = subastaService.listarSubasta();
		assertEquals(listaSubasta.size(), 3);
	}

	@Test
	public void testListarSubasta() {
		List<Subasta> listaSubasta = subastaService.listarSubasta();
		for (Subasta u : listaSubasta) {
			assertNotNull(u.getId());
		}

	}

	private void inicializaBaseDeDatos() {

		tipo1 = new Tipo("tipo1", true);
		tipo2 = new Tipo("tipo2", true);
		tipo3 = new Tipo("tipo3", true);

		entityManager.persist(tipo1);
		entityManager.persist(tipo2);
		entityManager.persist(tipo3);

		estilo1 = new Estilo("estilo1", true);
		estilo2 = new Estilo("estilo2", true);
		estilo3 = new Estilo("estilo3", true);

		entityManager.persist(estilo1);
		entityManager.persist(estilo2);
		entityManager.persist(estilo3);

		autor1 = new Autor("autor1", "123", true);
		autor2 = new Autor("autor2", "123", true);
		autor3 = new Autor("autor3", "123", true);

		entityManager.persist(autor1);
		entityManager.persist(autor2);
		entityManager.persist(autor3);

		obra1 = new Obra("titulo", autor1, 1234, tipo1, estilo1, true, "imagen");
		obra2 = new Obra("titulo", autor2, 1234, tipo2, estilo2, true, "imagen");
		obra3 = new Obra("titulo", autor3, 1234, tipo3, estilo3, true, "imagen");

		entityManager.persist(obra1);
		entityManager.persist(obra2);
		entityManager.persist(obra3);

		subasta1 = new Subasta(obra1, 11, 22, "hoy", "mañana", true);
		subasta2 = new Subasta(obra2, 11, 22, "hoy", "mañana", true);
		subasta3 = new Subasta(obra3, 11, 22, "hoy", "mañana", true);

		entityManager.persist(subasta1);
		entityManager.persist(subasta2);
		entityManager.persist(subasta3);

	}

}