package com.ecommerce.rest.model;
import jakarta.persistence.*;

@Entity
@Table(name = "wares")
public class Ware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "owner")
    private String owner;


    public Ware() {

    }
    public Ware(String name, int price, String owner) {
        this.name = name;
        this.price = price;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){
        return "Ware: [id = " + id + ", name = " + name + ", price = " + price + ", owner = " + owner + "]";
    }

}
