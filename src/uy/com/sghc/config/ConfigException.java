package uy.com.sghc.config;

import java.io.IOException;

public class ConfigException extends Throwable {
    public static final String CANT_READ_PERSISTENCE = "ConfigException.CANT_READ_PERSISTENCE";
    private final String type;

    public ConfigException(String type, IOException e) {
        super(e);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
