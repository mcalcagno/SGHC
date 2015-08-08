package uy.com.sghc.logica.entidades;

import java.util.ArrayList;
import java.util.List;

import uy.com.sghc.dtos.PacienteDto;

public class Paciente {

    private Long ci;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String direccion;
	private String telefono;
	private String celular;
	private String mail;
	
	private List<Ficha> fichas;

	public Paciente(){
		// constructor por defecto
	}
	
	public Paciente(final PacienteDto pacientedto) {
		this.ci = pacientedto.getCi();
		this.celular = pacientedto.getCelular();
		this.direccion = pacientedto.getDireccion();
		this.mail = pacientedto.getMail();
		this.primerApellido = pacientedto.getPrimerApellido();
		this.primerNombre = pacientedto.getPrimerNombre();
		this.segundoApellido = pacientedto.getSegundoApellido();
		this.segundoNombre = pacientedto.getSegundoNombre();
		this.telefono = pacientedto.getTelefono();
		this.fichas = new ArrayList<Ficha>();		
	}

	public Long getCi() {
		return ci;
	}

	public void setCi(final Long ci) {
		this.ci = ci;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(final String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(final String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(final String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(final String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(final String celular) {
		this.celular = celular;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	public List<Ficha> getFichas() {
		return fichas;
	}

	public void setFichas(final List<Ficha> fichas) {
		this.fichas = fichas;
	}
	
	@Override
	public String toString() {
		return "[Paciente].[Ci:"+ci+"|PrimerNombre:"+primerNombre+"|SegundoNombre:"+segundoNombre+
			"|PrimerApellido:"+primerApellido+"|SegundoApellido:"+segundoApellido+"|Direccion:"+direccion+
			"|Telefono:"+telefono+"|Celular:"+celular+"|Mail:"+mail+"]";
	}
	
	public PacienteDto getPacienteDto(){
		
		PacienteDto pacienteDto = new PacienteDto();
		pacienteDto.setCi(ci);
		pacienteDto.setPrimerNombre(primerNombre);
		pacienteDto.setSegundoNombre(segundoNombre);
		pacienteDto.setPrimerApellido(primerApellido);
		pacienteDto.setSegundoApellido(segundoApellido);
		pacienteDto.setDireccion(direccion);
		pacienteDto.setMail(mail);
		pacienteDto.setCelular(celular);
		return pacienteDto;
		
	}
	
}
