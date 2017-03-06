package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.service.AutorService;

public class AutoresForm extends FormLayout {

	private static final long serialVersionUID = -8212581707579739708L;

	@PropertyId("nombre")
	private TextField nombre;

	@PropertyId("fechaNacimiento")
	private TextField fechaNacimiento;

	private NativeButton confirmar;
	private NativeButton cancelar;

	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private final HorizontalLayout horizontal3;

	@SuppressWarnings("unused")
	private GestionAutores padre;
	private AutorService autorService;

	private Autor autor;

	public AutoresForm(GestionAutores padre) {
		this.padre = padre;
		autor = new Autor();
		autorService = ContextLoader.getCurrentWebApplicationContext().getBean(AutorService.class);

		confirmar = new NativeButton("Guardar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		nombre = new TextField("Nombre");
		fechaNacimiento = new TextField("Fecha de nacimiento");

		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		horizontal3 = new HorizontalLayout();

		confirmar.addClickListener(e -> {

			autorService.aniadirAutor(autor);
			padre.cargarAutores(autor);

			// setAutor(null);
			nombre.clear();
			fechaNacimiento.clear();
		});

		cancelar.addClickListener(e -> {

			padre.cargarAutores(null);
			nombre.clear();
			fechaNacimiento.clear();
		});

		horizontal1.addComponents(nombre);
		horizontal2.addComponent(fechaNacimiento);
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