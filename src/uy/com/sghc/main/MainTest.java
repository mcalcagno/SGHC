package uy.com.sghc.main;

import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.persistencia.controladores.ControlPersistirPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;


public class MainTest {

	
	public static void main(String[] args){
		
		IPersistirPaciente i = new ControlPersistirPaciente();
		
		
		Paciente p1 = new Paciente();
		p1.setPrimerNombre("Juan");
		p1.setSegundoNombre("Pedro");
		p1.setPrimerApellido("Perez");
		p1.setSegundoApellido("Martinez");
		p1.setCi(new Long(123456789));
		p1.setDireccion("Yaguaron 1414");
		p1.setMail("juanp@paciente.com.uy");
		p1.setTelefono("29002711");
		
		Paciente p2 = new Paciente();
		p2.setPrimerNombre("Pedro");
		p2.setSegundoNombre("Arnaldo");
		p2.setPrimerApellido("Fagundez");
		p2.setSegundoApellido("Nunez");
		p2.setCi(new Long(50489462));
		p2.setDireccion("Paysandu 1313");
		p2.setMail("pedro@paciente.com.uy");
		p2.setTelefono("29002711");
		
		try {
			i.crearPaciente(p1);
			i.crearPaciente(p2);
			p1.setDireccion("cambie la direccion como un cra");
			i.editarPaciente(p1);
			i.borrarPaciente(new Long(50489462));
			Paciente p3 = i.obtenerPaciente(new Long(123456789));
			System.out.print(p3.toString());
		} catch (SGHCExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
