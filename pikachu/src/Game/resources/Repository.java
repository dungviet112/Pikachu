package Game.resources;
import Game.MyFrame;

import java.sql.*;
import java.util.Properties;
public class Repository {
    static String DRIVER_CLASS;
    static String DB_URL;
    static String USER;
    static String PASS;

    public static void getName(Properties p) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Statement stmt = null;
        DRIVER_CLASS = p.getProperty("DRIVER_CLASS");
        DB_URL = p.getProperty("DB_URL");
        USER = p.getProperty("USER");
        PASS = p.getProperty("PASS");
    }
}
