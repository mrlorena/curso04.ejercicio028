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
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionTipos extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897900102340873208L;
	
	private TipoService tipoService;
	private ObraService obraService;
	private List<Tipo> listaTipos = new ArrayList<>();

	private TiposForm detalleTipo;
	private NativeButton aniadirTipo;
	private Grid gridTipos;
	
	@SuppressWarnings("unused")
	private MyUI padre;
	

	public GestionTipos(MyUI padre){
		this.padre = padre;
		
		tipoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);	
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);	

		if(listaTipos.isEmpty()){	
			obraService.generaBBDD();
		}
		
		aniadirTipo = new NativeButton("Añadir Tipo");
		aniadirTipo.setIcon(FontAwesome.PLUS);
		
		gridTipos = new Grid();
		gridTipos.setWidth(820, Unit.PIXELS);	
		gridTipos.setColumns("nombreTipo");	
		gridTipos.setFrozenColumnCount(1);
		gridTipos.setSelectionMode(SelectionMode.NONE);	
		
		detalleTipo = new TiposForm(this);
		aniadirTipo.addClickListener(e->{	
			aniadirTipo.setVisible(false);
			
			aniadirTipo();
		});
		
	
		cargarTipos(null);
		
		
		addComponents(gridTipos,aniadirTipo,detalleTipo);	
		
	}

	private void aniadirTipo() {	
		detalleTipo.setVisible(true);
		
		Tipo tipo = new Tipo("");
		detalleTipo.setTipo(tipo);
	
	}

	public void cargarTipos(Tipo tipo) {
		listaTipos = tipoService.listarTipo();
		System.out.println("1111111111"+listaTipos);
		aniadirTipo.setVisible(true);
		detalleTipo.setVisible(false);
		
		if(tipo!=null){
			gridTipos.setContainerDataSource(
					new BeanItemContainer<>(Tipo.class, listaTipos)
					);
			detalleTipo.setTipo(null);
		}
	
	}
}