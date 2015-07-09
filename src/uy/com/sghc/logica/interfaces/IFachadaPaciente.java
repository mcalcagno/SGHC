package uy.com.sghc.logica.interfaces;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;

public interface IFachadaPaciente {
	
	/**
	 * Crea un paciente en el sistema a partir de los datos 
	 * @param paciente
	 */
	public void crearPaciente(PacienteDto cliente);
	
	/**
	 * Borra un paciente a partir de su número de cédula (clave).
	 * Si el paciente no existe tira una excepción
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public void borrarPaciente(String cedula) throws SGHCExcepcion;
	
	/**
	 * Edita un paciente cargandole la información contenida en el parámetro.
	 * Si el paciente no existe tira una excepción
	 * @param cliente
	 * @throws SGHCExcepcion
	 */
	public void editarPaciente(PacienteDto cliente) throws SGHCExcepcion;

}
