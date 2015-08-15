package uy.com.sghc.logica.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.persistencia.controladores.ControlPersistirPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;

public class PacientesIndice {
	
	private static PacientesIndice instance = null;
	private static Logger logger = Logger.getLogger(PacientesIndice.class);
	private static List<Long> pacientes = new ArrayList<Long>();
	
	private PacientesIndice(){
		// constructor por defecto
	};
	
	public static PacientesIndice newInstance() {
		if (instance == null) {
			instance = new PacientesIndice();
			IPersistirPaciente i = new ControlPersistirPaciente();
			try {
				pacientes = i.obtenerPacientesIndices();
			} catch (SGHCExcepcion e) {
				logger.error("ERROR OBTIENDO INDICE",e);
			}
		}		
		return instance;
	}

	public List<Long> getPacientes() {
		return pacientes;
	}	
	
	public boolean existePaciente(Long cedula){
		return pacientes.contains(cedula);
	}
	
}
