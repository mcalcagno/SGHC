package uy.com.sghc.dtos;

import org.apache.commons.lang.StringUtils;

public class PacienteDto {

    private Long ci;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String direccion;
	private String telefono;
	private String celular;
	private String mail;

	public PacienteDto(){
		// constructor por defecto
	}

	public Long getCi() {
		return ci;
	}

	public void setCi(final Long ci) {
		this.ci = ci;
	}

	public String getPrimerNombre() {
		return primerNombre!=null?primerNombre.trim():StringUtils.EMPTY;
	}

	public void setPrimerNombre(final String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre!=null?segundoNombre.trim():StringUtils.EMPTY;
	}

	public void setSegundoNombre(final String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido!=null?primerApellido.trim():StringUtils.EMPTY;
	}

	public void setPrimerApellido(final String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido!=null?segundoApellido.trim():StringUtils.EMPTY;
	}

	public void setSegundoApellido(final String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getDireccion() {
		return direccion!=null?direccion.trim():StringUtils.EMPTY;
	}

	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono!=null?telefono.trim():StringUtils.EMPTY;
	}

	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular!=null?celular.trim():StringUtils.EMPTY;
	}

	public void setCelular(final String celular) {
		this.celular = celular;
	}

	public String getMail() {
		return mail!=null?mail.trim():StringUtils.EMPTY;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "[PacienteDto].[Ci:"+ci+"|PrimerNombre:"+primerNombre+"|SegundoNombre:"+segundoNombre+
			"|PrimerApellido:"+primerApellido+"|SegundoApellido:"+segundoApellido+"|Direccion:"+direccion+
			"|Telefono:"+telefono+"|Celular:"+celular+"|Mail:"+mail+"]";
	}

	public String getNombreYApellido() {
		return getPrimerNombre()+" "+getSegundoNombre()
			+" "+getPrimerApellido()+" "+getSegundoApellido();
	}
}
