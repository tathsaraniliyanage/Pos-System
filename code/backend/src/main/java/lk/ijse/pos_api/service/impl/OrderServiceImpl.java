package lk.ijse.pos_api.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.pos_api.dto.OrderDetailsDTO;
import lk.ijse.pos_api.dto.OrdersDTO;
import lk.ijse.pos_api.entity.Customer;
import lk.ijse.pos_api.entity.OrderDetails;
import lk.ijse.pos_api.entity.Orders;
import lk.ijse.pos_api.repo.CustomerRepo;
import lk.ijse.pos_api.repo.OrderDetailsRepo;
import lk.ijse.pos_api.repo.OrdersRepo;
import lk.ijse.pos_api.service.ItemService;
import lk.ijse.pos_api.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ItemService itemService;

    @Transactional
    public boolean placeOrder(OrdersDTO ordersDTO, List<OrderDetailsDTO> orderDetails) {

        Optional<Customer> customerOptional = customerRepo.findById(ordersDTO.getCustomer().getId());
        if(customerOptional.isPresent()){
            try {
                Orders orders = modelMapper.map(ordersDTO, Orders.class);
                orders.setCustomer(customerOptional.get());
                Orders save = ordersRepo.save(orders);
                if (save != null) {
                    OrdersDTO dto = modelMapper.map(save, OrdersDTO.class);
                    for (int i = 0; i < orderDetails.size(); i++) {
                        orderDetails.get(i).setOrdersDTO(dto);
                    }
                    return saveOrderDetail(orderDetails);
                } else {
                    throw new RuntimeException("Fail Place order");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }else {
            throw new RuntimeException("Customer not exist in this system");
        }
    }

    @Override
    public List<OrdersDTO> getOrder() {
        try {
            List<Orders> orders = ordersRepo.findAll();
            if (orders.isEmpty())
                return new ArrayList<>();
            return modelMapper.map(orders,new TypeToken<List<OrdersDTO>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public OrdersDTO getOrder(int order_id) {
        try {
            Optional<Orders> orders = ordersRepo.findById(order_id);
            if (!orders.isPresent())
                return OrdersDTO.builder().build();
            return modelMapper.map(orders,OrdersDTO.class);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<OrderDetailsDTO> getOrderDetails(int id) {
        try {
            List<OrderDetails> list = orderDetailsRepo.findOD(id);
            if (list.isEmpty())
                return new ArrayList<>();

            List<OrderDetailsDTO> dtos = modelMapper.map(list, new TypeToken<List<OrderDetailsDTO>>() {
            }.getType());
            for (int i = 0; i < dtos.size(); i++) {
                String image = itemService.getImage(dtos.get(i).getItem().getImage());
                dtos.get(i).getItem().setImage(image);
            }
            return dtos;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    private boolean saveOrderDetail(List<OrderDetailsDTO> orderDetails) {

        try {
            List<OrderDetails> orderDetails1 = orderDetailsRepo.saveAll(modelMapper.map(orderDetails, new TypeToken<List<OrderDetails>>() {
            }.getType()));
            return orderDetails1 != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
