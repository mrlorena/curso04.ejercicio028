package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;
import es.cic.curso.grupo5.ejercicio027.frontend.secundarios.GestionHistoricos;

public class ObrasForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;

	@SuppressWarnings("unused")
	private GestionObras padre;

	@PropertyId("titulo")
	private TextField titulo;
	
	@PropertyId("autor")
	protected ComboBox autor=new ComboBox();
	
	@PropertyId("anio")
	private TextField anio;
	
	@PropertyId("tipo")
	private Tipo nombreTipo;
	
	@PropertyId("categoria")
	private Estilo nombreCategoria;
	
	@PropertyId("precio")
	private TextField precio;
	
	@PropertyId("imagen")
	private TextField imagen;
	

	
	private Obra obra;
	private ObraService obraService;
	private List<String> listaAutores;
	private List<Tipo> listaTipos;
	private List<Estilo> listaCategorias;
	
	private EstiloService categoriaService;
	private TipoService operacionService;
	
	
	
	private NativeButton confirmar;
	private NativeButton cancelar;
	private ComboBox cbCategorias;
	private ComboBox cbTipos;
	private ComboBox cbAutores;
	
	
	
	public ObrasForm(GestionObras padre) {
		
		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);
		
		
	
	}
	public void setObra(Obra obra) {
		this.setVisible(obra != null);
		this.obra = obra;

		if (obra != null) {
			BeanFieldGroup.bindFieldsUnbuffered(obra, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Obra(), this);
		}
	}

}

