package es.cic.curso.curso04.ejercicio028.frontend.principal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionAutores;
import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionEstilo;
import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionObras;
import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionSubastas;
import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionTipos;;



@Theme("mytheme")
public class MyUI extends UI {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -2139001119090740717L;
	
	private TabSheet pestaniaPrincipal;
	private TabSheet pestaniaGestion;
	private VerticalLayout layout;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("120%");
		
		final HorizontalLayout hlSubastas = new GestionSubastas(this);
		hlSubastas.setMargin(true);
		hlSubastas.setSpacing(true);
		
		final HorizontalLayout hlGestion = new HorizontalLayout();
		hlGestion.setMargin(true);
		hlGestion.setSpacing(true);
		
	
		
		final HorizontalLayout hlObras = new GestionObras(this);
		hlObras.setMargin(true);
		hlObras.setSpacing(true);
		hlObras.setWidth("1700px");
		
		final HorizontalLayout hlEstilos = new GestionEstilo(this);
		hlEstilos.setMargin(true);
		hlEstilos.setSpacing(true);
		
		final HorizontalLayout hlTipos = new GestionTipos(this);
		hlTipos.setMargin(true);
		hlTipos.setSpacing(true);
		
		final HorizontalLayout hlAutores = new GestionAutores(this);
		hlAutores.setMargin(true);
		hlAutores.setSpacing(true);
		
		
		
		Label titulo = new Label("CATÁLOGO DE OBRAS DE ARTE");
		pestaniaPrincipal = new TabSheet();
		pestaniaPrincipal.setHeight(100.0f, Unit.PERCENTAGE);
		pestaniaPrincipal.addTab(hlSubastas, "SUBASTAS");
		pestaniaPrincipal.addTab(hlGestion, "GESTIÓN DE OBRAS");

		
		pestaniaGestion = new TabSheet();
		pestaniaGestion.setHeight(100.0f, Unit.PERCENTAGE);
		pestaniaGestion.addTab(hlObras, "OBRAS");
		pestaniaGestion.addTab(hlAutores, "AUTORES");
		pestaniaGestion.addTab(hlTipos, "TIPOS");
		pestaniaGestion.addTab(hlEstilos, "ESTILOS");
		
		
		
		
		
		hlGestion.addComponent(pestaniaGestion);
		layout.addComponents(titulo,pestaniaPrincipal);	
		setContent(layout);
	}
	

 
	   @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -692740140427143858L;	 
	   }
}
