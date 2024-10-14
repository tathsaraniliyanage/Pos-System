package lk.ijse.pos_api.service;



import lk.ijse.pos_api.dto.ItemDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Component
public interface ItemService {

    List<ItemDTO> getALl();

    ItemDTO getItem(int id);

    boolean saveItem(ItemDTO itemDTO);

    boolean updateItem(ItemDTO itemDTO);

    boolean deleteItem(int id);

    String getImage(String url);
}
