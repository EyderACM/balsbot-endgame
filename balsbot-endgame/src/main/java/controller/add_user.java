
package prueba;

//recibe,valida y envia los parametros para agregar un usuario

import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;

public class add_user 
{
    public void nuevo_usuario(HashMap<String,String> Nusuario,HashMap<String,String> contraseña,String comprobacion)
    {
        //Try and catch para nombre de usuario
         try
        {
            Set<String> llave = Nusuario.keySet();
            for (String key : llave) 
            {
                if(Nusuario.get(key).isEmpty())
                {
                    throw new NullPointerException();
                } 
                else 
                {
                    modelx.importGroupData(Nusuario);
                }
            }
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"error");
        }
         
        Set<String> llaveC = contraseña.keySet();
         //try and catch para contraseña
         try
        {
            for(String key : llaveC)
            {
                if(contraseña.get(key).isEmpty())
                {
                    throw new NullPointerException();
                } 
            }
           
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"error");
        } 
         
        //verificacion de contraseña
        for (String key : llaveC)
        {
            if(contraseña.get(key).equals(comprobacion))
            {
                modelx.NewUser(contraseña);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Las contraseñas no son iguales");
            }
        }
        
        
    }

}
