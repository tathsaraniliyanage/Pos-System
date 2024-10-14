package lk.ijse.pos_api.dto;

import lombok.*;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDTO {
    int id;
    String username;
    String fullName;
    String street;
    String lane;
    String city;
    String email;
}
