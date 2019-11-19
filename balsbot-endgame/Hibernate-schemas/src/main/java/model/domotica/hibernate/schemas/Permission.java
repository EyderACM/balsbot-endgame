package model.domotica.hibernate.schemas;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Permission {
    @Id
    @Column(name = "idpermission")
    private int Id;
    @Column(name = "type")
    private String Name;
    @ManyToOne
    @JoinColumn(name = "idgroup")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;

    public Permission() {
    }

    public Permission(int id, String name, Group group, User user) {
        Id = id;
        Name = name;
        this.group = group;
        this.user = user;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", group=" + group.getId() +
                ", user=" + user.getId() +
                '}';
    }
}
