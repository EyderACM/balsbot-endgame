//Author: Rodrigo Hernandez

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private Connection connection;
    private Properties connectionProps;
    private String dbms = "mysql";
    private String serverName = "localhost";
    private String portNumber = "3306";
    private String database = "acme";

    private void getCredentials(){
        connectionProps = new Properties();
        connectionProps.put("user", "Rodrigo");
        connectionProps.put("password", "Estrellita10");
    }

    public Connection getConnection() throws SQLException {
        getCredentials();
        try {
            connection = DriverManager.getConnection("jdbc:" + this.dbms + "://" +
                    this.serverName + ":" + this.portNumber + "/" +this.database, connectionProps);
            System.out.println("Connected");
            return connection;
        }catch (Exception e){System.out.println(e);}
        return null;
    }

    public boolean closeConnection(){
        boolean flag = true;
        try {
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            flag = false;
        }
        return flag;
    }

    public static void main(String args[]){
        try{
            Connection connection = new DBConnection().getConnection();
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
