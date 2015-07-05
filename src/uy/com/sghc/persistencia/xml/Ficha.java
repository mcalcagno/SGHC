package uy.com.sghc.persistencia.xml;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Ficha")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ficha", propOrder = {
    "numero",
    "fecha",
    "diagnostico",
    "motivoConsulta",
    "obseravaciones"
})

public class Ficha {
	
	@XmlElement(name = "Numero")
    private Integer numero;
	@XmlElement(name = "fecha")
    private Date fecha;
	@XmlElement(name = "Diagnostico")
    private Integer diagnostico;
	@XmlElement(name = "MotivoConsulta")
    private Integer motivoConsulta;
	@XmlElement(name = "Observaciones")
    private Integer observaciones;
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(Integer diagnostico) {
		this.diagnostico = diagnostico;
	}
	public Integer getMotivoConsulta() {
		return motivoConsulta;
	}
	public void setMotivoConsulta(Integer motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	public Integer getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(Integer observaciones) {
		this.observaciones = observaciones;
	}
	
	

}
