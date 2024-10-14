package lk.ijse.pos_api.service.impl;

import lk.ijse.pos_api.dto.CustomerDTO;
import lk.ijse.pos_api.entity.Customer;
import lk.ijse.pos_api.repo.CustomerRepo;
import lk.ijse.pos_api.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Transactional
@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepo customerRepo;

    public List<CustomerDTO> getALl() {
        try {
            List<Customer> all = customerRepo.findAll();
            if (all.isEmpty() || all == null)
                return new ArrayList<>();
            return modelMapper.map(all, new TypeToken<List<CustomerDTO>>() {
            }.getType());
        } catch (Exception exception) {
            throw exception;
        }

    }

    public CustomerDTO getCustomer(String id) {
        try {
            Optional<Customer> byId = customerRepo.findById(Integer.parseInt(id));
            if (byId.isPresent())
                return modelMapper.map(byId.get(), CustomerDTO.class);
            return null;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public boolean saveCustomer(CustomerDTO customerDTO) {
        try {
            System.out.println(customerDTO.toString());
            Customer customer = customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return customer != null;
        } catch (Exception exception) {
            throw exception;
        }

    }

    public boolean updateCustomer(CustomerDTO customerDTO) {
        try {
            Optional<Customer> byId = customerRepo.findById(customerDTO.getId());
            if (byId.isPresent()) {
                Customer customer = customerRepo.save(modelMapper.map(customerDTO, Customer.class));
                return customer != null;
            }
            return false;
        } catch (Exception exception) {
            throw exception;
        }
    }

    public boolean deleteCustomer(String id) {
        try {
            Optional<Customer> byId = customerRepo.findById(Integer.parseInt(id));
            if (byId.isPresent()) {
                System.out.println("is present");
                customerRepo.deleteById(Integer.parseInt(id));
                return true;
            }
            System.out.println("nit");
            return false;
        } catch (Exception exception) {
            throw exception;
        }
    }
}
