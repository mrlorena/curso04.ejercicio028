package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;


public class AutoresForm extends FormLayout {

	private static final long serialVersionUID = -8212581707579739708L;

	@PropertyId("nombre")
	private TextField nombre;

	@PropertyId("fechaNacimiento")
	private TextField fechaNacimiento;
	
	@PropertyId("habilitado")
	private CheckBox habilitado;

	private NativeButton confirmar;
	private NativeButton cancelar;

	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private final HorizontalLayout horizontal3;

	@SuppressWarnings("unused")
	private GestionAutores padre;

	private Autor autor;

	public AutoresForm(GestionAutores padre) {
		this.padre = padre;
		autor = new Autor();
		
		confirmar = new NativeButton("Guardar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		nombre = new TextField("Nombre");
		fechaNacimiento = new TextField("Fecha de nacimiento");
		habilitado = new CheckBox("Habilitado");

		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		horizontal3 = new HorizontalLayout();

		confirmar.addClickListener(e -> {
			padre.cargarAutores(autor);
			
		});

		cancelar.addClickListener(e -> {
			padre.cargarAutores(null);
		});

		horizontal1.addComponents(nombre);
		horizontal2.addComponents(fechaNacimiento, habilitado);
		horizontal3.addComponents(confirmar, cancelar);

		addComponents(horizontal1, horizontal2, horizontal3);

		setAutor(null);
	}

	public void setAutor(Autor autor) {
		this.setVisible(autor != null);
		this.autor = autor;

		if (autor != null) {
			BeanFieldGroup.bindFieldsUnbuffered(autor, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Autor(), this);
		}
	}

}