package es.cic.curso.curso04.ejercicio028.frontend.secundarios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionObras  extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683850118394414599L;
	
	private ObraService obraService;
	private Tipo tipo;
	private Estilo categoria;
	private ObrasForm detalleObras;
	
	private final VerticalLayout verticalLayoutGrid;
	private final VerticalLayout verticalLayoutFormulario;
	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private final HorizontalLayout horizontal3;
	private final HorizontalLayout horizontal4;
	
	private NativeButton aniadirObra;
	private Grid gridObras;
	
	private List<Obra> listaObras = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private MyUI padre;


	
	public GestionObras(MyUI padre){
		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);
		
		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		horizontal3 = new HorizontalLayout();
		horizontal4 = new HorizontalLayout();
	
		verticalLayoutGrid = new VerticalLayout();
		verticalLayoutFormulario = new VerticalLayout();
		
	
	
		aniadirObra = new NativeButton("Añadir Registro");
		aniadirObra.setIcon(FontAwesome.PLUS);
		
		gridObras = new Grid();
		gridObras.setWidth(820, Unit.PIXELS);	
		gridObras.setColumns("usuario","operacion","hora","permitido");	
		gridObras.setFrozenColumnCount(1);
		gridObras.setSelectionMode(SelectionMode.NONE);	
		
		detalleObras = ObrasForm(this);

		aniadirObra.addClickListener(e->{	
			aniadirObra.setVisible(false);
			detalleObras.actualizarUsuarios();
			detalleObras.actualizarOp();
			aniadirObra();
		});
		
		cargarObras(null);
		
		
		
		
		verticalLayoutGrid.addComponent(gridObras);
		verticalLayoutFormulario.addComponents(horizontal1, horizontal2, horizontal3, horizontal4);
		addComponents(verticalLayoutGrid, verticalLayoutFormulario);
		
	}
	private void aniadirObra() {	
		detalleObras.setVisible(true);
		Obra o = new Obra("","",0,tipo,categoria,0,"");
		detalleObras.setObra(o);
		gridObras.setContainerDataSource(
				new BeanItemContainer<>(Obra.class, listaObras)
				);
		
	}


	public void cargarObras(Obra obra){	
		List<Obra> listaObras = obraService.listarObra();
		aniadirObra.setVisible(true);
		detalleObras.setVisible(false);
		

		gridObras.setContainerDataSource(
				new BeanItemContainer<>(Obra.class, listaObras)
				);
		detalleObras.setObra(null);
	}
}