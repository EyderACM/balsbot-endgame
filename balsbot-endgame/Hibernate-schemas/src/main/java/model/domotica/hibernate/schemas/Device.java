package model.domotica.hibernate.schemas;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Device {

    @Id
    @Column(name = "idpermission")
    private int Id;
    @Column(name = "name")
    private String Name;
    @Column(name = "type")
    private String Type;
    @Column(name = "brand")
    private String Brand;
    @Column(name = "model")
    private String Model;
    @Column(name = "admin", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean State;
    @ManyToOne
    @JoinColumn(name = "idgroup")
    private Group group;

    public Device() {
    }

    public Device(int id, String name, String type, String brand, String model, Boolean state, Group group) {
        Id = id;
        Name = name;
        Type = type;
        Brand = brand;
        Model = model;
        State = state;
        this.group = group;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Boolean getState() {
        return State;
    }

    public void setState(Boolean state) {
        State = state;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
