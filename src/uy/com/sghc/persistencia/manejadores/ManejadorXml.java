package uy.com.sghc.persistencia.manejadores;

public class ManejadorXml {
	private static ManejadorXml instance = null;
	
	private ManejadorXml(){};
	
	public static ManejadorXml newInstance() {
		if (instance == null) {
			instance = new ManejadorXml();
		}		
		return instance;
	}
}
