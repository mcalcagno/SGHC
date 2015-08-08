package uy.com.sghc.persistencia.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Paciente")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Paciente", propOrder = {
    "ci",
    "primerNombre",
    "segundoNombre",
    "primerApellido",
    "segundoApellido",
    "direccion",
    "telefono",
    "celular",
    "mail"
})
public class PacienteXml {

	@XmlElement(name = "CI")
    private Long ci;
	@XmlElement(name = "PrimerNombre")
	private String primerNombre;
	@XmlElement(name = "SegundoNombre")
	private String segundoNombre;
	@XmlElement(name = "PrimerApellido")
	private String primerApellido;
	@XmlElement(name = "SegundoApellido")
	private String segundoApellido;
	@XmlElement(name = "Direccion")
	private String direccion;
	@XmlElement(name = "Telefono")
	private String telefono;
	@XmlElement(name = "Celular")
	private String celular;
	@XmlElement(name = "Mail")
	private String mail;
	
	public Long getCi() {
		return ci;
	}
	public void setCi(Long ci) {
		this.ci = ci;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
}
