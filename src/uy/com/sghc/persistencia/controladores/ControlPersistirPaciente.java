package uy.com.sghc.persistencia.controladores;

import java.util.List;

import uy.com.sghc.dtos.PacienteFichasSimpleDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;
import uy.com.sghc.persistencia.manejadores.ManejadorXml;

public class ControlPersistirPaciente implements IPersistirPaciente{

	@Override
	public void crearPaciente(Paciente paciente) throws SGHCExcepcion{
		
		ManejadorXml manejador = ManejadorXml.newInstance();
		try{
			manejador.persistirNuevoPaciente(paciente);
		}catch(SGHCExcepcion e){
			throw e;
		}
	}

	@Override
	public void borrarPaciente(Long cedula) throws SGHCExcepcion {
		
		ManejadorXml manejador = ManejadorXml.newInstance();
		manejador.borrarPaciente(cedula);
		
	}

	@Override
	public void editarPaciente(Paciente paciente) throws SGHCExcepcion {
		
		ManejadorXml manejador = ManejadorXml.newInstance();
		manejador.editarPaciente(paciente);
		
	}

	@Override
	public Paciente obtenerPaciente(Long cedula) throws SGHCExcepcion {

		ManejadorXml manejador = ManejadorXml.newInstance();
		Paciente paciente = manejador.obtenetPaciente(cedula);
		return paciente;
		
	}

	@Override
	public List<PacienteFichasSimpleDto> obtenerFichasPacienteSimple(
			Long cedulaPaciente) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}
