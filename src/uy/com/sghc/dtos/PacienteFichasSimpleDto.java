package uy.com.sghc.dtos;

import java.util.List;

/**
 * Este DTO es para lista las fichas de un paciente, se va a usar para listar
 * información en el frontend.
 * 
 * @author saul
 * 
 */
public class PacienteFichasSimpleDto {

	private Long cedula;
	private List<Long> fichas;

	public PacienteFichasSimpleDto() {
		// constructor por defecto
	}

	public PacienteFichasSimpleDto(final Long cedula, final List<Long> fichas) {
		this.cedula = cedula;
		this.fichas = fichas;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(final Long cedula) {
		this.cedula = cedula;
	}

	public List<Long> getFichas() {
		return fichas;
	}

	public void setFichas(final List<Long> fichas) {
		this.fichas = fichas;
	}
}
