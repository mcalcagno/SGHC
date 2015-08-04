package uy.com.sghc.logica.contoladores;

import java.util.List;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;
import uy.com.sghc.persistencia.manejadores.ManejadorXml;

public class ControlPaciente implements IFachadaPaciente {

	private static IPersistirPaciente persistenciaPaciente;
	
	@Override
	public void crearPaciente(final PacienteDto pacientedto) {
		final Paciente paciente = new Paciente(pacientedto);
		persistenciaPaciente.crearPaciente(paciente);
	}

	@Override 
	public void borrarPaciente(final Long cedulaPaciente) throws SGHCExcepcion {
		persistenciaPaciente.borrarPaciente(cedulaPaciente);
	}

	@Override
	public void editarPaciente(PacienteDto pacientedto) throws SGHCExcepcion {		
		final Paciente paciente = new Paciente(pacientedto);
		persistenciaPaciente.editarPaciente(paciente);
	}

	@Override
	public void agregarFichaPaciente(Long cedulaPacienye, Ficha ficha) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarFichasPaciente(Long cedulaPaciente, List<Ficha> fichas) throws SGHCExcepcion {
		// TODO Auto-generated method stub
		
	}
}
