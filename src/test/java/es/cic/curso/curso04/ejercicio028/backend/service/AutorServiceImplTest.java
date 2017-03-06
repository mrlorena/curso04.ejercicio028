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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class AutorServiceImplTest {

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private AutorService autorService;

	private Autor autor1;
	private Autor autor2;
	private Autor autor3;

	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirAutor() {
		Autor tipoCreado = autorService.aniadirAutor(autor2);
		assertNotNull(tipoCreado.getId());
	}

	@Test
	public void testModificarAutor() {
		autor2.setNombre("Copiar");
		autorService.modificarAutor(autor2);
		assertEquals(autor2.getNombre(), "Copiar");
	}

	@Test
	public void testBorrarTipo() {
		Autor autorABorrar = new Autor("aa", "12", true);
		autorService.aniadirAutor(autorABorrar);
		autorService.borrarAutor(autorABorrar.getId());
		List<Autor> listaAutor = autorService.listarAutor();
		assertEquals(listaAutor.size(), 3);
	}

	@Test
	public void testListarAutor() {
		List<Autor> listaAutor = autorService.listarAutor();
		for (Autor u : listaAutor) {
			assertNotNull(u.getId());
		}

	}

	private void inicializaBaseDeDatos() {

		autor1 = new Autor("nombre1", "123", true);
		autor2 = new Autor("nombre2", "456", true);
		autor3 = new Autor("nombre3", "789", true);
		entityManager.persist(autor1);
		entityManager.persist(autor2);
		entityManager.persist(autor3);
	}

}