/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devicesdb;

import java.io.File;
import java.security.MessageDigest; //Change
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
    private final String dbms = "mysql";
    private final String serverName = "localhost";
    private final String portNumber = "3306";
    private final String database = "devices";
    private Scanner scan;
    private Encrypt encrypt;
    
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
            
            String name_d = AESEnc("h29dn230jc38s61h", name);
            String type_d = AESEnc("ijd3d3f9f4fwfknf", type);
            String brand_d = AESEnc("eifeifjeifjifjei", brand);
            String model_d = AESEnc("9fi4fjf44f4f4nt4", model);
            
            pstmt = connection.prepareStatement("insert into devices values(?,?,?,?,?,?)");
            pstmt.setString(1, id);
            pstmt.setString(2, name_d);
            pstmt.setString(3, type_d);
            pstmt.setString(4, brand_d);
            pstmt.setString(5, model_d);
            pstmt.setString(6, state);
            
            pstmt.execute();
            
            connection.close();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private String AESEnc(String key, String data){
        String encdata = null;
        
        try{
            Encrypt aes = new Encrypt(key);
            encdata = aes.encrypt(data);
            System.out.println(encdata);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return encdata;
    }
    
    public void closeFile(){
        scan.close();
    }
    

//    private String encryptData(String data){
//        try{
//            MessageDigest digs = MessageDigest.getInstance("MD5");
//            digs.update(data.getBytes("UTF8"));
//            
//            String str = new String(digs.digest());
//            
//            return str;
//        }catch(Exception ex){
//            System.out.println(ex.getMessage());
//            return "";
//        }
//    }
    
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
