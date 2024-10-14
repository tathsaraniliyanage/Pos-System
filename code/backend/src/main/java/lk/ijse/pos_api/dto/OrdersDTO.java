package lk.ijse.pos_api.dto;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/

import lk.ijse.pos_api.entity.Customer;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class OrdersDTO {
    private int id;
    private Timestamp date_time;
    private double total;
    private Customer customer;

}
