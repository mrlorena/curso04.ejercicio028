package es.cic.curso.curso04.ejercicio028.frontend.secundarios;
import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.CategoriaService;


public class TiposForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;

	@PropertyId("nombre")
	private TextField nombre;
	@PropertyId("password")
	protected PasswordField password;
	@PropertyId("email")
	private TextField email;	
	@PropertyId("rol")
	
	private NativeButton confirmar;
	private NativeButton cancelar;	
	
	private ComboBox roles;
	
	@SuppressWarnings("unused")
	private GestionTipos padre;
	private CategoriaService rolService;

	private Tipo tipo;
	
			
	public TiposForm(GestionTipos padre) {
		this.padre = padre;

		rolService = ContextLoader.getCurrentWebApplicationContext().getBean(CategoriaService.class);	
		
		
	}
	private void mostrarNotificacion(Notification notificacionMostrar) {
		notificacionMostrar.setDelayMsec(2000);
		notificacionMostrar.show(Page.getCurrent());
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