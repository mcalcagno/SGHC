package uy.com.sghc.dtos;

import java.util.Date;

public class FichaDto {
	
	private Long numero;
    private Date fecha;
    private String diagnostico;
    private String motivoConsulta;
    private String observaciones;

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

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(final String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(final String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(final String observaciones) {
		this.observaciones = observaciones;
	};
}
