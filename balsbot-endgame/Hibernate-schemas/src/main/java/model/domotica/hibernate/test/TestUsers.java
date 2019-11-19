package model.domotica.hibernate.test;
import model.domotica.hibernate.schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestUsers {

    private static EntityManagerFactory emf;
    private static EntityManager manager;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("Domotica_App");
        manager = emf.createEntityManager();

        List<User> usuarios = (List<User>) manager.createQuery("FROM User").getResultList();
        System.out.println("hay un total de " + usuarios.size() + " usuarios");
        for (User usuario: usuarios) {
            System.out.println(usuario.toString());
        }
    }
}
