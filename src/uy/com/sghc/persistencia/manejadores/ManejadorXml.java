package uy.com.sghc.persistencia.manejadores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.Binder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import uy.com.sghc.config.PropController;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.persistencia.xml.FichaXml;
import uy.com.sghc.persistencia.xml.PacienteXml;
import uy.com.sghc.persistencia.xml.PacientesIndiceXml;

public class ManejadorXml {
	
	private static ManejadorXml instance = null;
	private static Logger logger = Logger.getLogger(ManejadorXml.class);
//	private static List<Long> pacientes = new ArrayList<Long>();
	
	private ManejadorXml(){
		// constructor por defecto
	};
	
	public static ManejadorXml newInstance() {
		if (instance == null) {
			instance = new ManejadorXml();
			//inicializarPacienteIndice();
		}		
		return instance;
	}

	public void persistirNuevoPaciente(Paciente paciente) throws SGHCExcepcion{
		
		try{
			JAXBContext jc = JAXBContext.newInstance(PacienteXml.class);
			Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
	        StringWriter stringWriter = new StringWriter();
	        marshaller.marshal(this.pacienteXmlFromPaciente(paciente), stringWriter);
	        String strPaciente = stringWriter.toString();
	        String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
	        	File.separator+String.valueOf(paciente.getCi())+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
	        FileWriter outFile = new FileWriter(path);
			outFile.write(strPaciente);
			outFile.close();
			agregarPacienteIndice(paciente.getCi());
			logger.debug("Paciente creado ruta: "+path+" xml: "+strPaciente);
		}catch(JAXBException e){
			logger.error("ERRROR EDITANDO INDICE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		} catch (IOException e) {
			logger.error("ERRROR EDITANDO INDICE- "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}
	}
	

	public void borrarPaciente(Long cedula) throws SGHCExcepcion{
		
		try{
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
        		File.separator+String.valueOf(cedula)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File archivo = new File(path);
			//TODO: extraer la fichas del paciente y borrarlas todas
			archivo.deleteOnExit();
			eliminarPacienteIndice(cedula);
			logger.debug("Paciente borrado: "+cedula+" ruta: "+path);
		}catch(Exception e){
			logger.error("ERRROR BORRANDO PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		
	}
	
	public void editarPaciente(Paciente paciente) throws SGHCExcepcion{
		
		try {
			Binder<Node> binder = getContextoBinder(PacienteXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+String.valueOf(paciente.getCi())+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			PacienteXml nuevoPaciente = (PacienteXml) binder.unmarshal(doc);
			clonarPacienteXmlFromPaciente(paciente, nuevoPaciente);
			binder.updateXML(nuevoPaciente);
			persistirEdicion(file, doc);
			logger.debug("Paciente editado: "+paciente.toString());
		}catch (JAXBException e) {
			logger.error("ERRROR AL EDITAR EL PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		
	}
	
	public Paciente obtenetPaciente(Long cedula) throws SGHCExcepcion {
		
		Paciente paciente = null;
		PacienteXml pacienteXml = obtenerPacienteXml(cedula);
		paciente = this.pacienteFromPacienteXml(pacienteXml);
		return paciente;
		
	}

	public List<Long> obtenerPacienteIndice(){
		
		List<Long> lista = new ArrayList<Long>();
		try {
			Binder<Node> binder = getContextoBinder(PacientesIndiceXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_INDICE)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			PacientesIndiceXml indice = (PacientesIndiceXml) binder.unmarshal(doc);
			//pacientes = indice.getPacientes();
			lista = indice.getPacientes();
			logger.debug("Se iniclializa el indice");
		}catch (JAXBException e) {
			logger.error("ERRROR AL INICIALIZAR EL INDICE - "+e.getMessage(),e);
		}catch (SGHCExcepcion e) {
			logger.error("ERRROR AL INICIALIZAR EL INDICE - "+e.getMessage(),e);
		}
		return lista;
		
	}

	public List<Ficha> obtenerFichasPaciente(Long cedula) throws SGHCExcepcion {
		
		List <Ficha> listaFichas = new ArrayList<Ficha>();
		PacienteXml pacienteXml = obtenerPacienteXml(cedula);
		List<FichaXml> fichasXml = pacienteXml.getFichas();
		Iterator<FichaXml> it = fichasXml.iterator();
		while(it.hasNext()){
			FichaXml fichaXml = it.next();
			Ficha ficha = fichaFromFichaXml(fichaXml);
			listaFichas.add(ficha);
		}
		return listaFichas;
		
	}

	public void agregarFichaPaciente(Ficha ficha, Long cedula) throws SGHCExcepcion {

		try {
			Binder<Node> binder = getContextoBinder(PacienteXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+String.valueOf(cedula)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			PacienteXml pacienteXml = (PacienteXml) binder.unmarshal(doc);
			pacienteXml.getFichas().add(fichaXmlFromFicha(ficha));
			binder.updateXML(pacienteXml);
			persistirEdicion(file, doc);
			logger.debug("Paciente editado: "+pacienteXml.toString());
		}catch (JAXBException e) {
			logger.error("ERRROR AL AGREGAR FICHA AL PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERRROR AL AGREGAR FICHA AL PACIENTE");
		} 
		
	}
	
	
	/*
	 * Metodos auxiliares
	 */

	private static Document getDocument(File file) throws SGHCExcepcion{
		Document doc = null;
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);
		}catch(ParserConfigurationException e){
			logger.error("ERRROR OBTENIENDO EL DOCUMENTO - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(IOException e){
			logger.error("ERRROR OBTENIENDO EL DOCUMENTO - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(SAXException e){
			logger.error("ERRROR OBTENIENDO EL DOCUMENTO - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(Exception e){
			logger.error("ERRROR OBTENIENDO EL DOCUMENTO - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}
		return doc;
	}
	
	private static Binder<Node> getContextoBinder(Class clase) throws SGHCExcepcion{
		Binder<Node> binder = null;
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(clase);
			binder = jaxbContext.createBinder();
		}catch(JAXBException e){
			logger.error("ERRROR OBTENIENDO EL CONTEXTO BINDER- "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}
		return binder;
	}
	
	private static void agregarPacienteIndice(Long cedula) throws SGHCExcepcion{
		//pacientes.add(cedula);
		try {
			Binder<Node> binder = getContextoBinder(PacientesIndiceXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_INDICE)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			PacientesIndiceXml indice = (PacientesIndiceXml) binder.unmarshal(doc);
			indice.getPacientes().add(cedula);
			binder.updateXML(indice);
			persistirEdicion(file, doc);
			logger.debug("Indice editado se agrega "+cedula.toString());
		}catch (JAXBException e) {
			logger.error("ERRROR AL EDITAR EL INDICE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL EDITAR EL INDICE");
		}
		
	}	
	
	private static void eliminarPacienteIndice(Long cedula) throws SGHCExcepcion{
		//pacientes.remove(cedula);
		try {
			Binder<Node> binder = getContextoBinder(PacientesIndiceXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_INDICE)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			PacientesIndiceXml indice = (PacientesIndiceXml) binder.unmarshal(doc);
			indice.getPacientes().remove(cedula);
			binder.updateXML(indice);
			persistirEdicion(file, doc);
			logger.debug("Indice editado se elimina "+cedula.toString());
		}catch (JAXBException e) {
			logger.error("ERRROR AL EDITAR EL INDICE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL EDITAR EL INDICE ");
		}
		
	}	
	
	/*
	private static void inicializarPacienteIndice(){
		
		try {
			Binder<Node> binder = getContextoBinder(PacientesIndice.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_INDICE)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			PacientesIndice indice = (PacientesIndice) binder.unmarshal(doc);
			//pacientes = indice.getPacientes();
			logger.debug("Se iniclializa el indice");
		}catch (JAXBException e) {
			logger.error("ERRROR AL INICIALIZAR EL INDICE - "+e.getMessage(),e);
		}catch (SGHCExcepcion e) {
			logger.error("ERRROR AL INICIALIZAR EL INDICE - "+e.getMessage(),e);
		}
		
	}
	*/
	
	private static void persistirEdicion(File file,Document doc) throws SGHCExcepcion{
		
		TransformerFactory tf = TransformerFactory.newInstance(); 
		StreamResult result = new StreamResult(file); 
		Transformer t;
		try {
			t = tf.newTransformer(); 
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); 
			t.setOutputProperty(OutputKeys.INDENT, "yes"); 
			t.transform(new DOMSource(doc), result);
		}catch (TransformerConfigurationException e) {
			logger.error("ERRROR PERSISTIENOD EDICIÓN - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR PERSISTIENDO EDICION - TransformerConfigurationException");
		}catch (TransformerException e) {
			logger.error("ERRROR PERSISTIENOD EDICIÓN - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR PERSISTIENDO EDICION - TransformerException");
		}
		
	}
	
	private PacienteXml obtenerPacienteXml(Long cedula) throws SGHCExcepcion {
		
		PacienteXml pacienteXml = new PacienteXml();
		try{
			Binder<Node> binder = getContextoBinder(PacienteXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
					File.separator+String.valueOf(cedula)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = getDocument(file); 
			pacienteXml = (PacienteXml) binder.unmarshal(doc);
		}catch (JAXBException e) {
			logger.error("ERRROR OBTENIENDO PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL OBTENER EL PACIENTE XML");
		}
		return pacienteXml;
		
	}
	
	private PacienteXml pacienteXmlFromPaciente(Paciente paciente){
		
		PacienteXml pacienteXml = new PacienteXml();
		pacienteXml.setCi(paciente.getCi());
		pacienteXml.setPrimerNombre(paciente.getPrimerNombre());
		pacienteXml.setSegundoNombre(paciente.getSegundoNombre());
		pacienteXml.setPrimerApellido(paciente.getPrimerApellido());
		pacienteXml.setSegundoApellido(paciente.getSegundoApellido());
		pacienteXml.setDireccion(paciente.getDireccion());
		pacienteXml.setMail(paciente.getMail());
		pacienteXml.setTelefono(paciente.getTelefono());
		pacienteXml.setCelular(paciente.getCelular());
		return pacienteXml;
		
	}
	
	private Paciente pacienteFromPacienteXml(PacienteXml pacienteXml){
		
		Paciente paciente = new Paciente();
		paciente.setCi(pacienteXml.getCi());
		paciente.setPrimerNombre(pacienteXml.getPrimerNombre());
		paciente.setSegundoNombre(pacienteXml.getSegundoNombre());
		paciente.setPrimerApellido(pacienteXml.getPrimerApellido());
		paciente.setSegundoApellido(pacienteXml.getSegundoApellido());
		paciente.setDireccion(pacienteXml.getDireccion());
		paciente.setMail(pacienteXml.getMail());
		paciente.setTelefono(pacienteXml.getTelefono());
		paciente.setCelular(pacienteXml.getCelular());
		return paciente;
		
	}
	
	private PacienteXml clonarPacienteXmlFromPaciente(Paciente paciente,PacienteXml pacienteXml){
		
		pacienteXml.setCi(paciente.getCi());
		pacienteXml.setPrimerNombre(paciente.getPrimerNombre());
		pacienteXml.setSegundoNombre(paciente.getSegundoNombre());
		pacienteXml.setPrimerApellido(paciente.getPrimerApellido());
		pacienteXml.setSegundoApellido(paciente.getSegundoApellido());
		pacienteXml.setDireccion(paciente.getDireccion());
		pacienteXml.setMail(paciente.getMail());
		pacienteXml.setTelefono(paciente.getTelefono());
		pacienteXml.setCelular(paciente.getCelular());
		return pacienteXml;
		
	}
	
	private Ficha fichaFromFichaXml (FichaXml fichaXml){
		
		Ficha ficha = new Ficha();
		ficha.setNumero(fichaXml.getNumero());
		ficha.setFecha(fichaXml.getFecha());
		ficha.setMotivoConsulta(fichaXml.getMotivoConsulta());
		ficha.setDiagnostico(fichaXml.getDiagnostico());
		ficha.setObservaciones(fichaXml.getObservaciones());
		return ficha;
		
	}
	
	private FichaXml fichaXmlFromFicha(Ficha ficha){
		
		FichaXml fichaXml = new FichaXml();
		fichaXml.setNumero(ficha.getNumero());
		fichaXml.setFecha(ficha.getFecha());
		fichaXml.setMotivoConsulta(ficha.getMotivoConsulta());
		fichaXml.setDiagnostico(ficha.getDiagnostico());
		fichaXml.setObservaciones(ficha.getObservaciones());
		return fichaXml;
		
	}

	
}
