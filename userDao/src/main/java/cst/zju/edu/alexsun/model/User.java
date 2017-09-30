package cst.zju.edu.alexsun.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    public User(){
        this.id = 6666;
        this.name = "6666";
        this.address = "6666";
    }
    public User(int id, String name, String address){
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    @GeneratedValue
    public int getId(){
        return id;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    @Override
    public String toString() {
        return "student id: " + id + " name: "+name+" address in " + address;
    }
}
