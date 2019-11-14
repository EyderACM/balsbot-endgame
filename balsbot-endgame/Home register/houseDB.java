/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author joses
 */
public class houseDB {
    
    public void addHouse(Houses house){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session  = sesion.openSession();
        Transaction tx = session.beginTransaction();
        if(!exitsHouse(house)){
            session.save(house);
            tx.commit();
            System.out.println("Listo");
        }
        else{
            System.out.println("Ya se encuentra la casa registrada");
        }
        session.close();
    }
    
    public void delete(Houses house){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session  = sesion.openSession();
        session.beginTransaction();
        if(exitsHouse(house)){
            Houses newHouse = searchHouse(house.getName());
            session.delete(newHouse);
            System.out.println("Casa eliminada");
            session.getTransaction().commit();
        }
        else{
            System.out.println("No existe la casa");
        }
        session.close();
    }
    
    public void update(Houses house,String newName){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session  = sesion.openSession();
        session.beginTransaction();
        if(exitsHouse(house)){
            Houses newHouse = searchHouse(house.getName());
            newHouse.setName(newName);
            session.update(newHouse);
            session.getTransaction().commit();
            System.out.println("Se actualizo el nombre de la casa");
        }
        else{
            System.out.println("No se encontro la casa");
        }
        session.close();
    }
    
    public Houses searchHouse(String name){
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session  = sesion.openSession();
        
        Query search = session.createQuery("FROM Houses h WHERE h.name = :name");
        search.setParameter("name",name);
        Houses house = (Houses)search.uniqueResult();
        
        session.close();
        
        return house;
      
 
    }
    
    public boolean exitsHouse(Houses houses){
        boolean exits = false;
        Houses houseSearch = searchHouse(houses.getName());
        if(houseSearch != null){
            System.out.println("Se encontro");
            exits = true;
        }
        else{
            System.out.println("No se encontro");
        }
        return exits;
    }
}
