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

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class TipoServiceImplTest {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private TipoService tipoService;

	private Tipo tipo1;
	private Tipo tipo2;
	private Tipo tipo3;

	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirTipo() {
		Tipo tipoCreado = tipoService.aniadirTipo(tipo2);
		assertNotNull(tipoCreado.getId());
	}

	@Test
	public void testModificarTipo() {
		tipo2.setNombreTipo("Copiar");
		tipoService.modificarTipo(tipo2);
		assertEquals(tipo2.getNombreTipo(), "Copiar");
	}

	@Test
	public void testBorrarTipo() {
		Tipo tipoABorrar = new Tipo("Eliminar");
		tipoService.aniadirTipo(tipoABorrar);
		tipoService.borrarTipo(tipoABorrar.getId());
		List<Tipo> listaTipo = tipoService.listarTipo();
		assertEquals(listaTipo.size(), 3);
	}

	@Test
	public void testListarTipo() {
		List<Tipo> listaTipo = tipoService.listarTipo();
		for (Tipo u : listaTipo) {
			assertNotNull(u.getId());
		}

	}

	private void inicializaBaseDeDatos() {

		tipo1 = new Tipo("administrador");
		tipo2 = new Tipo("invitado");
		tipo3 = new Tipo("invitado");
		entityManager.persist(tipo1);
		entityManager.persist(tipo2);
		entityManager.persist(tipo3);
	}

}