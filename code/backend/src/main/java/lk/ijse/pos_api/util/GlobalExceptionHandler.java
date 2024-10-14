package lk.ijse.pos_api.util;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static ResponseUtil gobbleHandleException(Exception exception,Object data) {
        return ResponseUtil.builder().code(500).data(data).message(exception.getMessage()).build();
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public  static ResponseUtil conflicted(Exception exception,Object data){
        return ResponseUtil.builder().code(409).data(data).message(exception.getMessage()).build();
    }

}
