package uy.com.sghc.logica.entidades;

import java.util.Date;

public class Ficha {
	
	private Long numero;
    private Date fecha;
    private Integer diagnostico;
    private Integer motivoConsulta;
    private Integer observaciones;
	
	public Ficha(){
		// constructor por defecto
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(final Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	public Integer getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(final Integer diagnostico) {
		this.diagnostico = diagnostico;
	}

	public Integer getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(final Integer motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public Integer getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(final Integer observaciones) {
		this.observaciones = observaciones;
	};
}
