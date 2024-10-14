package lk.ijse.pos_api.repo;

import lk.ijse.pos_api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
}
