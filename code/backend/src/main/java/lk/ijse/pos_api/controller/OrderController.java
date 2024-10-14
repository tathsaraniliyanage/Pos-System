package lk.ijse.pos_api.controller;

import lk.ijse.pos_api.dto.OrderDetailsDTO;
import lk.ijse.pos_api.dto.OrdersDTO;
import lk.ijse.pos_api.service.OrderService;
import lk.ijse.pos_api.util.GlobalExceptionHandler;
import lk.ijse.pos_api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@RestController
@CrossOrigin
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUtil> saveCustomer(@RequestBody List<OrderDetailsDTO> orderDetails) {

        try {
            OrdersDTO ordersDTO = null;
            if (!orderDetails.isEmpty()) {
                ordersDTO = orderDetails.get(0).getOrdersDTO();
            }
            System.out.println(ordersDTO.toString());
            if (ordersDTO != null) {
                boolean isPalaceOrder = orderService.placeOrder(ordersDTO, orderDetails);
                if (isPalaceOrder)
                    return ResponseEntity.status(201).body(ResponseUtil.builder().data(null).message("Successful Place Order").code(201).build());
            }
            return ResponseEntity.status(409).body(new ResponseUtil().builder().code(409).message("Fail Place Order").data(orderDetails).build());
        } catch (Exception exception) {
            return ResponseEntity.status(500).body(GlobalExceptionHandler.gobbleHandleException(exception, null));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseUtil> getOrders(){
        try {
           List<OrdersDTO> list= orderService.getOrder();
           if (!list.isEmpty()){
               return ResponseEntity.status(200).body(ResponseUtil.builder().data(list).message("Successful find Orders").code(200).build());
           }else {
               return ResponseEntity.status(200).body(ResponseUtil.builder().data(list).message("Empty Orders").code(200).build());
           }
        }catch (Exception exception) {
            return ResponseEntity.status(500).body(GlobalExceptionHandler.gobbleHandleException(exception, null));
        }
    }

    @GetMapping(path = "find-by-id")
    public ResponseEntity<ResponseUtil> getOrdersById(@RequestParam("id")int id){
        try {
            OrdersDTO ordersDTO= orderService.getOrder(id);
            System.out.println(ordersDTO);
            if (ordersDTO !=null){
                return ResponseEntity.status(200).body(ResponseUtil.builder().data(ordersDTO).message("Successful find Orders").code(200).build());
            }else {
                return ResponseEntity.status(200).body(ResponseUtil.builder().data(ordersDTO).message("Empty Orders").code(200).build());
            }
        }catch (Exception exception) {
            return ResponseEntity.status(500).body(GlobalExceptionHandler.gobbleHandleException(exception, null));
        }
    }

    @GetMapping(path = "find-by-id-order-details")
    public ResponseEntity<ResponseUtil> getOrdersDetailsById(@RequestParam("id")int id){
        try {
            List<OrderDetailsDTO> list= orderService.getOrderDetails(id);
            System.out.println(list);
            if (!list.isEmpty()){
                return ResponseEntity.status(200).body(ResponseUtil.builder().data(list).message("Successful find Orders").code(200).build());
            }else {
                return ResponseEntity.status(200).body(ResponseUtil.builder().data(list).message("Empty Orders").code(200).build());
            }
        }catch (Exception exception) {
            return ResponseEntity.status(500).body(GlobalExceptionHandler.gobbleHandleException(exception, null));
        }
    }

}
