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

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;

import com.vaadin.ui.HorizontalLayout;

import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import es.cic.curso.curso04.ejercicio028.backend.service.ObraService;
import es.cic.curso.curso04.ejercicio028.backend.service.TipoService;
import es.cic.curso.curso04.ejercicio028.backend.dominio.Obra;
import es.cic.curso.curso04.ejercicio028.backend.service.CategoriaService;

public class ObrasForm extends FormLayout {
	 
	private static final long serialVersionUID = -8212581707579739708L;
	private ObraService obraService;

	@SuppressWarnings("unused")
	private GestionObras padre;

	@PropertyId("titulo")
	private TextField titulo;
	
	@PropertyId("autor")
	protected ComboBox autor=new ComboBox();
	
	@PropertyId("hora")
	protected String hora;
	@PropertyId("permitido")
	protected boolean permitido;

	private NativeButton confirmar;
	private NativeButton cancelar;
	private ComboBox horas;
	private ComboBox minutos;
	private ComboBox nombreUser=new ComboBox();
	private Historico historico;
	private List<Usuario> listaUsuarios;
	private final HorizontalLayout horizontal1;
	private final HorizontalLayout horizontal2;
	private ObraService historicoService;
	private List<String> listaNombres= new ArrayList<>();
	private List<String> listaRoles;
	private CategoriaService rolService;
	private TipoService operacionService;
	List<Rol> lisRoles = new ArrayList<>();
	List<Operacion> lisOperaciones = new ArrayList<>();
	List<String> listaOperaciones = new ArrayList<>();
	private Operacion operacionElegida;
	
	public ObrasForm(GestionObras padre) {
		
		permitido = false;
		this.padre = padre;
		historico = new Historico();
		operacionElegida = new Operacion();
		obraService = ContextLoader.getCurrentWebApplicationContext().getBean(UsuarioService.class);	
		historicoService = ContextLoader.getCurrentWebApplicationContext().getBean(ObraService.class);	
		rolService = ContextLoader.getCurrentWebApplicationContext().getBean(CategoriaService.class);	
		operacionService=ContextLoader.getCurrentWebApplicationContext().getBean(TipoService.class);	
		
		horizontal1 = new HorizontalLayout();
		horizontal2 = new HorizontalLayout();
		final HorizontalLayout horizontal3 = new HorizontalLayout();		
		final HorizontalLayout espacio = new HorizontalLayout();
		
		horizontal1.setSpacing(true);
		horizontal2.setSpacing(true);
		horizontal3.setSpacing(true);
		
		List<String> listaHoras = new ArrayList<>();
		for(int i =0;i<10;i++){		
			listaHoras.add("0"+String.valueOf(i));
		}
		for(int i =10;i<24;i++){		
			listaHoras.add(String.valueOf(i));
		}	 

		List<String> listaMinutos = new ArrayList<>();
		for(int i =0;i<10;i++){
			listaMinutos.add("0"+String.valueOf(i));
		}
		for(int i =10;i<60;i++){
			listaMinutos.add(String.valueOf(i));
		}
        
        DateField date = new DateField("fecha");

    	
		
		horas = new ComboBox("Hora",listaHoras);
		horas.setNullSelectionAllowed(false);
		horas.select(1);
		horas.setImmediate(true);
		horas.setWidth(90, Unit.PIXELS);
 
		minutos = new ComboBox("Minuto",listaMinutos);
		minutos.setNullSelectionAllowed(false);
		minutos.select(1);
		minutos.setImmediate(true);
		minutos.setWidth(90, Unit.PIXELS);

 
		listaRoles = new ArrayList<>();
		
		lisRoles=rolService.listarRol();
		
		for(Rol rol :lisRoles){	
			listaRoles.add(rol.getTipoRol());
		}
			
		confirmar = new NativeButton("Registrar histórico");
		confirmar.setIcon(FontAwesome.SAVE);

		cancelar = new NativeButton("Cancelar");
		cancelar.setIcon(FontAwesome.REPLY);
		
			
		confirmar.addClickListener(e->{
			
			if(operacion.getValue()==null||horas.getValue()==null|| minutos.getValue()==null || historico.getUsuario()==null ||date.getValue()==null){	
				Notification sample = new Notification("Rellene todos los campos");
				mostrarNotificacion(sample);
			
				
			}
			else{
				
				permitido = comprobarPermiso(historico.getUsuario().getRol());
				
				DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd ");
				String convertido = fechaHora.format(date.getValue());
				historico.setHora(convertido+horas.getValue() +":"+minutos.getValue());
				historico.setPermitido(permitido);
				historicoService.aniadirHistorico(historico);
				
				Notification notificacion;
				if(permitido){
					notificacion = new Notification("ACCESO PERMITIDO");
				}else{
					notificacion = new Notification("ACCESO DENEGADO");
				}
				mostrarNotificacion(notificacion);
				
				nombreUser.clear();
				nombreUser.setVisible(false);
				
				horas.clear();
				minutos.clear();
				date.clear();
				padre.cargaHistoricos(historico);
				operacion.clear();
				operacion.setVisible(false);
				setHistorico(null);
			}
		});

		cancelar.addClickListener(e->{
			
			date.clear();
			nombreUser.clear();
			nombreUser.setVisible(false);
			horas.clear();
			minutos.clear();
			padre.cargaHistoricos(null);
			operacion.clear();
			operacion.setVisible(false);

		});
		 
		 
		horizontal3.addComponents(date,horas,minutos);

		addComponents(horizontal1,horizontal2,horizontal3,espacio,confirmar,cancelar);	
	
	}



	public void actualizarOp() {
		
		lisOperaciones = operacionService.listarOperacion();
		for(Operacion ope :lisOperaciones){
			
			listaOperaciones.add(ope.getDescripcion());	
		}
		
		
		operacion = new ComboBox("Operación",listaOperaciones);
		operacion.setNullSelectionAllowed(false);
		operacion.select(1);
		operacion.setImmediate(true);
		operacion.setWidth(300, Unit.PIXELS);
		operacion.setInputPrompt("seleccione operacion");
		
		operacion.addValueChangeListener(a->{
			for(Operacion ope :lisOperaciones){
				if(operacion.getValue()==(ope.getDescripcion())){
				
					historico.setOperacion(operacion.getValue().toString());
				}
			}			
		});
		
		horizontal2.addComponent(operacion);
		
		
	}
	

	
	public void actualizarUsuarios() {
		listaUsuarios = obraService.listarUsuario();
		listaNombres.clear();
		for(Usuario user :listaUsuarios){	
			if(user.isActivo()){
			listaNombres.add(user.getNombre());
			}
		}
		
		nombreUser = new ComboBox("Nombre",listaNombres);
		nombreUser.setNullSelectionAllowed(false);
		nombreUser.select(1);
		nombreUser.setImmediate(true);
		nombreUser.setInputPrompt("seleccione usuario");
		nombreUser.setWidth(300, Unit.PIXELS);
		
		nombreUser.addValueChangeListener(a->{
			for(Usuario user :listaUsuarios){
				if(nombreUser.getValue()==(user.getNombre())){
				
					historico.setUsuario(user);
				}
			}			
		});
		
		horizontal1.addComponent(nombreUser);
	}
	private void mostrarNotificacion(Notification notificacion) {
		notificacion.setDelayMsec(2000);
		notificacion.show(Page.getCurrent());
	}
	
	private boolean comprobarPermiso(Rol rol){
		boolean res = false;

		switch (rol.getTipoRol()) {

		case "administrador":      

			recorrerOperaciones();
			res=rol.getTipoRol().equals(operacionElegida.getNombreRol());
			break;
			
		case "supervisor":
			recorrerOperaciones();
			res=rol.getTipoRol().equals(operacionElegida.getNombreRol());
			break;
			
		case "editor":
			recorrerOperaciones();
			res=rol.getTipoRol().equals(operacionElegida.getNombreRol());
			break;
			
		case "invitado":
			recorrerOperaciones();
			res=rol.getTipoRol().equals(operacionElegida.getNombreRol());
			break;

		case "personal de mantenimiento":
			recorrerOperaciones();
			res=rol.getTipoRol().equals(operacionElegida.getNombreRol());
			break;

		case "personal RR.HH":
			recorrerOperaciones();
			res=rol.getTipoRol().equals(operacionElegida.getNombreRol());
			break;

	        }	
		
		
		return res;	
	}
	private void recorrerOperaciones() {
		for(Operacion ope :lisOperaciones){
			if(operacion.getValue()==ope.getDescripcion()){
				operacionElegida.setNombreRol(ope.getNombreRol());
				
			}			
		}
	}
	
	public void setHistorico(Historico historico) {
		this.setVisible(historico != null);
		this.historico = historico;

		if (historico != null) {
			BeanFieldGroup.bindFieldsUnbuffered(historico, this);
		} else {
			BeanFieldGroup.bindFieldsUnbuffered(new Historico(), this);
		}
	}

}

