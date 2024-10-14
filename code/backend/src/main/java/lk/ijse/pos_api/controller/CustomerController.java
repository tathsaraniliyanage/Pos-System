package lk.ijse.pos_api.controller;


import lk.ijse.pos_api.dto.CustomerDTO;
import lk.ijse.pos_api.service.CustomerService;
import lk.ijse.pos_api.util.GlobalExceptionHandler;
import lk.ijse.pos_api.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil getALl() {
        try {
            List<CustomerDTO> customerDTOList = customerService.getALl();
            if (customerDTOList != null && !customerDTOList.isEmpty()) {
                return ResponseUtil.builder()
                        .code(200)
                        .data(customerDTOList)
                        .message("Successfully find Customer !")
                        .build();
            } else {
                return ResponseUtil.builder()
                        .code(200)
                        .data(customerDTOList)
                        .message("Customers not exists in this System ")
                        .build();
            }

        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }

    }

    @GetMapping(params = {"id"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil getCustomer(@RequestParam("id") String id) {
        try {
            CustomerDTO customer = customerService.getCustomer(id);
            if (customer != null) {
                return ResponseUtil.builder()
                        .code(200)
                        .data(customer)
                        .message("Successfully find find !")
                        .build();
            } else {
                return ResponseUtil.builder()
                        .code(200)
                        .data(null)
                        .message("This Customer not exists in this System Customer id : " + id)
                        .build();
            }


        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            System.out.println(customerDTO.toString());
            boolean isSaveCustomer = customerService.saveCustomer(customerDTO);
            if (isSaveCustomer) {
                return ResponseUtil.builder().code(201).data(customerDTO).message("Successfully Customer Save !").build();
            } else {
                return GlobalExceptionHandler.conflicted(new RuntimeException("failed Customer Save !"), customerDTO);
            }
        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            boolean isUpdateCustomer = customerService.updateCustomer(customerDTO);
            if (isUpdateCustomer) {
                return ResponseUtil.builder().code(200).data(customerDTO).message("Successfully Customer Update !").build();
            } else {
                return GlobalExceptionHandler.conflicted(new RuntimeException("failed Customer Update !"), customerDTO);
            }
        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }

    }

    @DeleteMapping(params = {"id"})
    public ResponseUtil deleteCustomer(@RequestParam("id") String id) {
        try {
            boolean isDeleteCustomer = customerService.deleteCustomer(id);
            if (isDeleteCustomer) {
                return ResponseUtil.builder()
                        .code(204)
                        .data(id)
                        .message("Successfully Customer deleted!")
                        .build();
            } else {
                System.out.println("not in else");
                return GlobalExceptionHandler.conflicted(new RuntimeException("failed Customer Delete ! customer id is : " + id), id);
            }

        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }


    }

}
