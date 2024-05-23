package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = " items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item   implements Serializable {
    private static final long serialVersionUID = -5023340962065349410L;
    @Id
    @Column(name = "id")
    protected String id;
    protected String name;
    protected String description;
    protected double price;
    @Column(name = "on_special")
    protected boolean onSpecial;

    @ManyToMany
    @JoinTable(
            name = "items_ingredients",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    protected Set<Ingredient> ingredients;

    @Embedded
    protected NewEntity newEntity;


    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", onSpecial=" + onSpecial +
                '}';
    }

    public Item(String id, String name, String description, double price, boolean onSpecial) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.onSpecial = onSpecial;
    }


}
