
package controller;
import view.AdminHouse_UI;
import model.Model;
import exception.EmptyException;
import exception.TooLongException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;

public class AdminHouse_controller {
    
    private Model model;
    private AdminHouse_UI view;
    
    //Create House method
    public void CreateHouse(AdminHouse_UI view) throws EmptyException, TooLongException{
        this.model = new Model();
        Map<String,String> data = new HashMap<>();
        data.put("name", view.getHouseName_Input());
        
        if(!validCompleteness(data)){   //Validates that there's no empty input
            throw new EmptyException();
        }else if(data.get("name").length() > 45){ //Validates house name length
            throw new TooLongException();
        }else{
           
            try{
               model.importHouse(data);
           }
           catch(Exception ex){        //Catch a model type exception and display it to the view
               showCreateHouseError(ex, view);
           }
        }
    }
    
    //Displays if an expected error occured
    public void showCreateHouseError(Exception ex, AdminHouse_UI view){
        
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must type a house name" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof TooLongException){
            JOptionPane.showMessageDialog(
                    view, "House name is too long" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(
                    view, "Something happened", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Displays if there is no error
    public void showCreateHouseSucceed(AdminHouse_UI view){
       JOptionPane.showMessageDialog(
                    view, "House created" , "Success", JOptionPane.INFORMATION_MESSAGE);
    }
     
    //Validate that there is no empty information
    public boolean validCompleteness(Map<String,String> data){
        boolean isComplete = false;
        Set<String> keys = data.keySet();
            for(String key: keys){
                if(!data.get(key).isBlank()){ //Checks that there is no empty information
                    isComplete = true;
                }
            }
            
        return isComplete;
    }
}
