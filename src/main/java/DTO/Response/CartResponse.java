package DTO;

import lombok.Data;

@Data
public class CartResponse {
    private ItemResponse item;
    private int quantity;
}
