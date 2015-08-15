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

	@Override
	public List<Long> obtenerPacientesIndices() throws SGHCExcepcion {
		return ManejadorXml.newInstance().obtenerPacienteIndice();
	}
}
