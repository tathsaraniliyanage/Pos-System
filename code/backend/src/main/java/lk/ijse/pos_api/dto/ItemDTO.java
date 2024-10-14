package lk.ijse.pos_api.dto;

import lombok.*;

/**
 * @author Prabodha Thathsarani
 * @date 10/4/24
 * @project webpos-spring
 **/

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private int code;
    private String item_name;
    private double qty;
    private double price;
    private String description;
    private String image;
}
