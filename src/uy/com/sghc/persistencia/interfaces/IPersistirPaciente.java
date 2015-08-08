package uy.com.sghc.persistencia.interfaces;

import java.util.List;

import uy.com.sghc.dtos.PacienteFichasSimpleDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;

public interface IPersistirPaciente {
	/**
	 * Crea un paciente en la base de datos a partir de los datos 
	 * @param paciente
	 */
	public void crearPaciente(Paciente paciente) throws SGHCExcepcion;
	
	/**
	 * Borra un paciente de la base de datos a partir de su número de cédula (clave).
	 * Si el paciente no existe tira una excepción
	 * @param cedula
	 * @throws SGHCExcepcion
	 */
	public void borrarPaciente(Long cedula) throws SGHCExcepcion;
	
	/**
	 * Edita un paciente en la base de datos cargandole la información contenida en el parámetro pero no modifica las fichas del mismo.
	 * Si el paciente no existe tira una excepción
	 * @param cliente
	 * @throws SGHCExcepcion
	 */
	public void editarPaciente(Paciente paciente) throws SGHCExcepcion;

	/**
	 * Busca el paciente por su cédula y retorna la información del mismo encapsulada en el objeto paciente
	 * @param ci
	 * @return Paciente
	 * @throws SGHCExcepcion
	 */
	public Paciente obtenerPaciente(Long cedula) throws SGHCExcepcion;
	
	/** 
	 * @param cedulaPaciente
	 * @return Retorna una lista con los números de fichas de un paciente buscandolo por cédula
	 * @throws SGHCExcepcion en caso de que no exista el paciente
	 */
	public List<Ficha> obtenerFichasPaciente(Long cedula) throws SGHCExcepcion;

	/**
	 * lista los pacientes que contegan el string paraemtro en alguno de sus atibutos
	 * @param busqueda
	 * @return List<Paciente>
	 * @throws SGHCExcepcion
	 */
	public List<Paciente> buscarPacientesPorString(String busqueda) throws SGHCExcepcion;
	
	/**
	 * lista las fichas que contegan el string paraemtro en alguno de sus atibutos
	 * @param busqueda, booleno de si lleva o no paciente, y en cao de llevarlo la cedula del pacinete
	 * @return List<Fichas>
	 * @throws SGHCExcepcion
	 */
	public List<Ficha> buscarFichasPorString(String busqueda,boolean porPaciente, Long cedula) throws SGHCExcepcion;

	/**
	 * agregar una ficha a un paciente
	 * @param nueva ficha y cedula del paciente
	 * @return List<Fichas>
	 * @throws SGHCExcepcion
	 */
	public void agregarFichaPaciente(Ficha ficha, long cedula) throws SGHCExcepcion;
	
}
