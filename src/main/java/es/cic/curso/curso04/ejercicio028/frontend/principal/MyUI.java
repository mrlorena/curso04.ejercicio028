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

import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionCategorias;
import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionObras;
import es.cic.curso.curso04.ejercicio028.frontend.secundarios.GestionTipos;



@Theme("mytheme")
public class MyUI extends UI {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = -2139001119090740717L;
	private TabSheet pestania;
	private VerticalLayout layout;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("100%");

		final HorizontalLayout hlTipos = new GestionTipos(this);
		hlTipos.setMargin(true);
		hlTipos.setSpacing(true);
		final HorizontalLayout hlObras = new GestionObras(this);
		hlObras.setMargin(true);
		hlObras.setSpacing(true);
		final HorizontalLayout hlCategorias = new GestionCategorias(this);
		hlCategorias.setMargin(true);
		hlCategorias.setSpacing(true);
		
		
		Label titulo = new Label("CATÁLOGO DE OBRAS DE ARTE");
		pestania = new TabSheet();
		pestania.setHeight(100.0f, Unit.PERCENTAGE);
		pestania.addTab(hlObras, "OBRAS");
		pestania.addTab(hlTipos, "TIPOS");
		pestania.addTab(hlCategorias, "CATEGORIAS");

		layout.addComponents(titulo,pestania);	
		setContent(layout);
	}
	

 
	   @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = -692740140427143858L;	 
	   }
}
