//Author: Rodrigo Hernandez

package database;

import java.sql.*;

public class DBQuery {
    private static DBConnection dbConnection;
    private static Statement query;
    private ResultSet resultSet;

    public ResultSet getCustomersAccountsInfo(String firstName, String lastName){
        String SQLquery = "SELECT * FROM notUsers WHERE firstName=\'"+firstName+"\' and " +
                "lastName=\'"+lastName+"\'";
        try {
            dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();
            query = connection.createStatement();
            resultSet = query.executeQuery(SQLquery);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public static void postInfo(String firstName, String lastName){
        String SQLQuery = "INSERT INTO notUsers(firstName, lastName) VALUES ('"+firstName+"', '"+lastName+"')";
        try {
            dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();
            query = connection.createStatement();
            PreparedStatement posted = connection.prepareStatement(SQLQuery);
            posted.executeUpdate();
            closeDBQuery();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void closeDBQuery(){
        dbConnection.closeConnection();
    }

    public static void main(String args[]){
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
