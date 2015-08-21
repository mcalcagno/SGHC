package uy.com.sghc.logica.contoladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;
import uy.com.sghc.logica.utils.PacientesIndice;
import uy.com.sghc.persistencia.controladores.ControlPersistirPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;

public class ControlPaciente implements IFachadaPaciente {

	private static IPersistirPaciente persistenciaPaciente = new ControlPersistirPaciente();
	private static Logger logger = Logger.getLogger(ControlPaciente.class);
	
	@Override
	public void crearPaciente(final PacienteDto pacienteDto) throws SGHCExcepcion {
		
		PacientesIndice indice = PacientesIndice.newInstance();
		if(!indice.existePaciente(pacienteDto.getCi())){
			indice.getPacientes().add(pacienteDto.getCi());
			persistenciaPaciente.crearPaciente(new Paciente(pacienteDto));	
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
	public void agregarFichaPaciente(Long cedula, FichaDto fichaDto) throws SGHCExcepcion {
		persistenciaPaciente.agregarFichaPaciente(new Ficha(fichaDto), cedula.longValue());
	}

	@Override
	public void agregarFichasPaciente(Long cedula, List<FichaDto> fichasDto) throws SGHCExcepcion {
		
		Iterator<FichaDto> it = fichasDto.iterator();
		while(it.hasNext()){
			persistenciaPaciente.agregarFichaPaciente(new Ficha(it.next()), cedula.longValue());
		}
		
	}
	
	@Override
	public List<PacienteDto> buscarPacientes(final String filtro) throws SGHCExcepcion {
			
		logger.info("COMIENZA BÚSQUEDA PARA "+filtro);
		List<PacienteDto> listaRetorno = new ArrayList<PacienteDto>();
		if (StringUtils.isNotBlank(filtro)) {
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
		}
		return listaRetorno;
		
	}
	
	
}
