
package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import exception.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.AddInvited_UI;
import model.Model;

public class AddInvited_Controller {

    private Model model;

    public void addInvited(AddInvited_UI view) throws EmptyException, UserNotFoundException, UserIsInvitedException{
        this.model = new Model();
        Map<String,String> data = new HashMap<>();
        data.put("username", view.getUsername());

        if(!validCompleteness(data)){   //No empty input
            throw new EmptyException();
        }else if(userExists(view.getUsername(), view)){     //The username does not exists
            throw new UserNotFoundException();
        }else if(userAlreadyInvited(view.getUsername(), view)){     //The username is already an invited
            throw new UserIsInvitedException();
        }

        try{
            model.importInvited(data);
        }catch(Exception ex){
            addInvitedShowError(ex, view);
        }
    }

    //Displays if an expected error occured
    public void addInvitedShowError(Exception ex, AddInvited_UI view){

        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must enter a username" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof UserNotFoundException){
            JOptionPane.showMessageDialog(
                    view, "User does not exists" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof UserIsInvitedException){
            JOptionPane.showMessageDialog(
                    view, "User is already an invited" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof SQLException){
            JOptionPane.showMessageDialog(
                    view, "Database error" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Displays if there is no error
    public void addInvitedSucceed(AddInvited_UI view){
        JOptionPane.showMessageDialog(
                view, "Invited added" , "Success", JOptionPane.INFORMATION_MESSAGE);
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

    //Validate that the user exists
    public boolean userExists(String username, AddInvited_UI view){
        boolean exists = false;

        try{
            exists = model.findUser(username);

        }catch(Exception ex){
            addInvitedShowError(ex,view);
        }

        return exists;
    }

    //Validate that the user is not an invited already
    public boolean userAlreadyInvited(String username, AddInvited_UI view){
        boolean isInvited = false;

        try{
            isInvited = model.findInvited(username);

        }catch(Exception ex){
            addInvitedShowError(ex,view);
        }

        return isInvited;
    }
}
