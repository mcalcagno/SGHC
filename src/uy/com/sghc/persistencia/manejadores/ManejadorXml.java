package uy.com.sghc.persistencia.manejadores;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.persistencia.xml.FichaXml;
import uy.com.sghc.persistencia.xml.PacienteXml;

public class ManejadorXml {
	
	private static ManejadorXml instance = null;
	
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
	        //TODO: configuraciones ruta
	        String path = "C:\\ArchivosXML\\Pacientes"+File.separator+String.valueOf(paciente.getCi())+".xml";
	        FileWriter outFile = new FileWriter(path);
			outFile.write(strPaciente);
			outFile.close();
		}catch(JAXBException e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		} catch (IOException e) {
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}
	}
	

	public void borrarPaciente(Long cedula) throws SGHCExcepcion{
		
		try{
			String path = "C:\\ArchivosXML\\Pacientes"+File.separator+String.valueOf(cedula)+".xml";
			File archivo = new File(path);
			//TODO: extraer la fichas del paciente y borrarlas todas
			archivo.deleteOnExit();
		}catch(Exception e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		
	}
	
	public void editarPaciente(Paciente paciente) throws SGHCExcepcion{
		
		try {
			Binder<Node> binder = this.getContextoBinder(PacienteXml.class);
			//TODO: reemplazar por propiedad
			File file = new File("C:\\ArchivosXML\\Pacientes"+File.separator+String.valueOf(paciente.getCi())+".xml");
			Document doc = this.getDocument(file); 
			PacienteXml nuevoPaciente = (PacienteXml) binder.unmarshal(doc);
			clonarPacienteXmlFromPaciente(paciente, nuevoPaciente);
			binder.updateXML(nuevoPaciente);
			persistirEdicion(file, doc);
			
		}catch (JAXBException e) {
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		
	}
	
	public Paciente obtenetPaciente(Long cedula) throws SGHCExcepcion {
		
		Paciente paciente = null;
		try{
			Binder<Node> binder = this.getContextoBinder(PacienteXml.class);
			//TODO: reemplazar por propiedad
			File file = new File("C:\\ArchivosXML\\Pacientes"+File.separator+String.valueOf(cedula)+".xml");
			Document doc = this.getDocument(file); 
			PacienteXml pacienteXml = (PacienteXml) binder.unmarshal(doc);
			paciente = this.pacienteFromPacienteXml(pacienteXml);
		}catch (JAXBException e) {
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL BORRAR XML DEL PACIENTE");
		}
		return paciente;
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
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(IOException e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(SAXException e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(Exception e){
			//TODO: loguear
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
			//TODO: loguear
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
			//TODO: loguear
			throw new SGHCExcepcion("ERROR PERSISTIENDO EDICION - TransformerConfigurationException");
		}catch (TransformerException e) {
			//TODO: loguear
			throw new SGHCExcepcion("ERROR PERSISTIENDO EDICION - TransformerException");
		}
		
	}

	private static PacienteXml pacienteXmlFromPaciente(Paciente paciente){
		
		PacienteXml pacienteXml = new PacienteXml();
		pacienteXml.setCI(paciente.getCi());
		pacienteXml.setNombre1(paciente.getPrimerNombre());
		pacienteXml.setNombre2(paciente.getSegundoNombre());
		pacienteXml.setApellido1(paciente.getPrimerApellido());
		pacienteXml.setApellido2(paciente.getSegundoApellido());
		pacienteXml.setDireccion(paciente.getDireccion());
		pacienteXml.setEmail(paciente.getMail());
		pacienteXml.setTelefono(paciente.getTelefono());
		return pacienteXml;
		
	}
	
	private static Paciente pacienteFromPacienteXml(PacienteXml pacienteXml){
		
		Paciente paciente = new Paciente();
		paciente.setCi(pacienteXml.getCI());
		paciente.setPrimerNombre(pacienteXml.getNombre1());
		paciente.setSegundoNombre(pacienteXml.getNombre2());
		paciente.setPrimerApellido(pacienteXml.getApellido1());
		paciente.setSegundoApellido(pacienteXml.getApellido2());
		paciente.setDireccion(pacienteXml.getDireccion());
		paciente.setMail(pacienteXml.getEmail());
		paciente.setTelefono(pacienteXml.getTelefono());
		return paciente;
		
	}
	
	private static PacienteXml clonarPacienteXmlFromPaciente(Paciente paciente,PacienteXml pacienteXml){
		
		pacienteXml.setCI(paciente.getCi());
		pacienteXml.setNombre1(paciente.getPrimerNombre());
		pacienteXml.setNombre2(paciente.getSegundoNombre());
		pacienteXml.setApellido1(paciente.getPrimerApellido());
		pacienteXml.setApellido2(paciente.getSegundoApellido());
		pacienteXml.setDireccion(paciente.getDireccion());
		pacienteXml.setEmail(paciente.getMail());
		pacienteXml.setTelefono(paciente.getTelefono());
		return pacienteXml;
		
	}
	
}
