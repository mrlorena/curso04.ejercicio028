package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;

public class EstiloForm extends FormLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8212581707579739708L;

	@PropertyId("nombreEstilo")
	private TextField nombreEstilo;
	
	@PropertyId("habilitado")
	private CheckBox habilitado;

	private NativeButton confirmar;
	private NativeButton cancelar;

	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;

	@SuppressWarnings("unused")
	private GestionEstilo padre;

	private Estilo estilo;

	public EstiloForm(GestionEstilo padre) {
		this.padre = padre;
		estilo = new Estilo();

		confirmar = new NativeButton("Guardar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		nombreEstilo = new TextField("Estilo ");
		habilitado = new CheckBox("Habilitado");
		
		
			
		
		
		
		
		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();

		confirmar.addClickListener(e -> {
			padre.cargarEstilos(estilo);

			setEstilo(null);
			nombreEstilo.clear();
			
		});

		cancelar.addClickListener(e -> {

			nombreEstilo.clear();
			padre.cargarEstilos(null);
		});

		horizontal1.addComponents(nombreEstilo, habilitado);
		horizontal2.addComponents(confirmar, cancelar);

		addComponents(horizontal1, horizontal2);

	}

	public void setEstilo(Estilo estilo) {
		this.setVisible(estilo != null);
		this.estilo = estilo;

		if (estilo != null) {
			BeanFieldGroup.bindFieldsUnbuffered(estilo, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Estilo(), this);
		}
	}
}
