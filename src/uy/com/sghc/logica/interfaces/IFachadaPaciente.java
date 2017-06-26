package uy.com.sghc.logica.interfaces;

import java.util.List;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;

public interface IFachadaPaciente {
	
	/**
	 * Crea un paciente en el sistema a partir de los datos 
	 * @param paciente
	 */
	public void crearPaciente(PacienteDto paciente) throws SGHCExcepcion;
	
	/**
	 * Borra un paciente a partir de su n�mero de c�dula (clave).
	 * Si el paciente no existe tira una excepci�n
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public void borrarPaciente(Long cedula) throws SGHCExcepcion;
	
	/**
	 * Edita un paciente cargandole la informaci�n contenida en el par�metro.
	 * Si el paciente no existe tira una excepci�n
	 * @param cliente
	 * @throws SGHCExcepcion
	 */
	public void editarPaciente(PacienteDto paciente) throws SGHCExcepcion;
	
	/**
	 * Obtener datos de un paciente
	 * Si el paciente no existe tira una excepci�n
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public PacienteDto obtenerPaciente(Long cedula) throws SGHCExcepcion;
	
	/**
	 * Agrega una ficha al paciente, retorna una excepci�n si el paciente no existe. 
	 * Si la ficha no existe crea la ficha en el sistema y la asocia al paciente 
	 * @param cedulaPacienye
	 * @param ficha
	 * @throws SGHCExcepcion
	 */
	public void agregarFichaPaciente(Long cedula, FichaDto ficha) throws SGHCExcepcion;
	
	/**
	 * Agrega una lista de fichas a un paciente
	 * @param cedulaPaciente
	 * @param fichas
	 * @throws SGHCExcepcion
	 */
	public void agregarFichasPaciente(Long cedula, List<FichaDto> fichas) throws SGHCExcepcion;
	
	/**
	 * Buscar pacientes que en alguno de sus campos cumpla con la condici�n del filtro y retorna una lista con la informaci�n de los pacientes
	 * en principio el DTO podr�a contener solo C�dula y Nombre
	 * @param filtro
	 * @return
	 * @throws SGHCExcepcion
	 */
	public List<PacienteDto> buscarPacientes(String filtro) throws SGHCExcepcion;

	
	/**
	 * Retorna una lista de fichas del paciente identificado por la cedula
	 * @param cedula
	 * @return
	 * @throws SGHCExcepcion cuando no existe el paciente con dicha cedula
	 */
	public List<FichaDto> obtenerFichasPaciente(Long cedula) throws SGHCExcepcion;

}
