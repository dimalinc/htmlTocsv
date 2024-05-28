package keystone.utils;

import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class MyLogger {
   private static Logger log;

    static public void MyLogger() {
        try (FileInputStream ins = new FileInputStream("src/main/resources/logerConfig.properties")) {
            LogManager.getLogManager().readConfiguration(ins);
            log = Logger.getLogger(MyLogger.class.getName());
            LogManager.getLogManager().readConfiguration();
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }

    public static Logger getLog() {
        return log;
    }
}
