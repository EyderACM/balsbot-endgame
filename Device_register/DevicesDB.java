/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devicesdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author Eduardo_Cruz
 */
public class DevicesDB {
    private Connection connection;
    private Properties connectionProps;
    private String dbms = "mysql";
    private String serverName = "localhost";
    private String portNumber = "3306";
    private String database = "devices";
    
    private void getCredentials(){
        connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "$Pedro$Eduardo$Cruz");
    }
    
    public Connection getConnection() throws SQLException{
        
        getCredentials();
        if (this.dbms.equals("mysql")) {
            connection = DriverManager.getConnection(
                "jdbc:" + this.dbms + "://" +
                this.serverName +
                ":" + this.portNumber + "/"
                 + this.database,
                 connectionProps);
        }
        System.out.println("Connection Established");
        return connection;
    }
    
    public boolean closeConnection(){
        boolean flag = true;
        try{
            connection.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            flag = false;
        }
        
        return flag;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            PreparedStatement pstmt;
            Connection connection = new DevicesDB().getConnection();
            
            pstmt = connection.prepareStatement("insert into devices values(?,?,?,?,?,?)");
            pstmt.setString(1, "6");
            pstmt.setString(2, "Anita'sTv");
            pstmt.setString(3, "TV");
            pstmt.setString(4, "Panasonic");
            pstmt.setString(5, "j20OJjinI93");
            pstmt.setString(6, "0");
            pstmt.execute();
            
            connection.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
