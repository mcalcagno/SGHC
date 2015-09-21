package uy.com.sghc.reportes.fichasPaciente;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.dtos.PacienteDto;
import uy.com.sghc.reportes.controladores.ManejadorReportes;

public class ImprimirFichas {

	public static void imprimir(final PacienteDto paciente, final List<FichaDto> fichas) {

		final ImprimirFichaDataSource dataSource = new ImprimirFichaDataSource();
		for (final FichaDto ficha : fichas) {
			dataSource.addFichaDto(ficha);
		}		
		
		final Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cedula", paciente.getCi());
		parametros.put("nombre", paciente.getNombreYApellido());
		
		final ManejadorReportes controlReportes = new ManejadorReportes();
		controlReportes.imprimirReporte(dataSource, "C:\\Users\\mcalcagno\\SGHC\\src\\uy\\com\\sghc\\reportes\\fichasPaciente\\FichasPaciente.jrxml", parametros, "reporte.pdf");		
	}	
}
