package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.service.AutorService;
import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;

public class ObrasForm extends FormLayout {

	private static final long serialVersionUID = -8212581707579739708L;

	@SuppressWarnings("unused")
	private GestionObras padre;

	@PropertyId("titulo")
	private TextField txTitulo;

	@PropertyId("autor")
	private Autor nombre;

	@PropertyId("anio")
	private TextField txAnio;

	@PropertyId("tipo")
	private Tipo nombreTipo;

	@PropertyId("estilo")
	private Estilo nombreEstilo;

	@PropertyId("habilitada")
	private CheckBox habilitada;

	@PropertyId("imagen")
	private TextField txImagen;

	private Obra obra;
	private ObraService obraService;
	private EstiloService estiloService;
	private TipoService tipoService;
	private AutorService autorService;

	private List<String> listaAutores;
	private List<String> listaTipos;
	private List<String> listaEstilos;

	private List<Tipo> listaNombreTipos;
	private List<Estilo> listaNombreEstilos;
	private List<Autor> listaNombreAutores;

	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private final HorizontalLayout horizontal3;
	private final HorizontalLayout horizontal4;

	private NativeButton confirmar;
	private NativeButton cancelar;

	private ComboBox cbAutores;
	private ComboBox cbTipos;
	private ComboBox cbEstilos;

	public ObrasForm(GestionObras padre) {

		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);
		estiloService = ContextLoader.getCurrentWebApplicationContext().getBean(EstiloService.class);
		tipoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);
		autorService = ContextLoader.getCurrentWebApplicationContext().getBean(AutorService.class);

		obra = new Obra();

		listaTipos = new ArrayList<>();
		for (Tipo t : tipoService.listarTipo()) {
			listaTipos.add(t.getNombreTipo());
		}

		listaEstilos = new ArrayList<>();
		for (Estilo e : estiloService.listarEstilo()) {
			listaEstilos.add(e.getNombreEstilo());
		}

		listaAutores = new ArrayList<>();
		for (Autor a : autorService.listarAutor()) {
			listaAutores.add(a.getNombre());
		}

		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		horizontal3 = new HorizontalLayout();
		horizontal4 = new HorizontalLayout();

		horizontal1.setSpacing(true);
		horizontal2.setSpacing(true);
		horizontal3.setSpacing(true);
		horizontal4.setSpacing(true);

		txTitulo = new TextField("Título *");
		txAnio = new TextField("Año");
		habilitada = new CheckBox("Habilitada");
		txImagen = new TextField("Imagen *");

		confirmar = new NativeButton("Registrar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		final Image image = new Image("Cargar imagen");

		class ImageUploader implements Receiver, SucceededListener {
			/**
			 * 
			 */
			private static final long serialVersionUID = -994472642239542333L;

			private File file;
			private OutputStream outputFile = null;

			@Override
			public OutputStream receiveUpload(String strFilename, String strMIMEType) {

				FileOutputStream fos = null;

				try {
					file = new File("C:\\Documents and Settings\\ABDEN00U\\Desktop\\tmp\\" + strFilename);
					fos = new FileOutputStream(file);
					fos.close();
				} catch (final IOException e) {
					new Notification("Could not open file", e.getMessage(), Type.ERROR_MESSAGE).show(Page.getCurrent());
					return null;
				}

				return fos;
			}

			protected void finalize() {
				try {
					super.finalize();
					if (outputFile != null) {
						outputFile.close();
					}
				} catch (Throwable exception) {
					// exception.printStackTrace();
				}

			}

			public void uploadSucceeded(SucceededEvent event) {
				image.setSource(new FileResource(file));
			}

		}
		;
		ImageUploader receiver = new ImageUploader();

		Upload upload = new Upload("Seleccione la imagen", receiver);
		upload.addSucceededListener(receiver);
		upload.setButtonCaption("Guardar");

		confirmar.addClickListener(e -> {
			obraService.aniadirObra(obra);
			cbEstilos.setVisible(false);
			cbTipos.setVisible(false);
			cbAutores.setVisible(false);
			padre.cargarObras(obra);

			txTitulo.clear();
			txImagen.clear();
			txAnio.clear();
			habilitada.clear();
			cbAutores.clear();
			cbEstilos.clear();
			cbTipos.clear();

			setObra(null);
		});

		cancelar.addClickListener(e -> {

			padre.cargarObras(null);
			txTitulo.clear();
			txImagen.clear();
			txAnio.clear();
			habilitada.clear();

			cbEstilos.setVisible(false);
			cbTipos.setVisible(false);
			cbAutores.setVisible(false);
			cbAutores.clear();
			cbEstilos.clear();
			cbTipos.clear();

		});
		horizontal1.addComponents(txTitulo, txAnio);

		horizontal3.addComponents(habilitada, upload);
		horizontal4.addComponents(confirmar, cancelar);

		addComponents(horizontal1, horizontal2, horizontal3, horizontal4);
	}

	public void actualizarAutor() {
		listaNombreAutores = autorService.listarAutor();

		listaAutores.clear();

		for (Autor a : listaNombreAutores) {

			listaAutores.add(a.getNombre());
		}

		cbAutores = new ComboBox("Autor", listaAutores);
		cbAutores.setNullSelectionAllowed(false);
		cbAutores.select(1);
		cbAutores.setImmediate(true);
		cbAutores.setWidth(90, Unit.PIXELS);
		cbAutores.setInputPrompt("seleccione un autor");

		cbAutores.addValueChangeListener(a -> {
			for (Autor au : listaNombreAutores) {
				if (cbAutores.getValue() == (au.getNombre())) {
					obra.setAutor(au);
				}
			}
		});

		horizontal2.addComponent(cbAutores);
	}

	public void actualizarEstilo() {
		listaNombreEstilos = estiloService.listarEstilo();
		listaEstilos.clear();

		for (Estilo e : listaNombreEstilos) {

			listaEstilos.add(e.getNombreEstilo());
		}

		cbEstilos = new ComboBox("Estilo *", listaEstilos);
		cbEstilos.setNullSelectionAllowed(false);
		cbEstilos.select(1);
		cbEstilos.setImmediate(true);
		cbEstilos.setWidth(90, Unit.PIXELS);
		cbEstilos.setInputPrompt("seleccione estilo de la obra");

		cbEstilos.addValueChangeListener(a -> {
			for (Estilo e : listaNombreEstilos) {
				if (cbEstilos.getValue() == (e.getNombreEstilo())) {

					obra.setEstilo(e);
				}
			}
		});

		horizontal2.addComponent(cbEstilos);
	}

	public void actualizarTipo() {
		listaNombreTipos = tipoService.listarTipo();
		listaTipos.clear();

		for (Tipo t : listaNombreTipos) {

			listaTipos.add(t.getNombreTipo());
		}

		cbTipos = new ComboBox("Tipo *", listaTipos);
		cbTipos.setNullSelectionAllowed(false);
		cbTipos.select(1);
		cbTipos.setImmediate(true);
		cbTipos.setWidth(90, Unit.PIXELS);
		cbTipos.setInputPrompt("seleccione tipo de obra");

		cbTipos.addValueChangeListener(a -> {
			for (Tipo t : listaNombreTipos) {
				if (cbTipos.getValue() == (t.getNombreTipo())) {

					obra.setTipo(t);
				}
			}
		});

		horizontal2.addComponent(cbTipos);
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
