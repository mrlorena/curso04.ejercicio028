package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dto.ObraConverter;
import es.cic.curso.curso04.ejercicio028.backend.dto.ObraDTO;
import es.cic.curso.curso04.ejercicio028.backend.service.AutorService;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionObras extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2600433507457283447L;
	private ObraService obraService;
	private AutorService autorService;

	private ObrasForm detalleObras;
	private List<ObraDTO> listaObras;
	private List<Obra> listaObr;
	private List<String> listaTitulos;
	private List<Autor> listaAutores;

	private ObraConverter obraConverter = new ObraConverter();

	private NativeButton aniadirObra;
	private NativeButton modificar;
	private NativeButton cancelar;
	private Grid gridObras;

	private final VerticalLayout extra;
	private final VerticalLayout vertical;
	private ComboBox titulos = new ComboBox();

	@SuppressWarnings("unused")
	private MyUI padre;

	public GestionObras(MyUI padre) {
		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);
		autorService = ContextLoader.getCurrentWebApplicationContext().getBean(AutorService.class);

		vertical = new VerticalLayout();
		vertical.setSpacing(true);

		extra = new VerticalLayout();
		extra.setSpacing(true);

		listaObras = new ArrayList<>();
		listaObr = new ArrayList<>();
		listaTitulos = new ArrayList<>();
		listaAutores = new ArrayList<>();

		listaObr = obraService.listarObra();

		if (listaObr.isEmpty()) {
			obraService.generaBBDD();
			listaObr = obraService.listarObra();

		}

		listaAutores = autorService.listarAutor();

		listaObras = obraConverter.entity2DTO(listaObr, listaAutores);

		aniadirObra = new NativeButton("Añadir Obra");
		aniadirObra.setIcon(FontAwesome.PLUS);

		modificar = new NativeButton("modificar");
		modificar.setIcon(FontAwesome.PENCIL);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		gridObras = new Grid();
		gridObras.setWidth(820, Unit.PIXELS);
		gridObras.setColumns("titulo", "autor", "anio", "tipo", "estilo", "habilitada", "imagen");
		gridObras.setFrozenColumnCount(1);
		gridObras.setSelectionMode(SelectionMode.NONE);

		detalleObras = new ObrasForm(this);
		aniadirObra.addClickListener(e -> {
			aniadirObra.setVisible(false);
			detalleObras.actualizarTipo();
			detalleObras.actualizarEstilo();
			detalleObras.actualizarAutor();
			aniadirObra();
		});

		modificar.addClickListener(e -> {
			modificarObra();

		});

		vertical.addComponents(aniadirObra, modificar, extra, detalleObras);
		addComponents(gridObras, vertical);

		cargarObras(null);

	}

	private void aniadirObra() {
		detalleObras.setVisible(true);

		Obra obra = new Obra("", null, 0, null, null, false, "");
		detalleObras.setObra(obra);

		gridObras.setContainerDataSource(new BeanItemContainer<>(ObraDTO.class, listaObras));
	}

	public void modificarObra() {
		listaObr = obraService.listarObra();
		listaTitulos.clear();
		for (Obra obra : listaObr) {

			listaTitulos.add(obra.getTitulo());

		}
		titulos = new ComboBox("Titulo", listaTitulos);
		titulos.setInputPrompt("Seleccione el titulo");
		titulos.setNullSelectionAllowed(false);
		titulos.select(1);
		titulos.setImmediate(true);
		titulos.setWidth(300, Unit.PIXELS);

		aniadirObra.setVisible(false);
		modificar.setVisible(false);
		titulos.setVisible(true);
		cancelar.setVisible(true);

		extra.addComponents(titulos, cancelar);

		titulos.addValueChangeListener(a -> {

			for (Obra o : listaObr) {
				if (titulos.getValue() == (o.getTitulo())) {
					detalleObras.setVisible(true);
					detalleObras.setObra(o);
					detalleObras.actualizarTipo();
					detalleObras.actualizarEstilo();
					detalleObras.actualizarAutor();
					cancelar.setVisible(false);
				}
			}
		});

		cancelar.addClickListener(a -> {

			titulos.setVisible(false);
			cancelar.setVisible(false);
			titulos.clear();
			aniadirObra.setVisible(true);
			modificar.setVisible(true);

		});
	}

	public void cargarObras(Obra obra) {

		aniadirObra.setVisible(true);
		modificar.setVisible(true);
		detalleObras.setVisible(false);
		titulos.setVisible(false);

		if (obra != null) {

			listaObras.clear();

			for (Autor a : listaAutores) {

				if (obra.getAutor().getNombre().equals(a.getNombre())) {
					obraService.modificarObra(obra);

				}
			}

			listaObras = obraConverter.entity2DTO(listaObr, listaAutores);

		}

		gridObras.setContainerDataSource(new BeanItemContainer<>(ObraDTO.class, listaObras));
		detalleObras.setObra(null);
	}
}
