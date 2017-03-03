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
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import es.cic.curso.curso04.ejercicio028.backend.dominio.Estilo;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Tipo;
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
	protected ComboBox autor=new ComboBox();
	
	@PropertyId("anio")
	private TextField txAnio;
	
	@PropertyId("tipo")
	private Tipo nombreTipo;
	
	@PropertyId("estilo")
	private Estilo nombreEstilo;
	
	@PropertyId("precio")
	private TextField txPrecio;
	
	@PropertyId("imagen")
	private TextField txImagen;
	

	private Obra obra;
	private ObraService obraService;
	private EstiloService estiloService;
	private TipoService tipoService;
	private List<String> listaAutores;
	private List<String> listaTipos;
	private List<String> listaEstilos;
	
	private List<Tipo> listaNombreTipos;
	private List<Estilo> listaNombreEstilos;
	
	
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
		
		obra = new Obra();
		
		listaAutores = new ArrayList<>();
		listaAutores.add("aaa");
		listaAutores.add("bbb");
		listaAutores.add("ccc");
		
		listaTipos = new ArrayList<>();
		for(Tipo t :tipoService.listarTipo()){	
			listaTipos.add(t.getNombreTipo());
		}
		
		listaEstilos = new ArrayList<>();
		for(Estilo e :estiloService.listarEstilo()){	
			listaEstilos.add(e.getNombreEstilo());
		}
		
		
		
		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		horizontal3 = new HorizontalLayout();
		horizontal4 = new HorizontalLayout();
		
		horizontal1.setSpacing(true);
		horizontal2.setSpacing(true);
		horizontal3.setSpacing(true);
		horizontal4.setSpacing(true);
		
		
		cbAutores = new ComboBox("Autor",listaAutores);
		cbAutores.setNullSelectionAllowed(false);
		cbAutores.select(1);
		cbAutores.setImmediate(true);
		cbAutores.setWidth(90, Unit.PIXELS);
		
		
		txTitulo = new TextField("Titulo *");
		txAnio = new TextField("Año");
		txPrecio = new TextField("Precio *");
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

			public File file;
        	
    			OutputStream outputFile = null;

    			@Override
    			public OutputStream receiveUpload(String strFilename, String strMIMEType) {
    				File file = null;
    				try {
    					file = new File("/src/main/webapp/WEB-INF" + strFilename);
    					if (!file.exists()) {
    						file.createNewFile();
    					}
    					outputFile = new FileOutputStream(file);
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    				return outputFile;
    			}

    			protected void finalize() {
    				try {
    					super.finalize();
    					if (outputFile != null) {
    						outputFile.close();
    					}
    				} catch (Throwable exception) {
    					exception.printStackTrace();
    				}
    			
            
		       
		    }

		    public void uploadSucceeded(SucceededEvent event) {
		        image.setSource(new FileResource(file));
		    }
		};
		ImageUploader receiver = new ImageUploader();

		Upload upload = new Upload("Seleccione la imagen", receiver);
		upload.addSucceededListener(receiver);
		upload.setButtonCaption("Guardar");
		
		
		
		confirmar.addClickListener(e->{
				obra.setAutor(cbAutores.getValue().toString());
				obraService.aniadirObra(obra);
				
			
				padre.cargarObras(obra);
				
				setObra(null);
				
				txTitulo.clear();
				txImagen.clear();
				txAnio.clear();
				txPrecio.clear();
				
				cbEstilos.setVisible(false);
				cbTipos.setVisible(false);
				cbAutores.clear();
				cbEstilos.clear();
				cbTipos.clear();
		
			
		});

		cancelar.addClickListener(e->{
			
			txTitulo.clear();
			txImagen.clear();
			txAnio.clear();
			txPrecio.clear();
			
			cbEstilos.setVisible(false);
			cbTipos.setVisible(false);
			cbAutores.clear();
			cbEstilos.clear();
			cbTipos.clear();
			
			padre.cargarObras(null);
			
		
		});
		horizontal1.addComponents(txTitulo,txAnio);
		horizontal2.addComponents(cbAutores);
		horizontal3.addComponents(txPrecio, upload);
		horizontal4.addComponents(confirmar, cancelar);

		addComponents(horizontal1,horizontal2,horizontal3,horizontal4);	
	}
	
	public void actualizarEstilo() {
		listaNombreEstilos = estiloService.listarEstilo();
		listaEstilos.clear();
		
		for(Estilo e :listaNombreEstilos){	
			
			listaEstilos.add(e.getNombreEstilo());	
		}
		
		cbEstilos = new ComboBox("Estilo",listaEstilos);
		cbEstilos.setNullSelectionAllowed(false);
		cbEstilos.select(1);
		cbEstilos.setImmediate(true);
		cbEstilos.setWidth(90, Unit.PIXELS);
		cbEstilos.setInputPrompt("seleccione estilo de la obra");
		
		cbTipos.addValueChangeListener(a->{
			for(Estilo e : listaNombreEstilos){
				if(cbEstilos.getValue()==(e.getNombreEstilo())){
				
					obra.setEstilo(e);
				}
			}							
		});
		
		horizontal2.addComponent(cbEstilos);
	}

	public void actualizarTipo() {
		listaNombreTipos = tipoService.listarTipo();
		listaTipos.clear();
		
		for(Tipo t :listaNombreTipos){	
			
			listaTipos.add(t.getNombreTipo());	
		}
		
		cbTipos = new ComboBox("Tipo *",listaTipos);
		cbTipos.setNullSelectionAllowed(false);
		cbTipos.select(1);
		cbTipos.setImmediate(true);
		cbTipos.setWidth(90, Unit.PIXELS);
		cbTipos.setInputPrompt("seleccione tipo de obra");
		
		cbTipos.addValueChangeListener(a->{
			for(Tipo t :listaNombreTipos){
				if(cbTipos.getValue()==(t.getNombreTipo())){
				
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

