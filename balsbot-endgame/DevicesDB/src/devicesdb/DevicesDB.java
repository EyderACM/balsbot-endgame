/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devicesdb;

import java.io.File;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.*;


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
                 + this.database
                 + "?servetTimezone=UTC",
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
    
    private Scanner scan;
    
    public void openFile(){
    
        try{
            scan = new Scanner(new File("devices.txt"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void readFile(){
        while(scan.hasNext()){
            String device_id = scan.next();
            String device_name = scan.next();
            String device_type = scan.next();
            String device_brand = scan.next();
            String device_model = scan.next();
            String state = scan.next();
            
            setStatement(device_id, device_name, device_type, device_brand, device_model, state);
            System.out.println(device_id);
        }
    }
    
    public void setStatement(String id, String name, String type, String brand, String model, String state){
        try{
            PreparedStatement pstmt;
            Connection connection = new DevicesDB().getConnection();
            
            pstmt = connection.prepareStatement("insert into devices values(?,?,?,?,?,?)");
            pstmt.setString(1, id);
            pstmt.setString(2, encryptData(name));
            pstmt.setString(3, encryptData(type));
            pstmt.setString(4, encryptData(brand));
            pstmt.setString(5, encryptData(model));
            pstmt.setString(6, state);
            
            pstmt.execute();
            
            connection.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeFile(){
        scan.close();
    }
    
    private String encryptData(String data){
        try{
            MessageDigest digs = MessageDigest.getInstance("MD5");
            digs.update(data.getBytes("UTF8"));
            
            String str = new String(digs.digest());
            
            return str;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DevicesDB devfile = new DevicesDB();
        devfile.openFile();
        devfile.readFile();
        devfile.closeFile();
    }
    
}
