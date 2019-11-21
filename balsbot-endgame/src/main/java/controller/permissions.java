
package prueba;

import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;

//Recibe los parametros para modificar los permisos del usuario
public class permissions 
{      
    public void access (HashMap<String,String> Ausuario,HashMap<String,String> grupo,boolean status)
    {
        //Try and catch para nombre de usuario
         try
        {
            Set<String> llave = Ausuario.keySet();
            for (String key : llave) 
            {
                if(Ausuario.get(key).isEmpty())
                {
                    throw new NullPointerException();
                } 
                else 
                {
                    modelx.importGroupData(Ausuario);
                }
            }
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"error");
        }
         
        
         //try and catch para grupo
         try
        {
            Set<String> grupoA = grupo.keySet();
            for(String key : grupoA)
            {
                if(grupo.get(key).isEmpty())
                {
                    throw new NullPointerException();
                }
                else
                {
                   modelx.importGroupData(grupoA); 
                   modelx.importGroupData(status);
                }
            }
           
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"error");
        }                    
           
         
    
    }
    
}
