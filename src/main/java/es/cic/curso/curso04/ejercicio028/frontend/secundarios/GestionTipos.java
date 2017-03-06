package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionTipos extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897900102340873208L;

	private TipoService tipoService;
	private List<Tipo> listaTipos;

	private TiposForm detalleTipo;
	private NativeButton aniadirTipo;
	private Grid gridTipos;

	@SuppressWarnings("unused")
	private MyUI padre;

	public GestionTipos(MyUI padre) {
		this.padre = padre;

		tipoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);

		listaTipos = new ArrayList<>();

		aniadirTipo = new NativeButton("Añadir Tipo");
		aniadirTipo.setIcon(FontAwesome.PLUS);

		gridTipos = new Grid();
		gridTipos.setWidth(820, Unit.PIXELS);
		gridTipos.setColumns("nombreTipo");
		gridTipos.setFrozenColumnCount(1);
		gridTipos.setSelectionMode(SelectionMode.NONE);

		detalleTipo = new TiposForm(this);

		aniadirTipo.addClickListener(e -> {
			aniadirTipo.setVisible(false);

			aniadirTipo();
		});

		cargarTipos(null);

		addComponents(gridTipos, aniadirTipo, detalleTipo);

	}

	private void aniadirTipo() {
		detalleTipo.setVisible(true);

		Tipo tipo = new Tipo("");
		detalleTipo.setTipo(tipo);

	}

	public void cargarTipos(Tipo tipo) {
		listaTipos = tipoService.listarTipo();
		
		aniadirTipo.setVisible(true);
		detalleTipo.setVisible(false);

		gridTipos.setContainerDataSource(new BeanItemContainer<>(Tipo.class, listaTipos));
		detalleTipo.setTipo(null);

	}
}