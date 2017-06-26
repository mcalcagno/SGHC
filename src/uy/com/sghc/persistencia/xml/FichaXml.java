package uy.com.sghc.persistencia.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ficha", propOrder = {
    "numero",
    "fecha",
    "diagnostico",
    "motivoConsulta",
    "observaciones"
})

public class FichaXml {
	
	@XmlElement(name = "Numero")
    private Long numero;
	@XmlElement(name = "Fecha")
    private Date fecha;
	@XmlElement(name = "Diagnostico")
    private String diagnostico;
	@XmlElement(name = "MotivoConsulta")
    private String motivoConsulta;
	@XmlElement(name = "Observaciones")
    private String observaciones;
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getMotivoConsulta() {
		return motivoConsulta;
	}
	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
