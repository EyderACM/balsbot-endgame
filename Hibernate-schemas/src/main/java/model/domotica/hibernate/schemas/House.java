package model.domotica.hibernate.schemas;
import javax.persistence.*;


@Entity
@Table(name = "houses")
public class House {
    @Id
    @Column(name = "idhouse")
    private int Id;
    @Column(name = "name")
    private String Name;

    public House() {
    }

    public House(int id, String name) {
        Id = id;
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "House{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
