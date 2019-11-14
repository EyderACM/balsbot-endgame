
package controller;

import exception.EmptyException;
import exception.TooLongException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import javax.swing.JOptionPane;
import model.Model;
import view.AddGroup_UI;

public class AddGroup_Controller {
    
    private Model model;
    private AddGroup_UI view;
    
    //Add a Group with his childs
    public void addGroup(AddGroup_UI view) throws EmptyException, TooLongException{
       this.model = new Model();
       
       Map<String,String> textFieldsData = new HashMap<>(); //Save the input in a map
       textFieldsData.put("group_name", view.getGroupName());
       
       List<String> groupsSelected = view.getSelectedNullGroups(); //Save the selected "child groups" in a List
       
       if(!validCompleteness(textFieldsData)){   //Validates that there's no empty input in the textfields
           throw new EmptyException();
       }else if(textFieldsData.get("name").length() > 45){ //Validates group name length
           throw new TooLongException();
       }else{

           try{
               if(groupsSelected.isEmpty()){
                   model.importNullGroup(textFieldsData);
               }else{
                   model.importGroup(textFieldsData, groupsSelected);
               }   
           }
           catch(Exception ex){        //Catch a model type exception and display it to the view
               addGroupShowError(ex, view);
           }
       }
    }        
    
    //Gets the current existing nullGroups
    public List<String> getNullGroupsList(){
        return model.getNullGroups();
    }
    
    //Displays if an expected error occured
    public void addGroupShowError(Exception ex, AddGroup_UI view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must type a group name" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof TooLongException){
            JOptionPane.showMessageDialog(
                    view, "Group name is too long" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof SQLException){
            JOptionPane.showMessageDialog(
                    view, "Database error" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    //Displays if there is no error
    public void addGroupShowSucceed(AddGroup_UI view){
       JOptionPane.showMessageDialog(
                    view, "Group created" , "Success", JOptionPane.INFORMATION_MESSAGE);
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
