package lk.ijse.pos_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String item_name;
    private double qty;
    private double price;
    private String description;
    private String image;
}
