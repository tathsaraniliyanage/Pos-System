package lk.ijse.pos_api.service;

import lk.ijse.pos_api.dto.CustomerDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Component
public interface CustomerService {

    List<CustomerDTO> getALl();

    CustomerDTO getCustomer(String id);

    boolean saveCustomer(CustomerDTO customerDTO);

    boolean updateCustomer(CustomerDTO customerDTO);

    boolean deleteCustomer(String id);
}
