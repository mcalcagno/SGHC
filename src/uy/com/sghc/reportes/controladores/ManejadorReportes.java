package uy.com.sghc.reportes.controladores;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;

public class ManejadorReportes {
	
	private static Logger logger = Logger.getLogger(ManejadorReportes.class);
	
	/**
	 * Ejecuta, guarda e imprime en pantalla un reporte partiendo de la lógica especificada en su jrxml y el datasource correspondiente
	 * @param dataSource del reporte a imprimir
	 * @param archivoJrxml que tiene la especificación del reporte
	 * @param param: opcionalmente se le pueden pasar parámetros al reporte
	 * @param archivoDestino: ruta y nombre del archivo PDF a partir de la ruta base de los reportes
	 */
	public void imprimirReporte(final SGHCDataSource dataSource, final InputStream archivoJrxml, final Map<String, Object> param, 
			final String archivoDestino) {
		try {			
//			final JasperDesign jasperDesgin = JRXmlLoader.load(archivoJrxml);
			final JasperReport jasperReport = JasperCompileManager.compileReport(archivoJrxml);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
			   
			// TODO: colocar en una property la ruta base de los reportes
			final String fileDestinoStr = System.getProperty("java.io.tmpdir")+File.separator+System.currentTimeMillis()+archivoDestino;
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileDestinoStr);
			Desktop.getDesktop().open(new File(fileDestinoStr));
		} catch(final FileNotFoundException e){
			logger.error("Error: archivo no encontrado ["+archivoJrxml+"]", e);
		} catch (final JRException e) {
			logger.error(e);
		} catch (final IOException e) {
			logger.error(e);
		}
	}	
}
