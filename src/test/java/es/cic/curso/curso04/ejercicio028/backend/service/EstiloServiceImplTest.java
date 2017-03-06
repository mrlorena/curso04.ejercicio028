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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class EstiloServiceImplTest {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private EstiloService estiloService;

	private Estilo estilo1;
	private Estilo estilo2;
	private Estilo estilo3;

	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirCategoria() {
		estiloService.aniadirEstilo(estilo1);
		estiloService.aniadirEstilo(estilo2);
		estiloService.aniadirEstilo(estilo3);

		assertNotNull(estilo1.getId());
		assertNotNull(estilo2.getId());
		assertNotNull(estilo3.getId());
	}

	@Test
	public void testModificarCategoria() {

		estilo2.setNombreEstilo("estilo");
		estiloService.modificarEstilo(estilo2);
		assertEquals(estilo2.getNombreEstilo(), "estilo");
	}

	@Test
	public void testBorrarCategoria() {
		Estilo estiloABorrar = new Estilo("estilo", true);
		estiloService.aniadirEstilo(estiloABorrar);
		estiloService.borrarEstilo(estiloABorrar.getId());
		List<Estilo> listaEstilo = estiloService.listarEstilo();
		assertEquals(listaEstilo.size(), 3);
	}

	@Test
	public void testListarCategoria() {
		List<Estilo> listaEstilo = estiloService.listarEstilo();
		for (Estilo u : listaEstilo) {
			assertNotNull(u.getId());
		}

	}

	private void inicializaBaseDeDatos() {
		estilo1 = new Estilo("categoria1", true);
		estilo2 = new Estilo("categoria2", true);
		estilo3 = new Estilo("categoria3", true);

		entityManager.persist(estilo1);
		entityManager.persist(estilo2);
		entityManager.persist(estilo3);

	}

}
