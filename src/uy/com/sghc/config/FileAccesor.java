package uy.com.sghc.config;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public abstract class FileAccesor {
    private static Logger logger = Logger.getLogger(FileAccesor.class);

    public static URL getURL(final String resourceRelativePath) {
        logger.debug("Loading URL: " + resourceRelativePath);
        return FileAccesor.class.getResource(resourceRelativePath);
    }

    public static InputStream getInputStream(final String resourceRelativePath) {
    	logger.debug("Loading URL: " + resourceRelativePath);
        return FileAccesor.class.getResourceAsStream(resourceRelativePath);
    }

    public static void addLine(final String line, final String filePath) throws IOException {
    	final File file = new File(filePath);
        if (!fileExists(filePath)) {
            file.createNewFile();
        }

        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                logger.warn(PropController.getPropMessage(PropController.MESS_PERSIST_FILE_CANT_CLOSE_BUFFER));
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (final IOException ex) {
                logger.warn(PropController.getPropMessage(PropController.MESS_PERSIST_FILE_CANT_CLOSE_FILE));
            }
        }
    }

    public static List<String> getAllLines(final String filePath) throws IOException {
        List<String> lines = new LinkedList<String>();
        if (fileExists(filePath)) {
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;
            try {
                String currentLine;
                fileReader = new FileReader(filePath);
                bufferedReader = new BufferedReader(fileReader);
                while ((currentLine = bufferedReader.readLine()) != null) {
                    lines.add(currentLine);
                }
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (final IOException ex) {
                    logger.warn(PropController.getPropMessage(PropController.MESS_PERSIST_FILE_CANT_CLOSE_BUFFER));
                }
                try {
                    if (fileReader != null) {
                        fileReader.close();
                    }
                } catch (final IOException ex) {
                    logger.warn(PropController.getPropMessage(PropController.MESS_PERSIST_FILE_CANT_CLOSE_FILE));
                }
            }
        }
        return lines;
    }

    public static String checkDirectorios(final String root, final String relativePath) {
    	String raiz = root;
        String[] subDirs = relativePath.split(File.separator);
        for (String subDir : subDirs) {
            File subDirFile = new File(root, subDir);
            if (!subDirFile.exists()) {
                subDirFile.mkdir();
            }
            raiz = subDirFile.getAbsolutePath();
        }
        return raiz + File.separator;
    }

    public static void deleteFile(final String filePath) {
        if(FileAccesor.fileExists(filePath)) {
            File file = new File(filePath);
            file.delete();
        }
    }

    private static boolean fileExists(final String filePath) {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }
}
