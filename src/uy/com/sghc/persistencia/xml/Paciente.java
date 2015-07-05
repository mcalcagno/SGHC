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
    "nombre1",
    "nombre2",
    "apellido1",
    "apellido2",
    "direccion",
    "telefono",
    "email"
})
public class Paciente {

	@XmlElement(name = "CI")
    private Integer ci;
	@XmlElement(name = "Nombre1")
	private String nombre1;
	@XmlElement(name = "Nombre2")
	private String nombre2;
	@XmlElement(name = "Apellido1")
	private String apellido1;
	@XmlElement(name = "Apellido2")
	private String apellido2;
	@XmlElement(name = "Direccion")
	private String direccion;
	@XmlElement(name = "Telefono")
	private String telefono;
	@XmlElement(name = "Email")
	private String email;
	
	public Integer getCI() {
		return ci;
	}
	public void setCI(Integer ci) {
		this.ci = ci;
	}
	public String getNombre1() {
		return nombre1;
	}
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	public String getNombre2() {
		return nombre2;
	}
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
