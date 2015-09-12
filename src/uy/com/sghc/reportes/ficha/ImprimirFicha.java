package uy.com.sghc.reportes.ficha;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import uy.com.sghc.dtos.FichaDto;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ImprimirFicha {

	public static  void imprimir() {
		try{
			ImprimirFichaDataSource dataSource = new ImprimirFichaDataSource();
			JasperPrint jasperPrint = null;
			InputStream inputStream = null;
			FichaDto f1 = new FichaDto();
			f1.setNumero(new Long(111));
			f1.setObservaciones("observaciones de la primer ficha");
			FichaDto f2 = new FichaDto();
			f2.setNumero(new Long(22));
			f2.setObservaciones("observaciones de la segunda ficha");
			dataSource.addFichaDto(f1);
			dataSource.addFichaDto(f2);
			inputStream = new FileInputStream("C:\\Users\\mcalcagno\\SGHC\\src\\uy\\com\\sghc\\reportes\\ficha\\Ficha.jrxml");
			JasperDesign jasperDesgin = JRXmlLoader.load(inputStream);
			JasperReport jasperreport = JasperCompileManager.compileReport(jasperDesgin);
			jasperPrint = JasperFillManager.fillReport(jasperreport,null,dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Reportes\\reporte.pdf");
			Desktop.getDesktop().open(new File("C:\\Reportes\\reporte.pdf"));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
