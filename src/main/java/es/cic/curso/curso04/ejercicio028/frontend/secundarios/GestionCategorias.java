package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import org.springframework.web.context.ContextLoader;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionCategorias extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3915064377142680846L;

	@SuppressWarnings("unused")
	private MyUI padre;
	private Grid grigOperaciones;
	private EstiloService categoriaService;
	 
	private ComboBox operaciones = new ComboBox();
	private NativeButton cambiarRol;
	private NativeButton cancelar;
	private CategoriasForm detalleOperracion;
	
	
	public GestionCategorias(MyUI padre){
		this.padre = padre;
		
		categoriaService = ContextLoader.getCurrentWebApplicationContext().getBean(EstiloService.class);	
		
		
	
	}

	public void cargaGridCategorias() {
	 	
	
	}
}
