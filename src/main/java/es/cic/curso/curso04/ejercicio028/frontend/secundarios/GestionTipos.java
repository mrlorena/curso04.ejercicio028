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

import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionTipos extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897900102340873208L;

	private TiposForm detalleUsuario;
	private NativeButton aniadirUsuario;
	private NativeButton modificar;
	private Grid gridUsuarios;
	@SuppressWarnings("unused")
	private MyUI padre;
	private ComboBox usuarios=new ComboBox();
	List<String> listaNombres = new ArrayList<>();
	private NativeButton cancelar;
	private ComboBox mostrar;

	public GestionTipos(MyUI padre){
		this.padre = padre;

		
	}

	private void aniadirTipos() {	
		
	}

	public void cargaGridTipos() {
	
	}
}