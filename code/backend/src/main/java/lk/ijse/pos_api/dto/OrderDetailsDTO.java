package lk.ijse.pos_api.dto;

import lk.ijse.pos_api.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailsDTO {
    private int id;
    private OrdersDTO ordersDTO;
    private Item item;
    private double qty;
    private double price;
}
