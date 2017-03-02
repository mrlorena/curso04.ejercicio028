package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Categoria;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.CategoriaService;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;

public class ObrasForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;
	private ObraService obraService;

	@SuppressWarnings("unused")
	private GestionObras padre;

	@PropertyId("titulo")
	private TextField titulo;
	
	@PropertyId("autor")
	protected ComboBox autor=new ComboBox();
	
	@PropertyId("hora")
	protected String hora;
	@PropertyId("permitido")
	protected boolean permitido;

	private NativeButton confirmar;
	private NativeButton cancelar;
	private ComboBox horas;
	private ComboBox minutos;
	private ComboBox nombreUser=new ComboBox();
	private Obra obra;
	private List<Tipo> listaTipos;
	private List<Categoria> listaCategorias;
	
	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private ObraService historicoService;
	private List<String> listaNombres= new ArrayList<>();
	private List<String> listaRoles;
	private CategoriaService rolService;
	private TipoService operacionService;
	
	
	public ObrasForm(GestionObras padre) {
		
		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		
		this.padre = padre;
		obra = new Obra();

		
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

