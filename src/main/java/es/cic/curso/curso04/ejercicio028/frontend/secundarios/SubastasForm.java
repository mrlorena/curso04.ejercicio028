package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.SubastaService;

public class SubastasForm extends FormLayout {

	private static final long serialVersionUID = -8212581707579739708L;

	@SuppressWarnings("unused")
	private GestionSubastas padre;

	@PropertyId("obra")
	private Obra obra;

	@PropertyId("pujaInicial")
	private TextField pujaInicial;

	@PropertyId("precioVenta")
	private TextField precioVenta;

	@PropertyId("fechaInicio")
	private String convertidoFechaInicial;

	@PropertyId("fechaFin")
	private String convertidoFechaFin;

	@PropertyId("activa")
	private CheckBox activa;

	private Subasta subasta;
	private ObraService obraService;
	private SubastaService subastaService;

	private List<String> listaObras;
	private List<Obra> listaNombreObras;
	private List<Subasta> listaSubasta;

	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private final HorizontalLayout horizontal3;
	private final HorizontalLayout horizontal4;

	private NativeButton confirmar;
	private NativeButton cancelar;
	
	DateField dateInicio;
	DateField dateFin;
	DateFormat fecha;
	private ComboBox cbObras;

	public SubastasForm(GestionSubastas padre) {

		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);
		subastaService = ContextLoader.getCurrentWebApplicationContext().getBean(SubastaService.class);
		subasta = new Subasta();

		listaObras = new ArrayList<>();
		for (Obra t : obraService.listarObra()) {
			listaObras.add(t.getTitulo());
		}

		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		horizontal3 = new HorizontalLayout();
		horizontal4 = new HorizontalLayout();

		horizontal1.setSpacing(true);
		horizontal2.setSpacing(true);
		horizontal3.setSpacing(true);
		horizontal4.setSpacing(true);

		pujaInicial = new TextField("Puja inicial *");
		precioVenta = new TextField("Precio venta");
		dateInicio = new DateField("Fecha Inicio *");
		dateFin = new DateField("Fecha fin *");
		activa = new CheckBox("Activa");
	


		confirmar = new NativeButton("Registrar");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);

		confirmar.addClickListener(e -> {
			listaSubasta = new ArrayList<>();
			listaSubasta = subastaService.listarSubasta();
			Notification notificacion;
			notificacion = new Notification("No es posible volver a subastar esta obra");

			fecha = new SimpleDateFormat("dd-MM-yyyy");
			
			if (listaSubasta.isEmpty()) {
				
				
				convertidoFechaInicial = fecha.format(dateInicio.getValue());
				convertidoFechaFin = fecha.format(dateFin.getValue());
				
				subasta.setFechaInicio(convertidoFechaInicial);
				subasta.setFechaFin(convertidoFechaFin);
				
				subastaService.aniadirSubasta(subasta);
				cbObras.setVisible(false);

				
				System.out.print("inicio"+convertidoFechaInicial);
				System.out.print("fin"+convertidoFechaFin);
				
				padre.cargarSubastas(subasta);

				setSubasta(null);

			} else {

				for (Subasta s : listaSubasta) {
					if (cbObras.getValue() == s.getObra().getTitulo()) {
						mostrarNotificacion(notificacion);
						padre.cargarSubastas(null);

					} else {


						convertidoFechaInicial = fecha.format(dateInicio.getValue());
						convertidoFechaFin = fecha.format(dateFin.getValue());
						
						subasta.setFechaInicio(convertidoFechaInicial);
						subasta.setFechaFin(convertidoFechaFin);
						
						subastaService.aniadirSubasta(subasta);
						cbObras.setVisible(false);

						padre.cargarSubastas(subasta);

						// pujaInicial.clear();
						// precioVenta.clear();
						// fechaInicio.clear();
						// fechaFin.clear();
						// activa.clear();

						// cbObras.clear();

						setSubasta(null);

					}
				}

			}

		});

		cancelar.addClickListener(e -> {

		//	pujaInicial.clear();
		//	precioVenta.clear();
		//	fechaInicio.clear();
		//	fechaFin.clear();
		//	activa.clear();
			padre.cargarSubastas(null);
			cbObras.setVisible(false);
			cbObras.clear();

		});

		horizontal2.addComponents(pujaInicial, precioVenta);
		horizontal3.addComponents(dateInicio, dateFin, activa);
		horizontal4.addComponents(confirmar, cancelar);

		addComponents(horizontal1, horizontal2, horizontal3, horizontal4);
	}

	public void actualizarObras() {
		listaNombreObras = obraService.listarObra();

		listaObras.clear();

		for (Obra a : listaNombreObras) {

			listaObras.add(a.getTitulo());
		}

		cbObras = new ComboBox("Título de la obra", listaObras);
		cbObras.setNullSelectionAllowed(false);
		cbObras.select(1);
		cbObras.setImmediate(true);
		cbObras.setWidth(90, Unit.PIXELS);
		cbObras.setInputPrompt("seleccione la obra");

		cbObras.addValueChangeListener(a -> {
			for (Obra au : listaNombreObras) {
				if (cbObras.getValue() == (au.getTitulo())) {

					subasta.setObra(au);
				}
			}
		});

		horizontal1.addComponent(cbObras);
	}

	private void mostrarNotificacion(Notification notificacion) {
		notificacion.setDelayMsec(2000);
		notificacion.show(Page.getCurrent());
	}

	public void setSubasta(Subasta subasta) {
		this.setVisible(subasta != null);
		this.subasta = subasta;

		if (subasta != null) {
			BeanFieldGroup.bindFieldsUnbuffered(subasta, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Subasta(), this);
		}
	}

}
