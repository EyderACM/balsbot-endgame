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


public static void main(String args[]){
       
    try{
        ResultSet  rs = new DevicesQuery().getDevicesInfo();
        String records ="";
        while(rs.next()){
        records += rs.getInt("id")+ "\t" +
                   rs.getString("device_name") + "\t" +
                   rs.getString("device_type")+ "\t" +
                   rs.getString("device_brand")+ "\t" +
                   rs.getString("device_model")+ "\t" +
                   rs.getInt("state")+"\n" ;
        }
        System.out.println(records);
    
    }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       
}
}
