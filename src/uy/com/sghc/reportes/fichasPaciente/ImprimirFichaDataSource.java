package uy.com.sghc.reportes.fichasPaciente;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import uy.com.sghc.dtos.FichaDto;
import uy.com.sghc.reportes.controladores.SGHCDataSource;

public class ImprimirFichaDataSource extends SGHCDataSource {
	
	@Override
	public Object getFieldValue(final JRField field) throws JRException {
		Object valor = null;
		if ("numero".equals(field.getName())){
			valor = listaFichas.get(indiceFichaActual).getNumero();
		}else if("fecha".equals(field.getName())){
			valor = listaFichas.get(indiceFichaActual).getFecha();
		}else if("diagnostico".equals(field.getName())){
			valor = listaFichas.get(indiceFichaActual).getDiagnostico();
		}else if("motivoConsulta".equals(field.getName())){
			valor = listaFichas.get(indiceFichaActual).getMotivoConsulta();
		}else if("observaciones".equals(field.getName())){
			valor = listaFichas.get(indiceFichaActual).getObservaciones();
		}
		return valor;
	}

	@Override
	public boolean next() throws JRException {
		return ++indiceFichaActual < listaFichas.size();	
	}

	public void addFichaDto(final FichaDto fichaDto){
		listaFichas.add(fichaDto);
	}
}
