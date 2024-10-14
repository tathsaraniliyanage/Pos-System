package lk.ijse.pos_api.service;

import lk.ijse.pos_api.dto.OrderDetailsDTO;
import lk.ijse.pos_api.dto.OrdersDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Component
public interface OrderService {
    boolean placeOrder(OrdersDTO ordersDTO, List<OrderDetailsDTO> orderDetails);

    List<OrdersDTO> getOrder();

    OrdersDTO getOrder(int order_id);

    List<OrderDetailsDTO> getOrderDetails(int id);
}
