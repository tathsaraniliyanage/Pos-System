package lk.ijse.pos_api.repo;

import lk.ijse.pos_api.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/

public interface OrdersRepo extends JpaRepository<Orders, Integer> {
}
