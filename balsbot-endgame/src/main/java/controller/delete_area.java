
package controller;



import java.util.HashMap;
import java.util.Set;
import javax.swing.JOptionPane;

//recibe los parametros necesarios para borrar un grupo los valida y confirma la eliminacion

public class delete_area 
{
    public void delete (HashMap<String,String> nombre) throws NullPointerException
    {
        try
        {
            Set<String> llave = nombre.keySet();
            for (String key : llave) 
            {
                if(nombre.get(key).isEmpty())
                {
                    throw new NullPointerException();
                } 
                else 
                {
                    modelx.importGroupData(nombre);
                }
            }
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"error");
        } 
    }
    
    //confirma la eliminacion al usuario
    public void delete_confirmacion (boolean a)
    {
        if(a==true)
        {
            JOptionPane.showMessageDialog(null,"Se ha borrado exitosamente el area");
        }
        else
        {
            JOptionPane.showMessageDialog(null,"No se ha podido borrar el area por favor verifique los datos ingresados");
        }
    }

}
