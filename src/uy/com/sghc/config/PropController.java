package uy.com.sghc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropController {;
    private static Logger logger = Logger.getLogger(PropController.class);

    private static final String CONFIG_FILE = "/configuraciones/config.properties";
    private static final String MENSAJES_FILE = "/configuraciones/mensajes.properties";
    private static final String INTERFAZ_FILE = "/configuraciones/interfaz.properties";

    private static final String PROP_CONFIGURABLES = "CONFIGURABLES";
    private static String LOCAL_FILE_DIR;
    
    /**
     * Propiedades de configuracion general del sistema
     */
    public static final String CONFIG_PERSISTENCE_PATH = "PERSISTENCE_PATH";
    public static final String CONFIG_PERSISTENCE_PACIENTE_PATH = "Paciente.PATH";
    public static final String CONFIG_PERSISTENCE_PACIENTE_EXTENSION = "Paciente.EXTENSION";
    
    /**
     * Propiedades para mensajes del sistema al usuario
     */
    public static final String MESS_LOGIN_USUARIO_VACIO = "Mensajes.USUARIO_VACIO";
    public static final String MESS_LOGIN_PASSWORD_VACIO = "Mensajes.PASSWORD_VACIO";
    public static final String MESS_LOGIN_EXITO = "Mensajes.LOGIN_EXITO";
    public static final String MESS_CERRAR_VENTANA = "Mensajes.CERRAR_VENTANA";
    public static final String MESS_CONFIG_OK = "Mensajes.CONFIG_OK";
	public static final String MESS_PERSIST_FILE_CANT_CLOSE_BUFFER = "Mensajes.PERSIST_FILE_CANT_CLOSE_BUFFER";
	public static final String MESS_PERSIST_FILE_CANT_CLOSE_FILE = "Mensajes.PERSIST_FILE_CANT_CLOSE_FILE";

    /**
     * Propiedades para configuracion de la interfaz grafica
     */
    public static final String INT_LOGIN_LOGO = "LoginFrame.LOGO";
    public static final String INT_LOGIN_TITULO = "LoginFrame.TITULO";
    public static final String INT_LOGIN_USER = "LoginFrame.USER";
    public static final String INT_LOGIN_PASS = "LoginFrame.PASS";
    public static final String INT_LOGIN_BOTON = "LoginFrame.BOTON";
    public static final String DESKTOP_TITULO = "Desktop.TITULO"; //"Sistema de Gesti�n de Historias Cl�nicas";
    public static final String DESKTOP_MENU_INICIAL = "Desktop.MENU_INICIAL"; //"Inicio";
    public static final String DESKTOP_MENU_INICIAL_PACIENTE = "Desktop.MENU_INICIAL_PACIENTE"; //"Pacientes";
    public static final String DESKTOP_MENU_INICIAL_FICHAS = "Desktop.MENU_INICIAL_FICHAS"; //"Fichas";
    public static final String DESKTOP_LOOKANDFEEL_WINDOWS = "Desktop.LOOKANDFEEL_WINDOWS"; //"com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    
    public static String[] getConfigurableProperties() {
        return getPropConfig(PROP_CONFIGURABLES).split(";");
    }
    
    public static void setPropConfig(final Map<String,String> propiedades) throws ConfigException {
        checkLocalFileDir();
        FileAccesor.deleteFile(LOCAL_FILE_DIR);
        for (String key : propiedades.keySet()) {
            try {
                String valor = propiedades.get(key);
                if (!valor.isEmpty()) {
                    FileAccesor.addLine(key + "=" + propiedades.get(key), LOCAL_FILE_DIR);
                }
            } catch (final IOException e) {
                throw new ConfigException(ConfigException.CANT_READ_PERSISTENCE, e);
            }
        }
    }

    public static void resetConfigurations() {
        FileAccesor.deleteFile(LOCAL_FILE_DIR);
    }

    public static String getPropConfig(final String parametro) {
    	return getPropertyJar(parametro, CONFIG_FILE);
    }

    public static String getPropMessage(final String tipoMensaje) {
        return getPropertyJar(tipoMensaje, MENSAJES_FILE);
    }

    public static String getPropInterfaz(final String tipoMensaje) {
        return getPropertyJar(tipoMensaje, INTERFAZ_FILE);
    }

    private static String getPropertyLocal(final String property, final String fileName) {
        checkLocalFileDir();
        File f = new File(LOCAL_FILE_DIR);
        FileInputStream fis = null;
        try {
            if (f.exists()) {
                fis = new FileInputStream(f);
                Properties prop = new Properties();
                prop.load(fis);
                return prop.getProperty(property);
            } else {
                return null;
            }
        } catch (final Exception e) {
            logger.debug("Error al leer el archivo: "+fileName+" "+e.getMessage());
            return null;
        } finally {
            try  {
                if (fis!=null) {
                	fis.close();
                }
            } catch (final Exception ex) {
            	logger.debug("Error al cerrar el input stream"+ex.getMessage());
            	return null;
            }
        }
    }

    private static String getPropertyJar(final String property, final String fileName) {
        try {
            Properties prop = new Properties();
            prop.load(FileAccesor.getInputStream(fileName));
            return prop.getProperty(property);
        } catch (final Exception e) {
            logger.debug("Error al leer el archivo: "+fileName+" "+e.getMessage());
            return null;
        }
    }

    private static void checkLocalFileDir() {
        if (LOCAL_FILE_DIR==null) {
            try {
                Properties prop = new Properties();
                prop.load(FileAccesor.getInputStream(CONFIG_FILE));
                LOCAL_FILE_DIR = prop.getProperty(CONFIG_PERSISTENCE_PATH) + CONFIG_FILE;

                String[] relativePaths = CONFIG_FILE.split(File.separator);
                String relativePath = prop.getProperty(CONFIG_PERSISTENCE_PATH);
                for (int i = 0; i<relativePaths.length-1; i++) {
                    relativePath += File.separator + relativePaths[i];
                }
                LOCAL_FILE_DIR = FileAccesor.checkDirectorios(System.getProperty("user.home"), relativePath) + relativePaths[relativePaths.length-1];
            } catch (final Exception e) {
                logger.debug("Error al leer el archivo: " + CONFIG_FILE + " " + e.getMessage());
                LOCAL_FILE_DIR = null;
            }
        }
    }
}
