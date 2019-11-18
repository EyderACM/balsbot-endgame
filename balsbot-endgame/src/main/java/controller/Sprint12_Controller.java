public class Sprint12_Controller
{
 private Model model;
 private AdminHouse_UI view;

 ///////////////////////////////////////////////////////Funciones de Alex

 /*

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

 */

 //////////////////////////////////////////////////Funciones de Alex

 //Sprints de Amaury

 // Se realizaron ambas funciones tomando en cuenta de que son llamadas por la
 // funcion filtro, y que las excepciones son manejadas por un catcher desde otra
 // funcion

 // Modificar espacio
 public void ModifyHouse(AdminHouse_UI view) throws EmptyException, TooLongException {
  this.model = new Model();
  Map<String, String> data = new HashMap<>();

  data.put("name", view.getHouseName_Input());

  if (!validCompleteness(data)) // Esta funcion de Alex
  {
   throw new EmptyException();
  }
  else if (data.get("name").length() > 45) // los nombres de los espacios no pueden ser mas largos que 45 caracteres
  {
   throw new TooLongException();
  }
  else
  {
   model.importHouse(data); // No tengo las funciones del CRUD, pero eso se arregla despues
  }
 }

 // Iniciar Sesion
 public void LoginUser(AdminHouse_UI view) throws EmptyException, TooLongException {
  this.model = new Model();
  Map<String, String> data = new HashMap<>();

  data.put("user", view.getUsername_Input());
  data.put("password", view.getPassword_Input());  //Vi estas dos propiedades en la presentacion del View sobre sus avances.

  if (!validCompleteness(data)) // Esta funcion de Alex
  {
   throw new EmptyException();
  }
  else if (data.get("user").length() > 45) // Supongo que lo mismo que aplica para espacios aplica para usuarios
  {
   throw new TooLongException();
  }
  else
  {
   model.login(data); // Supongo que el Pseudo API del model tiene esto por algun lado.
   // Aqui podria tenerse una funcion async para saber si el login salio bien
   // O podriamos tener una funcion aparte que el model utilize en nosotros para
   // avisar al view
  }
 }
}