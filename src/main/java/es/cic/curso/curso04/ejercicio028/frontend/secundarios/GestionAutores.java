package es.cic.curso.curso04.ejercicio028.frontend.secundarios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Autor;
import es.cic.curso.curso04.ejercicio028.backend.service.AutorService;
import es.cic.curso.curso04.ejercicio028.frontend.principal.MyUI;


public class GestionAutores extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7897900102340873208L;
	
	private AutorService autorService;
	private List<Autor> listaAutores;
	private List<String> listaNombres;

	private AutoresForm detalleAutor;
	private NativeButton aniadirAutor;
	private NativeButton modificar;
	private NativeButton cancelar;
	
	private Grid gridAutores;
	private final VerticalLayout extra;
	private final VerticalLayout vertical;
	private ComboBox autores=new ComboBox();
	
	@SuppressWarnings("unused")
	private MyUI padre;
	

	public GestionAutores(MyUI padre){
		this.padre = padre;
		
		vertical = new VerticalLayout();
		vertical.setSpacing(true);
		
		extra = new VerticalLayout();
		extra.setSpacing(true);

		autorService = ContextLoader.getCurrentWebApplicationContext().getBean(AutorService.class);	

		listaAutores = new ArrayList<>();
		listaAutores = autorService.listarAutor();
		
		listaNombres = new ArrayList<>();
		
		aniadirAutor = new NativeButton("Añadir Autor");
		aniadirAutor.setIcon(FontAwesome.PLUS);
		
		modificar = new NativeButton("modificar");
		modificar.setIcon(FontAwesome.PENCIL);
		
		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);
		
		gridAutores = new Grid();
		gridAutores.setWidth(820, Unit.PIXELS);	
		gridAutores.setColumns("nombre", "fechaNacimiento");
		gridAutores.setFrozenColumnCount(1);
		gridAutores.setSelectionMode(SelectionMode.NONE);	
		
		detalleAutor = new AutoresForm(this);
		aniadirAutor.addClickListener(e->{	
			aniadirAutor.setVisible(false);
			modificar.setVisible(false);
			aniadirAutor();
		});
		
	
	modificar.addClickListener(e-> modificarAutor());

		vertical.addComponents(aniadirAutor,modificar,extra, detalleAutor);
		addComponents(gridAutores,vertical);

		
		cargarAutores(null);
		
		
		
		
	}

	private void aniadirAutor() {	
		detalleAutor.setVisible(true);
		
		Autor autor = new Autor("",0);
		detalleAutor.setAutor(autor);
	
	}
	
	public void modificarAutor(){
		listaAutores = autorService.listarAutor();
		listaNombres.clear();
		for(Autor autor :listaAutores){			
		 
			listaNombres.add(autor.getNombre());
			 
		}
		autores = new ComboBox("Nombre",listaNombres);
		autores.setInputPrompt("Seleccione usuario a modificar");
		autores.setNullSelectionAllowed(false);
		autores.select(1);
		autores.setImmediate(true);
		autores.setWidth(300, Unit.PIXELS);

		
		aniadirAutor.setVisible(false);
		modificar.setVisible(false);
		autores.setVisible(true);
		cancelar.setVisible(true);

		extra.addComponents(autores,cancelar);

		autores.addValueChangeListener(a->{


			for(Autor au :listaAutores){
				if(autores.getValue()==(au.getNombre())){
					detalleAutor.setVisible(true);
					detalleAutor.setAutor(au);
					cancelar.setVisible(false);
				}					
			}		
		});

		cancelar.addClickListener(a->{
		
			autores.setVisible(false);
			cancelar.setVisible(false);
			autores.clear();
			aniadirAutor.setVisible(true);
			modificar.setVisible(true);
						
		});
				
		
	}
	
	
	public void cargarAutores(Autor autor) {
		listaAutores = autorService.listarAutor();
		aniadirAutor.setVisible(true);
		modificar.setVisible(true);
		detalleAutor.setVisible(false);
		
		if(!listaAutores.isEmpty()){
			gridAutores.setContainerDataSource(
					new BeanItemContainer<>(Autor.class, listaAutores)
					);
		}
			detalleAutor.setAutor(null);
		
		
	
	}
}