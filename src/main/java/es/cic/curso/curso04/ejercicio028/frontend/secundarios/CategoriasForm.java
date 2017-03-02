package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
public class CategoriasForm extends FormLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3416021602328437461L;
	 
	@SuppressWarnings("unused")
	private GestionCategorias padre;
	
	@PropertyId("nombreRol")
	protected ComboBox nombreRol;
	

	private List<String> listaRoles;
	private EstiloService categoriaService;
	
	private NativeButton confirmar;
	private NativeButton cancelar;

	private Estilo categoria;
	
	
	public CategoriasForm(GestionCategorias padre) {
		
		
		final VerticalLayout vertical1 = new VerticalLayout();
		vertical1.setSpacing(true);

		categoriaService = ContextLoader.getCurrentWebApplicationContext().getBean(EstiloService.class);	
		
		
		
	}
	public void setCategoria(Estilo categoria) {
		this.setVisible(categoria != null);
		this.categoria = categoria;

		if (categoria != null) {
			BeanFieldGroup.bindFieldsUnbuffered(categoria, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Estilo(), this);
		}
	}
}
