package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "beverages")
public class Beverage extends Item  implements Serializable{
    private static final long serialVersionUID = -5023340962065349410L;
    @Enumerated(EnumType.STRING)
    private Size size;
    @Column(name = "supplier_name")
    private String supplierName;

    public Beverage() {
    }

    public Beverage(String id, String name, String description, double price, boolean onSpecial, Size size, String supplierName) {
        super(id, name, description, price, onSpecial);
        this.size = size;
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                ", size=" + size +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}