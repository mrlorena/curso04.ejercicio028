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

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
import es.cic.curso.curso04.ejercicio028.backend.dto.ObraConverter;
import es.cic.curso.curso04.ejercicio028.backend.dto.ObraDTO;
import es.cic.curso.curso04.ejercicio028.backend.service.EstiloService;
import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionObras  extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2600433507457283447L;
	private TipoService tipoService;
	private ObraService obraService;
	private EstiloService estiloService;
	private ObrasForm detalleObras;
	private List<ObraDTO> listaObras = new ArrayList<>();
	private List<Obra> listaObr = new ArrayList<>();
	private List<Tipo> listaTipos = new ArrayList<>();
	private List<Estilo> listaEstilos = new ArrayList<>();
	
	private ObraConverter obraConverter = new ObraConverter();
	
	private NativeButton aniadirObra;
	private Grid gridObras;
	
	
	@SuppressWarnings("unused")
	private MyUI padre;


	
	public GestionObras(MyUI padre){
		this.padre = padre;
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);
		tipoService = ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);
		estiloService = ContextLoader.getCurrentWebApplicationContext().getBean(EstiloService.class);
		
		

		if(listaObr.isEmpty()){	
			obraService.generaBBDD();
		}
		
		listaObras = new ArrayList<>();
		listaObr = new ArrayList<>();
		listaTipos = new ArrayList<>();
		listaEstilos = new ArrayList<>();
		
		listaTipos = tipoService.listarTipo();
		listaEstilos = estiloService.listarEstilo();
		listaObr = obraService.listarObra();
		
		listaObras = obraConverter.entity2DTO(listaObr, listaTipos, listaEstilos);
		
		aniadirObra = new NativeButton("Añadir Obra");
		aniadirObra.setIcon(FontAwesome.PLUS);
		
		gridObras = new Grid();
		gridObras.setWidth(820, Unit.PIXELS);	
		gridObras.setColumns("titulo","autor","anio","tipo","estilo","precio","imagen");	
		gridObras.setFrozenColumnCount(1);
		gridObras.setSelectionMode(SelectionMode.NONE);	
		
		detalleObras = new ObrasForm(this);
		aniadirObra.addClickListener(e->{	
			aniadirObra.setVisible(false);
			detalleObras.actualizarTipo();
			detalleObras.actualizarEstilo();
			aniadirObra();
		});
		
		cargarObras(null);
		addComponents(gridObras,aniadirObra,detalleObras);	
	}
	

	private void aniadirObra() {	
		detalleObras.setVisible(true);
		
		Obra obra = new Obra("","",0,null,null,0,"");
		detalleObras.setObra(obra);
	}


	public void cargarObras(Obra obra){	
		List<Tipo> listaTipos = tipoService.listarTipo();
		List<Estilo> listaEstilos = estiloService.listarEstilo();
		aniadirObra.setVisible(true);
		detalleObras.setVisible(false);
		
		if(obra!=null){
			Tipo tipo = null;
			Estilo estilo = null;
			
			for(Tipo t: listaTipos){
				
				if(obra.getTipo().getNombreTipo().equals(t.getNombreTipo())){
					tipo = new Tipo();
					tipo = obra.getTipo();
					
				}
			}	
				
			for(Estilo e: listaEstilos){
				
				if(obra.getEstilo().getNombreEstilo().equals(e.getNombreEstilo())){
					estilo = new Estilo();
					estilo = obra.getEstilo();		
				}
			}
			ObraDTO obraDTO = new ObraDTO();	
			obraDTO = obraConverter.entityToDto(obra,tipo,estilo);
			listaObras.add(obraDTO);
		}	
		

		gridObras.setContainerDataSource(
				new BeanItemContainer<>(ObraDTO.class, listaObras)
				);
		detalleObras.setObra(null);
	}
}
