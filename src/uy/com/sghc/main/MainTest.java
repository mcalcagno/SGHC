package uy.com.sghc.main;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import uy.com.sghc.config.PropController;
import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.excepciones.SGHCExcepcion;
import uy.com.sghc.logica.contoladores.ControlPaciente;
import uy.com.sghc.logica.entidades.Ficha;
import uy.com.sghc.logica.entidades.Paciente;
import uy.com.sghc.logica.interfaces.IFachadaPaciente;
import uy.com.sghc.persistencia.controladores.ControlPersistirPaciente;
import uy.com.sghc.persistencia.interfaces.IPersistirPaciente;
import uy.com.sghc.persistencia.manejadores.ManejadorXml;
import uy.com.sghc.persistencia.xml.PacienteXml;
import uy.com.sghc.persistencia.xml.PacientesIndiceXml;


public class MainTest {

	
	public static void main(String[] args){
		
		IFachadaPaciente i = new ControlPaciente();
		
		try {

			PacienteDto p1 = new PacienteDto();
			p1.setCi(new Long(1891));
			p1.setPrimerNombre("Pablo");
			p1.setSegundoNombre("Javier");
			p1.setPrimerApellido("Bengochea");
			p1.setSegundoApellido("El Profe");
			p1.setDireccion("---");
			p1.setTelefono("000000");
			p1.setCelular("1234567");
			p1.setMail("jp@paciente.com.uy");
			i.crearPaciente(p1);
			
			FichaDto f1 = new FichaDto();
			f1.setNumero(new Long(122));
			f1.setFecha(new Date());
			f1.setDiagnostico("dignostic de mierda");
			f1.setMotivoConsulta("motivo puto");
			f1.setObservaciones("observaciones");
			
			i.agregarFichaPaciente(new Long(1891), f1);
			
			FichaDto f2 = new FichaDto();
			f2.setNumero(new Long(122));
			f2.setFecha(new Date());
			f2.setDiagnostico("dignostic de mierda");
			f2.setMotivoConsulta("motivo puto");
			f2.setObservaciones("observaciones");
			
			i.agregarFichaPaciente(new Long(1891), f2);
			
			/*
			for(int ll = 501;ll <= 2000;ll++ ){
				PacienteDto p = new PacienteDto();
				p.setCi(new Long(ll+1000));
				p.setPrimerNombre("nombre"+String.valueOf(ll));
				p.setSegundoNombre("nombre"+String.valueOf(ll));
				p.setPrimerApellido("nombre"+String.valueOf(ll));
				p.setSegundoApellido("nombre"+String.valueOf(ll));
				p.setDireccion("---");
				p.setTelefono("000000");
				p.setCelular("1234567");
				p.setMail("paciente@paciente.com.uy");
				i.crearPaciente(p);
			}
			*/
/*			List<PacienteDto> dtos = i.buscarPacientes("50489462");
			Iterator<PacienteDto> it = dtos.iterator();
			while(it.hasNext()){
				PacienteDto dto = it.next();
				System.out.println(dto.toString());
			}
			//System.out.print(p3.toString());
		} catch (SGHCExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		/*
		PacientesIndice indice = new PacientesIndice();
		List<Long> lista = indice.getPacientes();
		lista.add(new Long(50489462));
		lista.add(new Long(88898989));
		lista.add(new Long(3232222));
		try{
		*/	
		}catch (Exception e) {
			String msj = e.getMessage();
		}
		
		
	}
	
	
}
