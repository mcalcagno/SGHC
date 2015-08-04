package uy.com.sghc.persistencia.interfaces;

import java.util.List;

import uy.com.sghc.dtos.PacienteFichasSimpleDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Paciente;

public interface IPersistirPaciente {
	/**
	 * Crea un paciente en la base de datos a partir de los datos 
	 * @param paciente
	 */
	public void crearPaciente(Paciente paciente);
	
	/**
	 * Borra un paciente de la base de datos a partir de su n�mero de c�dula (clave).
	 * Si el paciente no existe tira una excepci�n
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public void borrarPaciente(Long cedula) throws SGHCExcepcion;
	
	/**
	 * Edita un paciente en la base de datos cargandole la informaci�n contenida en el par�metro pero no modifica las fichas del mismo.
	 * Si el paciente no existe tira una excepci�n
	 * @param cliente
	 * @throws SGHCExcepcion
	 */
	public void editarPaciente(Paciente paciente) throws SGHCExcepcion;

	/**
	 * Busca el paciente por su c�dula y retorna la informaci�n del mismo encapsulada en el objeto paciente
	 * @param ci
	 * @return Paciente
	 * @throws SGHCExcepcion
	 */
	public Paciente obtenerPaciente(Long cedula) throws SGHCExcepcion;
	
	/** 
	 * @param cedulaPaciente
	 * @return Retorna una lista con los n�meros de fichas de un paciente buscandolo por c�dula
	 * @throws SGHCExcepcion en caso de que no exista el paciente
	 */
	public List<PacienteFichasSimpleDto> obtenerFichasPacienteSimple(Long cedulaPaciente) throws SGHCExcepcion;

	
}
