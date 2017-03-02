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

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso04.ejercicio028.backend.service.CategoriaService;

 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
public class CategoriaServiceImplTest {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired
	private CategoriaService categoriaService;

	private Categoria categoria1;
	private Categoria categoria2;
	private Categoria categoria3;
	
	
	@Before
	public void setUp() throws Exception {
		inicializaBaseDeDatos();
	}

	@Test
	public void testAniadirCategoria() {
		categoriaService.aniadirCategoria(categoria1);
		categoriaService.aniadirCategoria(categoria2);
		categoriaService.aniadirCategoria(categoria3);
		
		assertNotNull(categoria1.getId());
		assertNotNull(categoria2.getId());
		assertNotNull(categoria3.getId());
	}

	@Test
	public void testModificarCategoria() {
		
		categoria2.setNombreCategoria("categoria");
		categoriaService.modificarCategoria(categoria2);
		assertEquals(categoria2.getNombreCategoria(), "categoria");
	}

	@Test
	public void testBorrarCategoria() {
		Categoria categoriaABorrar = new Categoria("operacion");
		categoriaService.aniadirCategoria(categoriaABorrar);
		categoriaService.borrarCategoria(categoriaABorrar.getId());
		List<Categoria> listaCategoria = categoriaService.listarCategoria();
		assertEquals(listaCategoria.size(), 3);
	}

	@Test
	public void testListarCategoria() {
		List<Categoria> listaCategoria = categoriaService.listarCategoria();
		for (Categoria u : listaCategoria) {
			assertNotNull(u.getId());
		}

	}
	
	private void inicializaBaseDeDatos() {
		categoria1 = new Categoria("categoria1");
		categoria2 = new Categoria("categoria2");
		categoria3 = new Categoria("categoria3");

		entityManager.persist(categoria1);
		entityManager.persist(categoria2);
		entityManager.persist(categoria3);

	}


}
