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
@Table(name = "order_details")
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_code")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    private double qty;
    private double price;
}
