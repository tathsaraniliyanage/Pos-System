package lk.ijse.pos_api.controller;


import lk.ijse.pos_api.dto.CustomerDTO;
import lk.ijse.pos_api.dto.ItemDTO;
import lk.ijse.pos_api.service.ItemService;
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
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil getALl() {
        try {
            List<ItemDTO> list = itemService.getALl();
            if (list != null) {
                return ResponseUtil.builder()
                        .code(200)
                        .data(list)
                        .message("Successfully find find !")
                        .build();
            } else {
                return ResponseUtil.builder()
                        .code(200)
                        .data(null)
                        .message("This Customer not exists in this System Customer id " )
                        .build();
            }

        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }
    }

    @GetMapping(params = {"code"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil getItem(@RequestParam("code") int code) {
        try {
            ItemDTO itemDTO = itemService.getItem(code);
            if (itemDTO != null) {
                return ResponseUtil.builder()
                        .code(200)
                        .data(itemDTO)
                        .message("Successfully find find !")
                        .build();
            } else {
                return ResponseUtil.builder()
                        .code(200)
                        .data(null)
                        .message("This Customer not exists in this System Customer id : " + code)
                        .build();
            }


        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUtil saveItem(@RequestBody ItemDTO itemDTO) {
        try {
            boolean isSaveItem = itemService.saveItem(itemDTO);
            if (isSaveItem) {
                return ResponseUtil.builder().code(201).data(null).message("Successfully Item Save !").build();
            } else {
                return GlobalExceptionHandler.conflicted(new RuntimeException("failed Item Save !"), itemDTO);
            }
        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }

    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseUtil updateItem(@RequestBody ItemDTO itemDTO) {
        try {
            boolean isSaveItem = itemService.updateItem(itemDTO);
            if (isSaveItem) {
                return ResponseUtil.builder().code(201).data(null).message("Successfully Item Save !").build();
            } else {
                return GlobalExceptionHandler.conflicted(new RuntimeException("failed Item Save !"), itemDTO);
            }
        } catch (Exception exception) {
            return GlobalExceptionHandler.gobbleHandleException(exception, null);
        }
    }

    @DeleteMapping(params = {"code"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseUtil deleteItem(@RequestParam("code") String code) {
        return ResponseUtil.builder()
                .code(204)
                .data(code)
                .message("Successfully Item deleted!")
                .build();
    }

}
