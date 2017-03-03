package es.cic.curso.curso04.ejercicio028.frontend.secundarios;
import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;


public class TiposForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;

	@PropertyId("nombreTipo")
	private TextField nombreTipo;
	
	private NativeButton confirmar;
	private NativeButton cancelar;	
	
	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;

	
	@SuppressWarnings("unused")
	private GestionTipos padre;
	private TipoService tipoService;

	private Tipo tipo;
	
			
	public TiposForm(GestionTipos padre) {
		this.padre = padre;
		tipo = new Tipo();
		tipoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);	
		
		confirmar = new NativeButton("Guardar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);
		
		nombreTipo = new TextField("Nombre");

		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		
		confirmar.addClickListener(e->{
				tipoService.aniadirTipo(tipo);
				padre.cargarTipos(tipo);
				
				setTipo(null);
				nombreTipo.clear();
		});

		cancelar.addClickListener(e->{
			
			nombreTipo.clear();
			padre.cargarTipos(null);
		});
		
		horizontal1.addComponents(nombreTipo);
		horizontal2.addComponents(confirmar, cancelar);

		addComponents(horizontal1,horizontal2);	
		
		
	}
	
	public void setTipo(Tipo tipo) {
		this.setVisible(tipo != null);
		this.tipo = tipo;

		if (tipo != null) {
			BeanFieldGroup.bindFieldsUnbuffered(tipo, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Tipo(), this);
		}
	}
 
	
		
}