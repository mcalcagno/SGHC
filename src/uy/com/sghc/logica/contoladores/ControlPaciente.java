package uy.com.sghc.logica.contoladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;
import uy.com.sghc.logica.utils.PacientesIndice;
import uy.com.sghc.persistencia.controladores.ControlPersistirPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;
import uy.com.sghc.persistencia.manejadores.ManejadorXml;

public class ControlPaciente implements IFachadaPaciente {

	private static IPersistirPaciente persistenciaPaciente = new ControlPersistirPaciente();
	private static Logger logger = Logger.getLogger(ControlPaciente.class);
	
	@Override
	public void crearPaciente(final PacienteDto pacientedto) throws SGHCExcepcion {
		
		PacientesIndice indice = PacientesIndice.newInstance();
		if(!indice.existePaciente(pacientedto.getCi())){
			indice.getPacientes().add(pacientedto.getCi());
			persistenciaPaciente.crearPaciente(new Paciente(pacientedto));	
		}else{
			//TODO: usar mensajes.properties 
			logger.error("YA EXISTE UN PACIENTE CON LA MISMA CÉDULA");
			throw new SGHCExcepcion("YA EXISTE UN PACIENTE CON LA MISMA CÉDULA");
		}
		
	}

	@Override 
	public void borrarPaciente(final Long cedulaPaciente) throws SGHCExcepcion {
		PacientesIndice.newInstance().getPacientes().remove(cedulaPaciente);
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

		logger.info("COMIENZA BÚSQUEDA PARA "+filtro);
		List<PacienteDto> listaRetorno = new ArrayList<PacienteDto>();
		PacientesIndice indice = PacientesIndice.newInstance();
		Iterator<Long> it = indice.getPacientes().iterator();
		while(it.hasNext()){
			Long cedula = it.next();
			Paciente paciente = persistenciaPaciente.obtenerPaciente(cedula);
			if(paciente.validarFiltro(filtro)){
				logger.debug("se encuentra: "+paciente.toString());
				listaRetorno.add(paciente.getPacienteDto());
			}
		}
		return listaRetorno;
		
	}
	
	
}
