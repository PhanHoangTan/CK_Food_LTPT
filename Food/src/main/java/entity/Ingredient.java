package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {
    private static final long serialVersionUID = -5023340962065349410L;
    @Id
    @Column(name = "ingredient_id", nullable = false)
    private String id;
    @Column(name = "ingredient_name")
    private String name;
    private String unit;
    private double price;
    private double quantity;
    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;
    @Column(name = "expiry_date")
    private LocalDate expiryDate;
    @Column(name = "supplier_name")
    private String supplierName;

    @ManyToMany
    @JoinTable(
            name = "items_ingredients",
            joinColumns = @JoinColumn(name = "ingredient_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items;


    public Ingredient() {
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", manufacturingDate=" + manufacturingDate +
                ", expiryDate=" + expiryDate +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }

    public Ingredient(String id, String name, String unit, double price, double quantity, LocalDate manufacturingDate, LocalDate expiryDate, String supplierName) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.supplierName = supplierName;
    }
}