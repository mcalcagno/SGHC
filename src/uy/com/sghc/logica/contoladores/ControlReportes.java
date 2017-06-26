package uy.com.sghc.logica.contoladores;

import java.util.List;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.logica.interfaces.IFachadaReportes;
import uy.com.sghc.reportes.fichasPaciente.ImprimirFichas;

public class ControlReportes implements IFachadaReportes {

	@Override
	public void imprimirFichasPaciente(final PacienteDto paciente, final List<FichaDto> fichas) {
		ImprimirFichas.imprimir(paciente, fichas);
	}
	
}
