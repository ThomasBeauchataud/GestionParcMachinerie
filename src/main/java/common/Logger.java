package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class Logger {

    public static void log(String name, String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:/glassfish4/glassfish/domains/domain2/logs/" + name + ".log"));
            writer.write(new Date().toString() + " : " + message);
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
