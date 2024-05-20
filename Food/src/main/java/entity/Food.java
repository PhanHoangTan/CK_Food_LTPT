package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "foods")
public class Food extends Item  implements Serializable{
    private static final long serialVersionUID = -5023340962065349410L;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "preparation_time")
    private int preparationTime;
    @Column(name = "serving_time")
    private int servingTime;


    public Food(String id, String name, String description, double price, boolean onSpecial, Type type, int preparationTime, int servingTime) {
        super(id, name, description, price, onSpecial);
        this.type = type;
        this.preparationTime = preparationTime;
        this.servingTime = servingTime;
    }

    public Food() {
    }

    @Override
    public String toString() {
        return "Food{" +
                "type=" + type +
                ", preparationTime=" + preparationTime +
                ", servingTime=" + servingTime +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", onSpecial=" + onSpecial +
                '}';
    }
}