package es.cic.curso.curso04.ejercicio028.frontend.secundarios;


import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;

public class GestionObras  extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6683850118394414599L;

	private NativeButton aniadirHistorico;
	Grid gridHistorico;
	private ObrasForm detalleHistorico;
 
	@SuppressWarnings("unused")
	private MyUI padre;


	
	public GestionObras(MyUI padre){
		this.padre = padre;
		
	}
	private void aniadirObra(Obra obra) {	
		
		
	}


	public void cargarObra(Obra obra){	
		
		

	}
}