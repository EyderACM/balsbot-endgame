//Author: Rodrigo Hernandez

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static database.DBQuery.postInfo;

public class Main {
    public static void main(String[] args)throws Exception{
        try{
            Connection connection = new DBConnection().getConnection();
            connection.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        try{
            String firstName = "Helena";
            String lastName = "Nito";
            postInfo(firstName, lastName);
            ResultSet rs = new DBQuery().getCustomersAccountsInfo(firstName, lastName);
            String records = "";
            while (rs.next()){
                records += rs.getInt("id") + "\t" + rs.getString("firstName") +
                        rs.getString("lastName") + "\n";
            }
            System.out.println(records);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

}
