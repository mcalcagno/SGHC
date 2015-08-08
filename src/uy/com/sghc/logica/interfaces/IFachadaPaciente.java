package uy.com.sghc.logica.interfaces;

import java.util.List;

import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;

public interface IFachadaPaciente {
	
	/**
	 * Crea un paciente en el sistema a partir de los datos 
	 * @param paciente
	 */
	public void crearPaciente(PacienteDto paciente) throws SGHCExcepcion;
	
	/**
	 * Borra un paciente a partir de su número de cédula (clave).
	 * Si el paciente no existe tira una excepción
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public void borrarPaciente(Long cedula) throws SGHCExcepcion;
	
	/**
	 * Edita un paciente cargandole la información contenida en el parámetro.
	 * Si el paciente no existe tira una excepción
	 * @param cliente
	 * @throws SGHCExcepcion
	 */
	public void editarPaciente(PacienteDto paciente) throws SGHCExcepcion;
	
	/**
	 * Obtener datos de un paciente
	 * Si el paciente no existe tira una excepción
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public PacienteDto obtenerPaciente(Long cedula) throws SGHCExcepcion;
	
	/**
	 * Agrega una ficha al paciente, retorna una excepción si el paciente no existe. 
	 * Si la ficha no existe crea la ficha en el sistema y la asocia al paciente 
	 * @param cedulaPacienye
	 * @param ficha
	 * @throws SGHCExcepcion
	 */
	public void agregarFichaPaciente(Long cedulaPacienye, Ficha ficha) throws SGHCExcepcion;
	
	/**
	 * Agrega una lista de fichas a un paciente
	 * @param cedulaPaciente
	 * @param fichas
	 * @throws SGHCExcepcion
	 */
	public void agregarFichasPaciente(Long cedulaPaciente, List<Ficha> fichas) throws SGHCExcepcion;  

}
