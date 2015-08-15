package uy.com.sghc.logica.contoladores;

import java.util.LinkedList;
import java.util.List;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;
import uy.com.sghc.persistencia.controladores.ControlPersistirPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;
import uy.com.sghc.persistencia.manejadores.ManejadorXml;

public class ControlPaciente implements IFachadaPaciente {

	private static IPersistirPaciente persistenciaPaciente = new ControlPersistirPaciente();
	
	@Override
	public void crearPaciente(final PacienteDto pacientedto) throws SGHCExcepcion {
		persistenciaPaciente.crearPaciente(new Paciente(pacientedto));
	}

	@Override 
	public void borrarPaciente(final Long cedulaPaciente) throws SGHCExcepcion {
		persistenciaPaciente.borrarPaciente(cedulaPaciente);
	}

	@Override
	public void editarPaciente(PacienteDto pacientedto) throws SGHCExcepcion {		
		persistenciaPaciente.editarPaciente(new Paciente(pacientedto));
	}
	
	@Override
	public PacienteDto obtenerPaciente(Long cedula) throws SGHCExcepcion {
		return persistenciaPaciente.obtenerPaciente(cedula).getPacienteDto();
	}

	@Override
	public void agregarFichaPaciente(Long cedulaPacienye, Ficha ficha) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarFichasPaciente(Long cedulaPaciente, List<Ficha> fichas) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<PacienteDto> buscarPacientes(final String filtro) throws SGHCExcepcion {
		List<Paciente> listaPacientes = persistenciaPaciente.buscarPacientesPorString(filtro);
		List<PacienteDto> listaPacientesDto = new LinkedList<PacienteDto>();
		for (Paciente paciente : listaPacientes) {
			listaPacientesDto.add(paciente.getPacienteDto());
		}
		return listaPacientesDto;
	}
	
}
