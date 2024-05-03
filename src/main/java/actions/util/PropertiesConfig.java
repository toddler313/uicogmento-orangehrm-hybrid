package actions.util;

import actions.commons.GlobalConstants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
    private final Properties properties;
    private static PropertiesConfig configLoader;
    private final String propertiesFilePath = GlobalConstants.get().getPropertiesFilePath();

    private PropertiesConfig() {
        this.properties = PropertiesConfig.propertyLoader(propertiesFilePath);
    }

    public static synchronized PropertiesConfig configReader() {
        return (configLoader == null) ? (new PropertiesConfig()) : configLoader;
    }

    private static Properties propertyLoader(String propertiesFilePath) {
        Properties prob = new Properties();
        BufferedReader bReader;

        try {
            bReader = new BufferedReader(new FileReader(propertiesFilePath));
            prob.load(bReader);
            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties not found at " + propertiesFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file " + propertiesFilePath);
        }
        return prob;
    }

    public long getShortTimeout() {
        String shortTimeout = properties.getProperty("ShortTimeout", "10");
        if (shortTimeout != null) {
            return Long.parseLong(shortTimeout);
        } else {
            throw new RuntimeException("Short Timeout not found in property file, default to 10");
        }
    }

    public long getLongTimeout() {
        String longTimeout = properties.getProperty("LongTimeout", "30");
        if (longTimeout != null) {
            return Long.parseLong(longTimeout);
        } else {
            throw new RuntimeException("Long Timeout not found in property file, default to 30");
        }
    }

    public String getCogmentoUrl() {
        String testEnvUrl = properties.getProperty("CogmentoUrl");
        if (testEnvUrl != null) {
            return testEnvUrl;
        } else {
            throw new RuntimeException("'CogmentoUrl' not found in property file");
        }
    }

    public String getOrangeUrl() {
        String testEnvUrl = properties.getProperty("OrangeUrl");
        if (testEnvUrl != null) {
            return testEnvUrl;
        } else {
            throw new RuntimeException("'OrangeUrl' not found in property file");
        }
    }

    public String getOrangeAdminUsername() {
        String orangeAdminUsername = properties.getProperty("OrangeAdminUsername");
        if (orangeAdminUsername != null) {
            return orangeAdminUsername;
        } else {
            throw new RuntimeException("'OrangeAdminUsername' not found in property file");
        }
    }
    public String getOrangeAdminPassword() {
        String orangeAdminPassword = properties.getProperty("OrangeAdminPassword");
        if (orangeAdminPassword != null) {
            return orangeAdminPassword;
        } else {
            throw new RuntimeException("'OrangeAdminPassword' not found in property file");
        }
    }
    public String getDockerFilePath() {
        String dockerFilePath = properties.getProperty("DockerFilePath");
        if (dockerFilePath != null) {
            return dockerFilePath;
        } else {
            throw new RuntimeException("'DockerFilePath' not found in property file");
        }
    }

}
