/**************Created for functionality purpose**********************/
package model;

import java.sql.SQLException;
import java.util.Map;
import java.util.*;

public class Model {
    
    public void importHouse(Map<String,String> data) throws SQLException{
        
    }
    
    public void importGroup(Map<String,String> textFieldsData, List<String> groups) throws SQLException{ 
        /*
        IMPORT ...
            throw new SQLEXCEPTION;
        */
    }
    
    public void importNullGroup(Map<String,String> textFieldsData){
        
    }
    
    
    public List<String> getNullGroups(){
        List<String> nullGroups = new ArrayList<>();
        nullGroups.add("Alex room");
        return nullGroups;
    }
}
