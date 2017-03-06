package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Subasta;
import es.cic.curso.curso04.ejercicio028.backend.dto.SubastaConverter;
import es.cic.curso.curso04.ejercicio028.backend.dto.SubastaDTO;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionSubastas extends HorizontalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4896505037435108185L;

	@SuppressWarnings("unused")
	private MyUI padre;
	private SubastasForm detalleSubastas;
	private List<SubastaDTO> listaSubastasDTO;

	private ObraService obraService;
	private SubastaConverter subastaConverter = new SubastaConverter();

	private NativeButton aniadirSubasta;
	private Grid gridSubastas;

	public GestionSubastas(MyUI padre) {
		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);

		listaSubastasDTO = new ArrayList<>();

		aniadirSubasta = new NativeButton("Crear Subasta");
		aniadirSubasta.setIcon(FontAwesome.PLUS);

		gridSubastas = new Grid();
		gridSubastas.setWidth(820, Unit.PIXELS);
		gridSubastas.setColumns("obra", "pujaInicial", "precioVenta", "fechaInicio", "fechaFin", "activa");
		gridSubastas.setFrozenColumnCount(1);
		gridSubastas.setSelectionMode(SelectionMode.NONE);

		detalleSubastas = new SubastasForm(this);
		aniadirSubasta.addClickListener(e -> {
			aniadirSubasta.setVisible(false);
			detalleSubastas.actualizarObras();

			aniadirSubasta();
		});

		cargarSubastas(null);
		addComponents(gridSubastas, aniadirSubasta, detalleSubastas);

	}

	private void aniadirSubasta() {
		detalleSubastas.setVisible(true);

		Subasta subasta = new Subasta(null, 0, 0, "", "", false);
		detalleSubastas.setSubasta(subasta);

		gridSubastas.setContainerDataSource(new BeanItemContainer<>(SubastaDTO.class, listaSubastasDTO));
	}

	public void cargarSubastas(Subasta subasta) {
		List<Obra> listaObras = obraService.listarObra();
		aniadirSubasta.setVisible(true);
		detalleSubastas.setVisible(false);

		if (subasta != null) {
			Obra obra = null;

			for (Obra t : listaObras) {

				if (subasta.getObra().getTitulo().equals(t.getTitulo())) {
					obra = new Obra();
					obra = subasta.getObra();

				}
			}

			SubastaDTO subastaDTO = new SubastaDTO();
			subastaDTO = subastaConverter.entityToDto(subasta, obra);
			listaSubastasDTO.add(subastaDTO);
		}

		gridSubastas.setContainerDataSource(new BeanItemContainer<>(SubastaDTO.class, listaSubastasDTO));
		detalleSubastas.setSubasta(null);
	}

}
