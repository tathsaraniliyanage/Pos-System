package lk.ijse.pos_api.util;

import lombok.*;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseUtil {
    private int code;
    private String message;
    private Object data;
}
