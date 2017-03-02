package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;

import es.cic.curso.curso04.ejercicio028.backend.service.CategoriaService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionCategorias extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3915064377142680846L;

	@SuppressWarnings("unused")
	private MyUI padre;
	private Grid grigOperaciones;
	private CategoriaService categoriaService;
	 
	private ComboBox operaciones = new ComboBox();
	private NativeButton cambiarRol;
	private NativeButton cancelar;
	private CategoriasForm detalleOperracion;
	
	
	public GestionCategorias(MyUI padre){
		this.padre = padre;
		
		categoriaService = ContextLoader.getCurrentWebApplicationContext().getBean(CategoriaService.class);	
		
		
	
	}

	public void cargaGridCategorias() {
	 	
	
	}
}
