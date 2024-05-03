package actions.commons;

import lombok.Getter;

import java.io.File;

@Getter
public class GlobalConstants {
    private static GlobalConstants globConst;

    private GlobalConstants() {}

    public static synchronized GlobalConstants get() {
        return (globConst == null) ? (new GlobalConstants()) : globConst;
    }

    private final String osName = System.getProperty("os.name");
    private final String classPath = System.getProperty("user.dir");
    private final String javaVersion = System.getProperty("java.version");

    private final String propertiesFilePath = classPath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config.properties";
    private final String uploadFolderPath = classPath + File.separator + "uploadFiles" + File.separator;

    private final String alphaNumericString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private final String characterString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String userUsername = "test1";
    private final String userPassword = "A123b456!";
}
