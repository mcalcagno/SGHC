package uy.com.sghc.persistencia.controladores;

import java.util.LinkedList;
import java.util.List;

import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;
import uy.com.sghc.persistencia.manejadores.ManejadorXml;

public class ControlPersistirPaciente implements IPersistirPaciente{

	@Override
	public void crearPaciente(final Paciente paciente) throws SGHCExcepcion{		
		ManejadorXml manejador = ManejadorXml.newInstance();
		manejador.persistirNuevoPaciente(paciente);
	}

	@Override
	public void borrarPaciente(final Long cedula) throws SGHCExcepcion {	
		ManejadorXml manejador = ManejadorXml.newInstance();
		manejador.borrarPaciente(cedula);		
	}

	@Override
	public void editarPaciente(final Paciente paciente) throws SGHCExcepcion {		
		ManejadorXml manejador = ManejadorXml.newInstance();
		manejador.editarPaciente(paciente);		
	}

	@Override
	public Paciente obtenerPaciente(final Long cedula) throws SGHCExcepcion {
		ManejadorXml manejador = ManejadorXml.newInstance();
		Paciente paciente = manejador.obtenetPaciente(cedula);
		return paciente;		
	}

	@Override
	public List<Ficha> obtenerFichasPaciente(final Long cedula) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Paciente> buscarPacientesPorString(final String busqueda) throws SGHCExcepcion {
		// TODO: solo para probar es esto
		List<Paciente> lista = new LinkedList<Paciente>();

		Paciente paciente1 = new Paciente();
		paciente1.setCi(Long.valueOf(123456789));
		paciente1.setCelular("099999999");
		paciente1.setDireccion("calle 1");
		paciente1.setFichas(null);
		paciente1.setMail("mail@mail.com");
		paciente1.setPrimerNombre("Juan");
		paciente1.setSegundoNombre("Pedro");
		paciente1.setPrimerApellido("Damiani");
		paciente1.setSegundoApellido("");
		paciente1.setTelefono("29000982");
		
		Paciente paciente2 = new Paciente();
		paciente2.setCi(Long.valueOf(987654321));
		paciente2.setCelular("099994449");
		paciente2.setDireccion("calle 2");
		paciente2.setFichas(null);
		paciente2.setMail("mail@mail.com");
		paciente2.setPrimerNombre("Pablo  ");
		paciente2.setSegundoNombre("Javier");
		paciente2.setPrimerApellido("Bengoechea");
		paciente2.setSegundoApellido("");
		paciente2.setTelefono("29000982");
		
		lista.add(paciente1);
		lista.add(paciente2);
		return lista;
	}

	@Override
	public List<Ficha> buscarFichasPorString(final String busqueda, final boolean porPaciente, final Long cedula) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarFichaPaciente(final Ficha ficha, final long cedula) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		
	}
}
