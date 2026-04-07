package mvcs.services;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnect {
    private static SQLiteDataSource datasource = new SQLiteDataSource();
    
    static{
        datasource.setUrl("jdbc:sqlite:database/book.db");
    }
    
    public static Connection getConnection() throws SQLException{
        return datasource.getConnection();
    }
}
