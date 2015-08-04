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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.entidades.Paciente;
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
	        FileWriter outFile = new FileWriter("C:\\ArchivosXML\\Pacientes"+File.pathSeparator+String.valueOf(paciente.getCi())+".xml");
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
	
	private static Document getDocument(String ruta,String nombreArchivo) throws SGHCExcepcion{
		Document doc = null;
		try{
			File file = new File(ruta+File.pathSeparator+nombreArchivo);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(file);
		}catch(NullPointerException e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(ParserConfigurationException e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(IOException e){
			//TODO: loguear
			throw new SGHCExcepcion("ERROR AL CREAR CONTEXTO JAXB");
		}catch(SAXException e){
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
	
	
	
}
