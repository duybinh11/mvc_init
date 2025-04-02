package DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartCreateRequest {
    private int idItem;
    private int quantity;
    private int idUser;
}
