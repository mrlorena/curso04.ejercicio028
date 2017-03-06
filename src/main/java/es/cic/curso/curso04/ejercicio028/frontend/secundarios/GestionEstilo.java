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

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionEstilo extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3915064377142680846L;

	@SuppressWarnings("unused")
	private MyUI padre;

	private EstiloService estiloService;

	private List<Estilo> listaEstilos = new ArrayList<>();
	private List<String> listaNombreEstilo;
	
	private NativeButton aniadirEstilo;
	private NativeButton modificar;
	private NativeButton cancelar;
	
	private EstiloForm detalleEstilo;
	private Grid gridEstilo;
	private final VerticalLayout extra;
	private final VerticalLayout vertical;
	private ComboBox estilos = new ComboBox();

	public GestionEstilo(MyUI padre) {
		this.padre = padre;
		
		vertical = new VerticalLayout();
		vertical.setSpacing(true);

		extra = new VerticalLayout();
		extra.setSpacing(true);


		estiloService = ContextLoader.getCurrentWebApplicationContext().getBean(EstiloService.class);
		listaEstilos = new ArrayList<>();
		listaNombreEstilo = new ArrayList<>();
		
		aniadirEstilo = new NativeButton("Añadir Estilo");
		aniadirEstilo.setIcon(FontAwesome.PLUS);
		
		modificar = new NativeButton("modificar");
		modificar.setIcon(FontAwesome.PENCIL);
		modificar.setStyleName("red");

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);


		gridEstilo = new Grid();
		gridEstilo.setWidth(820, Unit.PIXELS);
		gridEstilo.setColumns("nombreEstilo", "habilitado");
		gridEstilo.setFrozenColumnCount(1);
		gridEstilo.setSelectionMode(SelectionMode.NONE);

		detalleEstilo = new EstiloForm(this);
		aniadirEstilo.addClickListener(e -> {
			aniadirEstilo.setVisible(false);

			aniadirEstilo();
		});

		
		modificar.addClickListener(e -> {modificarEstilo();
		

		});


		vertical.addComponents(aniadirEstilo,modificar,  extra, detalleEstilo);
		addComponents(gridEstilo, vertical);
		
		cargarEstilos(null);

	}

	private void aniadirEstilo() {
		detalleEstilo.setVisible(true);

		Estilo estilo = new Estilo("", true);
		detalleEstilo.setEstilo(estilo);

	}
	
	public void modificarEstilo() {
		listaEstilos = estiloService.listarEstilo();
		listaNombreEstilo.clear();
		for (Estilo e : listaEstilos) {

			listaNombreEstilo.add(e.getNombreEstilo());

		}
		estilos = new ComboBox("Estilo", listaNombreEstilo);
		estilos.setInputPrompt("Seleccione autor a modificar");
		estilos.setNullSelectionAllowed(false);
		estilos.select(1);
		estilos.setImmediate(true);
		estilos.setWidth(300, Unit.PIXELS);

		aniadirEstilo.setVisible(false);
		modificar.setVisible(false);
		estilos.setVisible(true);
		cancelar.setVisible(true);

		extra.addComponents(estilos, cancelar);

		estilos.addValueChangeListener(a -> {

			for (Estilo estilo : listaEstilos) {
				if (estilos.getValue() == (estilo.getNombreEstilo())) {
					detalleEstilo.setVisible(true);
					detalleEstilo.setEstilo(estilo);
					cancelar.setVisible(false);
				}
			}
		});

		cancelar.addClickListener(a -> {

			estilos.setVisible(false);
			cancelar.setVisible(false);
			estilos.clear();
			aniadirEstilo.setVisible(true);
			modificar.setVisible(true);

		});
	}
	
	

	public void cargarEstilos(Estilo estilo) {
		estilos.setVisible(false);
		estilos.clear();
		modificar.setVisible(true);
		
		if(estilo != null){
			estiloService.modificarEstilo(estilo);
		}	
		
		listaEstilos = estiloService.listarEstilo();

		aniadirEstilo.setVisible(true);
		detalleEstilo.setVisible(false);

		gridEstilo.setContainerDataSource(new BeanItemContainer<>(Estilo.class, listaEstilos));
		detalleEstilo.setEstilo(null);

	}
}
