package lk.ijse.pos_api.repo;

import lk.ijse.pos_api.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@EnableJpaRepositories
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {

    @Query(value = "SELECT * FROM order_details WHERE orders_id=:id",nativeQuery = true)
    List<OrderDetails>findOD(@Param("id") int id);
}
