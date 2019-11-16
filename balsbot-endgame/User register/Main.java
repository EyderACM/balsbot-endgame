//Author: Rodrigo Hernandez

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.ODBQuery.postInfo;

public class Main {
    public static void main(String[] args)throws Exception{
        try{
            Connection connection = new ODBConnection().getConnection();
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        try{
            String name = "Jotaro Kujo";
            int admin = 1;
            postInfo(name, admin);
            ResultSet rs = new DBQuery().getCustomersAccountsInfo(name, admin);
            String records = "";
            while (rs.next()){
                records += rs.getInt("iduser") + "\t" + rs.getString("name") + "\t" +
                        rs.getString("admin") + "\n";
            }
            System.out.println(records);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
