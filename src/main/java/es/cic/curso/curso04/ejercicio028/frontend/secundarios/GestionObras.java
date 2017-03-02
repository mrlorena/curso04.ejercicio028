package es.cic.curso.curso04.ejercicio028.frontend.secundarios;


import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.HistoricoForm;

public class GestionObras  extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683850118394414599L;
	
	
	private Tipo tipo;
	private Categoria categoria;
	private ObrasForm detalleObras;
	
	private final VerticalLayout verticalLayoutGrid;
	private final VerticalLayout verticalLayoutFormulario;
	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private final HorizontalLayout horizontal3;
	private final HorizontalLayout horizontal4;
	
	private NativeButton aniadirObra;
	private Grid gridObras;
	
 
	@SuppressWarnings("unused")
	private MyUI padre;


	
	public GestionObras(MyUI padre){
		this.padre = padre;
		
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
	private void aniadirObra(Obra obra) {	
		obra = new Obra("","",0,tipo,categoria,0,"");
		
	}


	public void cargarObras(Obra obra){	
		
		

	}
}