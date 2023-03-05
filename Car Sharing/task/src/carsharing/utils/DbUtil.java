package carsharing.utils;

import java.sql.*;
import java.util.Optional;

public class DbUtil {

    private static volatile DbUtil singleInstance;
    private static final String JDBC_DRIVER = "org.h2.Driver";

    // "jdbc:h2:./src/carsharing/db/carsharing;Mode=MySQL"; this conn string for "check stage" process
    private static final String DB_URL = "jdbc:h2:./Car Sharing/task/src/carsharing/resources/db/carsharing;Mode=MySQL";
    private static Connection connection;

    private DbUtil() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DbUtil getDbUtil() {
        DbUtil innerInstance = singleInstance;
        if (innerInstance != null) {
            return innerInstance;
        }

        synchronized (DbUtil.class) {
            if (singleInstance == null) {
                singleInstance = new DbUtil();
            }
            return singleInstance;
        }
    }

    public int executeUpdate(String query) {
        try {
            return connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Optional<ResultSet> executeQuery(String query) {
        try {
            return Optional.ofNullable(connection.createStatement().executeQuery(query));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error during connection closing.");
            e.printStackTrace();
        }
    }

}
