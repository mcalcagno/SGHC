package uy.com.sghc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by saul on 12/12/14.
 */
public class PropController {;
//    private static Logger logger = Logger.getLogger(PropController.class);

    private static final String CONFIG_FILE = "/configuraciones/config.properties";
    private static final String MENSAJES_FILE = "/configuraciones/mensajes.properties";
    private static final String INTERFAZ_FILE = "/configuraciones/interfaz.properties";

    private static final String PROP_CONFIGURABLES = "CONFIGURABLES";
    private static String LOCAL_FILE_DIR;
    
    /**
     * Propiedades de configuracion general del sistema
     */
    public static final String CONFIG_PERSISTENCE_PATH = "PERSISTENCE_PATH";

    /**
     * Propiedades para mensajes del sistema al usuario
     */
    public static final String MESS_LOGIN_USUARIO_VACIO = "Mensajes.USUARIO_VACIO";
    public static final String MESS_LOGIN_PASSWORD_VACIO = "Mensajes.PASSWORD_VACIO";
    public static final String MESS_LOGIN_EXITO = "Mensajes.LOGIN_EXITO";
    public static final String MESS_CERRAR_VENTANA = "Mensajes.CERRAR_VENTANA";
    public static final String MESS_CONFIG_OK = "Mensajes.CONFIG_OK";

    /**
     * Propiedades para configuracion de la interfaz grafica
     */
    public static final String INT_LOGIN_LOGO = "LoginFrame.LOGO";
    public static final String INT_LOGIN_TITULO = "LoginFrame.TITULO";
    public static final String INT_LOGIN_USER = "LoginFrame.USER";
    public static final String INT_LOGIN_PASS = "LoginFrame.PASS";
    public static final String INT_LOGIN_EBOTON = "LoginFrame.EBOTON";



    /**
     * Propiedades para la comunicacion con el WS
     */
    public static final String WS_TRUE                  = "WS.TRUE";
    public static final String WS_URL                    = "WS.URL";
    public static final String WS_TERMINAL               = "WS.TERMINAL";
    public static final String WS_IMPRESORALASER         = "WS.IMPRESORALASER";
    public static final String WS_OPAUTOMATICA           = "WS.OPAUTOMATICA";
    public static final String WS_DEFAULT_TEMPLATE       = "WS.DEFAULT_TEMPLATE";
    public static final String WS_DEFAULT_TEMPLATE_PATH  = "WS.DEFAULT_TEMPLATE_PATH";
    public static final String WS_AGENCIA_FALSE          = "WS.WS_AGENCIA_FALSE";
    public static final String WS_IMG_WEB_SERVER_URL     = "WS.IMG_WEB_SERVER_URL";

    public static String[] getConfigurableProperties() {
        return getPropConfig(PROP_CONFIGURABLES).split(";");
    }
    
//    public static void setPropConfig(Map<String,String> propiedades) throws ConfigException {
//        checkLocalFileDir();
//        FileAccesor.deleteFile(LOCAL_FILE_DIR);
//        for (String key : propiedades.keySet()) {
//            try {
//                String valor = propiedades.get(key);
//                if (!valor.isEmpty()) {
//                    FileAccesor.addLine(key + "=" + propiedades.get(key), LOCAL_FILE_DIR);
//                }
//            } catch (IOException e) {
//                throw new ConfigException(ConfigException.CANT_READ_PERSISTENCE, e);
//            }
//        }
//    }

    public static void resetConfigurations() {
        FileAccesor.deleteFile(LOCAL_FILE_DIR);
    }

    public static String getPropConfig(String parametro) {
        String propLocal = getPropertyLocal(parametro, LOCAL_FILE_DIR);
        if (propLocal==null) {
            propLocal = getPropertyJar(parametro, CONFIG_FILE);
        }
        return propLocal;
    }

    public static String getPropMessage(String tipoMensaje) {
        return getPropertyJar(tipoMensaje, MENSAJES_FILE);
    }

    public static String getPropInterfaz(String tipoMensaje) {
        return getPropertyJar(tipoMensaje, INTERFAZ_FILE);
    }

    private static String getPropertyLocal(String property, String fileName) {
        checkLocalFileDir();
        // intenta obtener la propiedad del archivo físico
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
        } catch (Exception e) {
//            logger.debug("Error al leer el archivo: "+fileName+" "+e.getMessage());
            return null;
        } finally {
            try  {
                if (fis!=null) fis.close();
            } catch (Exception ex) {}
        }
    }

    private static String getPropertyJar(String property, String fileName) {
        try {
            Properties prop = new Properties();
            prop.load(FileAccesor.getInputStream(fileName));
            return prop.getProperty(property);
        } catch (Exception e) {
//            logger.debug("Error al leer el archivo: "+fileName+" "+e.getMessage());
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


            } catch (Exception e) {
//                logger.debug("Error al leer el archivo: " + CONFIG_FILE + " " + e.getMessage());
                LOCAL_FILE_DIR = null;
            }
        }
    }
}