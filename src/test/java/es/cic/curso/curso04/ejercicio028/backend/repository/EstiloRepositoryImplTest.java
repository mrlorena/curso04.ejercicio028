package es.cic.curso.curso04.ejercicio028.backend.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:es/cic/curso/curso04.ejercicio028/applicationContext.xml" })
public class EstiloRepositoryImplTest extends AbstractRepositoryImplTest<Long, Estilo> {

	@Autowired
	private EstiloRepository sut;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	public Estilo getInstanceDeTParaNuevo() {

		Estilo estilo = new Estilo();

		estilo.setNombreEstilo("dibujo");

		return estilo;
	}

	@Override
	public Estilo getInstanceDeTParaLectura() {

		Estilo estilo = new Estilo();

		estilo.setNombreEstilo("dibujo");

		return estilo;
	}

	@Override
	public Long getClavePrimariaNoExistente() {
		return Long.MAX_VALUE;
	}

	@Override
	public Estilo getInstanceDeTParaModificar(Long clave) {
		Estilo estilo = getInstanceDeTParaLectura();
		estilo.setId(clave);
		estilo.setNombreEstilo("dibujo");

		return estilo;
	}

	@Override
	public IRepository<Long, Estilo> getRepository() {
		return sut;
	}

	@Override
	public boolean sonDatosIguales(Estilo t1, Estilo t2) {
		if (t1 == null || t2 == null) {
			throw new UnsupportedOperationException("No puedo comparar nulos");
		}

		if (!t1.getNombreEstilo().equals(t2.getNombreEstilo())) {
			return false;
		}

		return true;
	}
}