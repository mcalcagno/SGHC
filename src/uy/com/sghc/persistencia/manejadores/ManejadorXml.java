package uy.com.sghc.persistencia.manejadores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

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
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.persistencia.xml.FichaXml;
import uy.com.sghc.persistencia.xml.PacienteXml;

public class ManejadorXml {
	
	private static ManejadorXml instance = null;
	private static Logger logger = Logger.getLogger(ManejadorXml.class);
	
	private ManejadorXml(){
		// constructor por defecto
	};
	
	public static ManejadorXml newInstance() {
		if (instance == null) {
			instance = new ManejadorXml();
		}		
		return instance;
	}

	public void persistirNuevoPaciente(Paciente paciente) throws SGHCExcepcion{
		
		try{
			JAXBContext jc = JAXBContext.newInstance(PacienteXml.class);
			Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
	        StringWriter stringWriter = new StringWriter();
	        marshaller.marshal(this.pacienteXmlFromPaciente(paciente), stringWriter);
	        String strPaciente = stringWriter.toString();
	        String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
	        	File.separator+String.valueOf(paciente.getCi())+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
	        FileWriter outFile = new FileWriter(path);
			outFile.write(strPaciente);
			outFile.close();
			logger.debug("Paciente creado ruta: "+path+" xml: "+strPaciente);
		}catch(JAXBException e){
			logger.error("ERRROR PERSISTIENDO PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		} catch (IOException e) {
			logger.error("ERRROR PERSISTIENDO PACIENTE - "+e.getMessage(),e);
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
			logger.debug("Paciente borrado: "+cedula+" ruta: "+path);
		}catch(Exception e){
			logger.error("ERRROR BORRANDO PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		
	}
	
	public void editarPaciente(Paciente paciente) throws SGHCExcepcion{
		
		try {
			Binder<Node> binder = this.getContextoBinder(PacienteXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
				File.separator+String.valueOf(paciente.getCi())+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = this.getDocument(file); 
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
		try{
			Binder<Node> binder = this.getContextoBinder(PacienteXml.class);
			String path = PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_PATH)+
					File.separator+String.valueOf(cedula)+PropController.getPropConfig(PropController.CONFIG_PERSISTENCE_PACIENTE_EXTENSION);
			File file = new File(path);
			Document doc = this.getDocument(file); 
			PacienteXml pacienteXml = (PacienteXml) binder.unmarshal(doc);
			paciente = this.pacienteFromPacienteXml(pacienteXml);
		}catch (JAXBException e) {
			logger.error("ERRROR OBTENIENDO PACIENTE - "+e.getMessage(),e);
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		return paciente;
	}
	
	/*
	 * Metodos auxiliares
	 */
	
	private Document getDocument(File file) throws SGHCExcepcion{
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
	
	private Binder<Node> getContextoBinder(Class clase) throws SGHCExcepcion{
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
	
	private void persistirEdicion(File file,Document doc) throws SGHCExcepcion{
		
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
	
}
