//Author: Rodrigo Hernandez

package database;

import java.sql.*;

public class ODBQuery {
    private static ODBConnection odbConnection;
    private static Statement query;
    private ResultSet resultSet;

    public ResultSet getCustomersAccountsInfo(String name, int admin){
        String SQLquery = "SELECT * FROM users WHERE name=\'"+name+"\' and admin=\'"+admin+"\'";
        try {
            odbConnection = new ODBConnection();
            Connection connection = odbConnection.getConnection();
            query = connection.createStatement();
            resultSet = query.executeQuery(SQLquery);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public static void postInfo(String name, int admin){
        String SQLQuery = "INSERT INTO users(name, admin) VALUES ('"+name+"', '"+admin+"')";
        try {
            odbConnection = new ODBConnection();
            Connection connection = odbConnection.getConnection();
            query = connection.createStatement();
            PreparedStatement posted = connection.prepareStatement(SQLQuery);
            posted.executeUpdate();
            closeDBQuery();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void closeDBQuery(){
        odbConnection.closeConnection();
    }

    public static void main(String args[]){
        try{
            String name = "Wade Wilson";
            int admin = 0;
            postInfo(name, admin);
            ResultSet rs = new ODBQuery().getCustomersAccountsInfo(name, admin);
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
