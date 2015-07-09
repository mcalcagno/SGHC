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
	 * Borra un paciente a partir de su n�mero de c�dula (clave).
	 * Si el paciente no existe tira una excepci�n
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public void borrarPaciente(String cedula) throws SGHCExcepcion;
	
	/**
	 * Edita un paciente cargandole la informaci�n contenida en el par�metro.
	 * Si el paciente no existe tira una excepci�n
	 * @param cliente
	 * @throws SGHCExcepcion
	 */
	public void editarPaciente(PacienteDto cliente) throws SGHCExcepcion;

}
