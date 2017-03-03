package es.cic.curso.curso04.ejercicio028.frontend.utilities;

public class Validador {

	public Validador(){
		super();
		
	}
	
	public boolean validarEmail(String email){
		
		String patronEmail="^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$";
		
		return email.matches(patronEmail);
	}
	
	public boolean validarLongitudTexto(String numCaracteres){
		
		String patronNumCaracteres = "[a-zA-Z0-9]{1,50}";
		
		return numCaracteres.matches(patronNumCaracteres);
		
	}
	
	public boolean validarNombreApellidos(String nombreApellidos){
		String patronNombreApellidos= "[a-zA-Z\\s\\u00E0-\\u00FC]{1,50}";
		
		return nombreApellidos.matches(patronNombreApellidos);
	}
	
	
	
	
}
