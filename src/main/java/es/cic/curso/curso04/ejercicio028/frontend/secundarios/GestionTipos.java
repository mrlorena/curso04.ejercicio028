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
	private List<String> listaNombreTipo;

	private TiposForm detalleTipo;
	private NativeButton aniadirTipo;
	private NativeButton modificar;
	private NativeButton cancelar;
	
	private final VerticalLayout extra;
	private final VerticalLayout vertical;
	private ComboBox tipos = new ComboBox();
	private Grid gridTipos;

	@SuppressWarnings("unused")
	private MyUI padre;

	public GestionTipos(MyUI padre) {
		this.padre = padre;
		
		vertical = new VerticalLayout();
		vertical.setSpacing(true);

		extra = new VerticalLayout();
		extra.setSpacing(true);


		tipoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);

		listaTipos = new ArrayList<>();
		
		listaNombreTipo = new ArrayList<>();

		aniadirTipo = new NativeButton("Añadir Tipo");
		aniadirTipo.setIcon(FontAwesome.PLUS);
		
		modificar = new NativeButton("modificar");
		modificar.setIcon(FontAwesome.PENCIL);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		gridTipos = new Grid();
		gridTipos.setWidth(820, Unit.PIXELS);
		gridTipos.setColumns("nombreTipo", "habilitado");
		gridTipos.setFrozenColumnCount(1);
		gridTipos.setSelectionMode(SelectionMode.NONE);

		detalleTipo = new TiposForm(this);

		aniadirTipo.addClickListener(e -> {
			aniadirTipo.setVisible(false);

			aniadirTipo();
		});
		
		modificar.addClickListener(e -> {modificarTipo();
		

		});


		vertical.addComponents(aniadirTipo,modificar, extra, detalleTipo);
		addComponents(gridTipos, vertical);
		
		cargarTipos(null);

		

	}

	private void aniadirTipo() {
		detalleTipo.setVisible(true);

		Tipo tipo = new Tipo("", true);
		detalleTipo.setTipo(tipo);

	}
	
	public void modificarTipo() {
		listaTipos = tipoService.listarTipo();
		listaNombreTipo.clear();
		for (Tipo t : listaTipos) {

			listaNombreTipo.add(t.getNombreTipo());

		}
		tipos = new ComboBox("Tipo", listaNombreTipo);
		tipos.setInputPrompt("Seleccione el tipo a modificar");
		tipos.setNullSelectionAllowed(false);
		tipos.select(1);
		tipos.setImmediate(true);
		tipos.setWidth(300, Unit.PIXELS);

		aniadirTipo.setVisible(false);
		modificar.setVisible(false);
		tipos.setVisible(true);
		cancelar.setVisible(true);

		extra.addComponents(tipos, cancelar);

		tipos.addValueChangeListener(a -> {

			for (Tipo tipo : listaTipos) {
				if (tipos.getValue() == (tipo.getNombreTipo())) {
					detalleTipo.setVisible(true);
					detalleTipo.setTipo(tipo);
					cancelar.setVisible(false);
				}
			}
		});

		cancelar.addClickListener(a -> {

			tipos.setVisible(false);
			cancelar.setVisible(false);
			tipos.clear();
			aniadirTipo.setVisible(true);
			modificar.setVisible(true);

		});
	}


	public void cargarTipos(Tipo tipo) {
		
		tipos.setVisible(false);
		tipos.clear();
		modificar.setVisible(true);
		
		if(tipo != null){
			tipoService.modificarTipo(tipo);
		}	
		listaTipos = tipoService.listarTipo();
		
		aniadirTipo.setVisible(true);
		detalleTipo.setVisible(false);

		gridTipos.setContainerDataSource(new BeanItemContainer<>(Tipo.class, listaTipos));
		detalleTipo.setTipo(null);

	}
}