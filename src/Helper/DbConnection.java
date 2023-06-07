package Helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private Connection connection = null;

    public Connection connectionToDB() {

        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this.connection;
    }

    public static Connection getInstance(){
        DbConnection dbConnection = new DbConnection();
        return dbConnection.connectionToDB();
    }
}
