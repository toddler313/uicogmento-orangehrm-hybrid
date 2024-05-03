package actions.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnection {

    public static Connection getSQLServerConnection() {
        String hostName = "localhost";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "autotest";
        String username = "sa";
        String password = "Test@31390";

        return getSQLServerConnection(hostName, sqlInstanceName, database, username, password);
    }

    private static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String username, String password) {
        Connection conn = null;
        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionURL = "jdbc:sqlserver://" + hostName + ":11433" + ";instance=" + sqlInstanceName + ";databaseName=" + database
                    + ";encrypt=true;trustServerCertificate=true";
            conn = DriverManager.getConnection(connectionURL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
