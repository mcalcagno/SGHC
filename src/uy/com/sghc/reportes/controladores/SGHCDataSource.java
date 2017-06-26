package uy.com.sghc.reportes.controladores;

import java.util.ArrayList;
import java.util.List;

import uy.com.sghc.dtos.FichaDto;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public abstract class SGHCDataSource  implements JRDataSource {
	
	protected List<FichaDto> listaFichas = new ArrayList<FichaDto>();
	protected int indiceFichaActual = -1;
	
	@Override
	public abstract Object getFieldValue(JRField arg0) throws JRException;

	@Override
	public abstract boolean next() throws JRException;

}
