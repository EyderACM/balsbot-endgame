/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devicesdb;

import java.sql.*;

/**
 *
 * @author Eduardo_Cruz
 */
public class DevicesQuery {
    private DevicesDB dbconnection;
    private Statement query;
    private ResultSet resultset;
    
    
public ResultSet getDevicesInfo(){
    String SQLquery ="SELECT * FROM devices";
    
    try{
        dbconnection = new DevicesDB();
        Connection connection = dbconnection.getConnection();
        query = connection.createStatement();
        resultset = query.executeQuery(SQLquery);
        
    }
    catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    
    return resultset;
}

public void closeDBQuery(){
    dbconnection.closeConnection();
}

public static String decrypt(String encryptData, String key){
    
    String decData = null;
    
    try{
        Encrypt aes = new Encrypt(key);
        decData = aes.decryptData(encryptData);
    }catch(Exception ex){
        System.out.println(ex.getMessage());
    }
    
    return decData;
}


public static void main(String args[]){
       
    try{
        ResultSet  rs = new DevicesQuery().getDevicesInfo();
        String records ="";
        while(rs.next()){
            
        records += rs.getInt("id")+ "\t" +
                   decrypt(rs.getString("device_name"), "h29dn230jc38s61h") + "\t" +
                   decrypt(rs.getString("device_type"), "ijd3d3f9f4fwfknf") + "\t" +
                   decrypt(rs.getString("device_brand"),"eifeifjeifjifjei") + "\t" +
                   decrypt(rs.getString("device_model"),"9fi4fjf44f4f4nt4")+ "\t" +
                   rs.getInt("state")+"\n" ;
        }
        System.out.println(records);
    
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
}
}
