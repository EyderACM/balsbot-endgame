//Author: Rodrigo Hernandez

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ODBConnection {
    private Connection connection;
    private Properties connectionProps;
    private String dbms = "mysql";
    private String serverName = "database-demo.cxmtkcnwi46y.us-east-1.rds.amazonaws.com";
    private String portNumber = "3306";
    private String database = "domotica_poo";

    private void getCredentials(){
        connectionProps = new Properties();
        connectionProps.put("user", "admin");
        connectionProps.put("password", "Axelpoo25");
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
            Connection connection = new ODBConnection().getConnection();
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
