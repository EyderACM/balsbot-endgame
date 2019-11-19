package model.domotica.hibernate.schemas;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "idgroup")
    private int Id;
    @Column(name = "name")
    private String Name;
    @ManyToOne
    @JoinColumn(name = "idhouse")
    private House House;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "groups_relation",
            joinColumns = { @JoinColumn(name = "id_parentgroup") },
            inverseJoinColumns = { @JoinColumn(name = "id_childgroup") }
    )
    private Set<Group> children = new HashSet<>();
    @ManyToMany(mappedBy = "groups")
    private Set<Group> parents = new HashSet<>();

    public Group() {
    }

    public Group(int id, String name, House house) {
        Id = id;
        Name = name;
        House = house;
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

    public House getHouse() {
        return House;
    }

    public void setHouse(House house) {
        House = house;
    }

    @Override
    public String toString() {
        return "Group{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Id House" + House.getId() +
                '}';
    }
}
