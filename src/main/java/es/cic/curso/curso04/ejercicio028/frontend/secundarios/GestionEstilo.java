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

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionEstilo extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3915064377142680846L;

	@SuppressWarnings("unused")
	private MyUI padre;
	
	private EstiloService estiloService;
	private ObraService obraService;
	private List<Estilo> listaEstilos = new ArrayList<>();
	
	private NativeButton aniadirEstilo;
	private EstiloForm detalleEstilo;
	private Grid gridEstilo;
	
	public GestionEstilo(MyUI padre){
		this.padre = padre;
		
		estiloService = ContextLoader.getCurrentWebApplicationContext().getBean(EstiloService.class);	
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);	
		
		if(listaEstilos.isEmpty()){	
			obraService.generaBBDD();
		}
		
		aniadirEstilo = new NativeButton("Añadir Estilo");
		aniadirEstilo.setIcon(FontAwesome.PLUS);
		
		gridEstilo = new Grid();
		gridEstilo.setWidth(820, Unit.PIXELS);	
		gridEstilo.setColumns("nombreEstilo");	
		gridEstilo.setFrozenColumnCount(1);
		gridEstilo.setSelectionMode(SelectionMode.NONE);	
		
		detalleEstilo = new EstiloForm(this);
		aniadirEstilo.addClickListener(e->{	
			aniadirEstilo.setVisible(false);
			
			aniadirEstilo();
		});
		
	
		cargarEstilos(null);
		
		
		addComponents(gridEstilo,aniadirEstilo,detalleEstilo);	
		
	
	}

	private void aniadirEstilo() {	
		detalleEstilo.setVisible(true);
		
		Estilo estilo = new Estilo("");
		detalleEstilo.setEstilo(estilo);
		
		
	}

	public void cargarEstilos(Estilo estilo) {
		listaEstilos = estiloService.listarEstilo();

		aniadirEstilo.setVisible(true);
		detalleEstilo.setVisible(false);
		
		if(estilo!=null){
			gridEstilo.setContainerDataSource(
					new BeanItemContainer<>(Estilo.class, listaEstilos)
					);
			detalleEstilo.setEstilo(null);
		}
	
	}
}
