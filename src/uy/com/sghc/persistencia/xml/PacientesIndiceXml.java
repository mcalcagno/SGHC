package uy.com.sghc.persistencia.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PacientesIndice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PacientesIndice", propOrder = {
    "pacientes"
})	

public class PacientesIndiceXml {

	@XmlElement(name = "Paciente")
	private List<Long> pacientes = new ArrayList<Long>();

	public List<Long> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Long> pacientes) {
		this.pacientes = pacientes;
	}
	
}
