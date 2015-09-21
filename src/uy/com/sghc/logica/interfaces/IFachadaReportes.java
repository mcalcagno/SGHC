package uy.com.sghc.logica.interfaces;

import java.util.List;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;

public interface IFachadaReportes {
	
	/**
	 * 
	 * @param paciente
	 * @param fichas
	 */
	public void imprimirFichasPaciente(PacienteDto paciente, List<FichaDto> fichas);
	
	
}
