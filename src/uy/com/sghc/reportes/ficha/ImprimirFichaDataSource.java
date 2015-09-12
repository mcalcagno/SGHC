package uy.com.sghc.reportes.ficha;

import java.util.ArrayList;
import java.util.List;

import uy.com.sghc.dtos.FichaDto;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ImprimirFichaDataSource implements JRDataSource {

	private List<FichaDto> listaFichas = new ArrayList<FichaDto>();
	private int indiceFichaActual = -1;
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
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

	public void addFichaDto(FichaDto fichaDto){
		listaFichas.add(fichaDto);
	}
}
